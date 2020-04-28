package files;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    OPEN(Pattern.compile("^open ([1-9][0-9]*)"), (s, m, f)->{
        if(m.matches()){
            File[] files = new File[f.getCurrentFile().listFiles().length];
            int count = 0;
            for(File file1 : f.getCurrentFile().listFiles()) {
                if(!file1.isHidden()) {
                    files[count] = file1;
                    count++;
                }
            }
            int num = Integer.parseInt(m.group(1)) - 1;
            if (num >= files.length || num < 0 || files[num] == null) {
                System.out.println("No such file!");
                return;
            }
            if (!files[num].isDirectory()) {
                System.out.println("It is not directory!");
                return;
            }
            f.setCurrentFile(files[num]);
        }
    }),
    OPEN_WITH_ABSOLUTE_PATH(Pattern.compile("^open ([\\wа-яА-Я/\\\\:\\.]*)"), (s, m, f)->{
        if(m.matches()){
            File file = new File(m.group(1));
            file = new File(String.valueOf(f.getCurrentFile().toPath().resolve(file.toPath())));
            if((file).exists() && file.isDirectory()){
                f.setCurrentFile(file);
            }
            else {
                System.out.println("No such file!");

            }
        }
    }),
    COPY(Pattern.compile("^copy ([1-9][0-9]*)"), (s, m, f) -> {
        if(m.matches()){
            File[] files = new File[f.getCurrentFile().listFiles().length];
            int count = 0;
            for(File file1 : f.getCurrentFile().listFiles()) {
                if(!file1.isHidden()) {
                    files[count] = file1;
                    count++;
                }
            }
            int num = Integer.parseInt(m.group(1)) - 1;
            if(num >= files.length || num < 0 || files[num] == null){
                System.out.println("No such file!");
                return;
            }
            f.setBufferFile(files[num]);
            f.setCut(false);
        }
    }),
    INFO(Pattern.compile("^info ([1-9][0-9]*)"), (s, m, f) -> {
        if(m.matches()) {
            File[] files = new File[f.getCurrentFile().listFiles().length];
            int count = 0;
            for (File file1 : f.getCurrentFile().listFiles()) {
                if (!file1.isHidden()) {
                    files[count] = file1;
                    count++;
                }
            }
            int num = Integer.parseInt(m.group(1)) - 1;
            if(num >= files.length || num < 0 || files[num] == null){
                System.out.println("No such file!");
                return;
            }
            System.out.println(files[num].getName());
            System.out.println(new Date(files[num].lastModified()));
            //System.out.println(FileActions.getDirectorySpace(files[num]) + " bytes");
        }
    }),
    CREATE(Pattern.compile("^create ([\\w\\.]+)"), (s, m, f) -> {
        if(m.matches()){
            File newFile = new File(f.getCurrentFile().getPath() + f.getFileSystem().getSeparator() + (m.group(1)));
            newFile.mkdir();
        }
    }),
    DELETE(Pattern.compile("^delete ([1-9][0-9]*)"), (s, m, f) -> {
        if(m.matches()){
            File[] files = new File[f.getCurrentFile().listFiles().length];
            int count = 0;
            for (File file1 : f.getCurrentFile().listFiles()) {
                if (!file1.isHidden()) {
                    files[count] = file1;
                    count++;
                }
            }
            int num = Integer.parseInt(m.group(1)) - 1;
            if(num >= files.length || num < 0 || files[num] == null){
                System.out.println("No such file!");
                return;
            }
            FileActions.deleteDirectory(files[num]);
        }
    }),
    UP(Pattern.compile("^up"), (s, m, f) -> {
        if(m.matches()){
            File file = f.getCurrentFile().getParentFile();
            if(file != null) {
                f.setCurrentFile(f.getCurrentFile().getParentFile());
            }
        }
    }),
    CUT(Pattern.compile("^cut ([1-9][0-9]*)"), (s, m, f) -> {
        if(m.matches()){
            File[] files = new File[f.getCurrentFile().listFiles().length];
            int count = 0;
            for(File file1 : f.getCurrentFile().listFiles()) {
                if(!file1.isHidden()) {
                    files[count] = file1;
                    count++;
                }
            }
            int num = Integer.parseInt(m.group(1)) - 1;
            if(num >= files.length || num < 0 || files[num] == null){
                System.out.println("No such file!");
                return;
            }
            f.setBufferFile(files[num]);
            f.setCut(true);
        }
    }),
    PASTE(Pattern.compile("^paste"), (s, m, f) -> {
        if(m.matches()){
            if(f.isCut()){
                Files.move(f.getBufferFile().toPath(), Paths.get(f.getCurrentFile().getPath(), f.getBufferFile().getName()));
            }
            else{
                FileActions.copyDirectory(f.getBufferFile(), Paths.get(f.getCurrentFile().getPath(), f.getBufferFile().getName()));
            }
        }
    }),
    EXECUTE(Pattern.compile("^execute ([1-9][0-9]*)"), (s, m, f) -> {
        if(m.matches()){
            File[] files = new File[f.getCurrentFile().listFiles().length];
            int count = 0;
            for(File file1 : f.getCurrentFile().listFiles()) {
                if(!file1.isHidden()) {
                    files[count] = file1;
                    count++;
                }
            }
            int num = Integer.parseInt(m.group(1)) - 1;
            if(num >= files.length || files[num] == null){
                System.out.println("No such file!");
            }
            String mimeType = Files.probeContentType(files[num].toPath());
            if(mimeType == null){
                System.out.println("Unknown type");
                return;
            }
            Matcher matcher = Pattern.compile("(.*)/.*").matcher(mimeType);
            matcher.matches();
            String type = matcher.group(1);
            switch (type){
                case "image":
                    Runtime.getRuntime().exec("mspaint.exe " + files[num].getPath());
                    break;
                case "text":
                    Runtime.getRuntime().exec("notepad.exe " + files[num].getPath());
                    break;
                case "audio":
                case "video":
                    Runtime.getRuntime().exec("cmd /c start wmplayer.exe \"" + files[num].getPath() + '"');
                    break;
                case "application":
                    Runtime.getRuntime().exec("cmd /c start " + files[num].getPath());
                    break;
                default:
                    System.out.println("Unknown type");
                    break;
            }
        }
    }),
    EXIT(Pattern.compile("^exit"), (s, m, f) -> {
        f.exit();
    });

    private Pattern pattern;
    private Command action;

    Commands(Pattern pattern, Command action) {
        this.pattern = pattern;
        this.action = action;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Command getAction() {
        return action;
    }
}
