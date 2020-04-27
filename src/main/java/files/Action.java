package files;

import java.util.regex.Matcher;

public class Action{

    private Command c;

    public void perform(String command, Matcher m, FileManager f){
        c.execute(command, m, f);
    }

    public Action(Command c) {
        this.c = c;
    }
}
