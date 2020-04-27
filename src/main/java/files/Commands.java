package files;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    OPEN(Pattern.compile("^open ([1-9][0-9]*)"), new Action((s, m, f)->{
        if(m.matches()){
            File file;
            if((file = new File(m.group(1))) != null){
                f.setCurrentFile(file);
            }
            else {
                File[] files = f.getCurrentFile().listFiles();
                int num = Integer.parseInt(m.group(1)) - 1;
                if (num >= files.length || files[num] == null) {
                    System.out.println("No such file!");
                }
                if (!files[num].isDirectory()) {
                    System.out.println("It is not directory!");
                }
                f.setCurrentFile(files[num]);
            }
        }
    }));

    private Pattern pattern;
    private Action action;

    Commands(Pattern pattern, Action action) {
        this.pattern = pattern;
        this.action = action;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Action getAction() {
        return action;
    }
}
