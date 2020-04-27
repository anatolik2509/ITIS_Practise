package files;

import java.io.IOException;
import java.util.regex.Matcher;

public interface Command {
    void execute(String command, Matcher m, FileManager f) throws IOException;
}
