package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

    public static final Pattern OPEN_REG_EX = Pattern.compile("^open ([1-9][0-9]*)");
    public static final Pattern INFO_REG_EX = Pattern.compile("^info ([1-9][0-9]*)");
    public static final Pattern CREATE_REG_EX = Pattern.compile("^create (\\w+)");
    public static final Pattern DELETE_REG_EX = Pattern.compile("^delete ([1-9][0-9]*)");
    public static final Pattern EXECUTE_REG_EX = Pattern.compile("^execute ([1-9][0-9]*)");
    public static final Pattern UP_REG_EX = Pattern.compile("^up");
    public static final Pattern COPY_REG_EX = Pattern.compile("^copy ([1-9][0-9]*)");
    public static final Pattern CUT_REG_EX = Pattern.compile("^cut ([1-9][0-9]*)");
    public static final Pattern PASTE_REG_EX = Pattern.compile("^paste");
    public static final Pattern EXIT_REG_EX = Pattern.compile("^exit");

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        FileSystem fileSystem = FileSystems.getDefault();
        File currentFile = new File("C:/");
        File bufferFile = null;
        boolean cut = false;
        String command;
        while(!exit){
            int count = 1;
            System.out.println(currentFile);
            File files[] = new File[currentFile.listFiles().length];
            for(File f : currentFile.listFiles()){
                if(!f.isHidden()) {
                    files[count - 1] = f;
                    System.out.printf("%d) %s\n", count, f.getName(), f.getTotalSpace());
                    count++;
                }
            }
            command = sc.nextLine();
            System.out.println();
            Matcher matcher = OPEN_REG_EX.matcher(command);
            if(matcher.matches()){
                int num = Integer.parseInt(matcher.group(1)) - 1;
                if(num >= files.length || files[num] == null){
                    System.out.println("No such file!");
                    continue;
                }
                if(!files[num].isDirectory()){
                    System.out.println("It is not directory!");
                    continue;
                }
                currentFile = files[num];
                continue;
            }
            matcher.usePattern(INFO_REG_EX);
            if(matcher.matches()){
                int num = Integer.parseInt(matcher.group(1)) - 1;
                if(num >= files.length || files[num] == null){
                    System.out.println("No such file!");
                    continue;
                }
                File f = files[num];
                System.out.println("Name: " + f.getName());
                System.out.println("Last modified: " + new Date(f.lastModified()));
                continue;
            }
            matcher.usePattern(CREATE_REG_EX);
            if(matcher.matches()){
                File newFile = new File(currentFile.getPath() + fileSystem.getSeparator() + (matcher.group(1)));
                newFile.mkdir();
                continue;
            }
            matcher.usePattern(DELETE_REG_EX);
            if(matcher.matches()){
                int num = Integer.parseInt(matcher.group(1)) - 1;
                if(num >= files.length || files[num] == null){
                    System.out.println("No such file!");
                    continue;
                }
                files[num].delete();
                continue;
            }
            matcher.usePattern(UP_REG_EX);
            if(matcher.matches()){
                currentFile = currentFile.getParentFile();
                continue;
            }
            matcher.usePattern(EXECUTE_REG_EX);
            if(matcher.matches()){
                int num = Integer.parseInt(matcher.group(1)) - 1;
                if(num >= files.length || files[num] == null){
                    System.out.println("No such file!");
                    continue;
                }
                String mimeType = Files.probeContentType(files[num].toPath());
                if(mimeType == null){
                    System.out.println("Unknown type");
                    continue;
                }
                Matcher m = Pattern.compile("(.*)/.*").matcher(mimeType);
                m.matches();
                String type = m.group(1);
                System.out.println("cmd c/ start notepad.exe " + files[num].getPath());
                switch (type){
                    case "image":
                        Runtime.getRuntime().exec("mspaint.exe " + files[num].getPath());
                        break;
                    case "text":
                        Runtime.getRuntime().exec("start notepad.exe" + files[num].getPath());
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
                continue;
            }
            matcher.usePattern(COPY_REG_EX);
            if(matcher.matches()){
                int num = Integer.parseInt(matcher.group(1)) - 1;
                if(num >= files.length || files[num] == null){
                    System.out.println("No such file!");
                    continue;
                }
                bufferFile = files[num];
                cut = false;
                continue;
            }
            matcher.usePattern(CUT_REG_EX);
            if(matcher.matches()){
                int num = Integer.parseInt(matcher.group(1)) - 1;
                if(num >= files.length || files[num] == null){
                    System.out.println("No such file!");
                    continue;
                }
                bufferFile = files[num];
                cut = true;
                continue;
            }
            matcher.usePattern(PASTE_REG_EX);
            if(matcher.matches()){
                if(cut){
                    Files.move(bufferFile.toPath(), Paths.get(currentFile.getPath(), bufferFile.getName()));
                }
                else{
                    Files.copy(bufferFile.toPath(), Paths.get(currentFile.getPath(), bufferFile.getName()));
                }
                continue;
            }
            matcher.usePattern(EXIT_REG_EX);
            if(matcher.matches()){
                exit = true;
                continue;
            }
            System.out.println("Unknown command");
        }
    }

}
