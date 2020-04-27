package files;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandPerformer {
    public void perform(String command, FileManager f) throws IOException {
        Matcher m;
        for(Commands c : Commands.values()){
            m = c.getPattern().matcher(command);
            if(m.matches()){
                c.getAction().execute(command, c.getPattern().matcher(command), f);
                return;
            }
        }
        System.out.println("Unknown command!");
    }
}
