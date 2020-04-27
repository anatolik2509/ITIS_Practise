package files;

import java.util.regex.Pattern;

public class CommandMatcher {
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


}
