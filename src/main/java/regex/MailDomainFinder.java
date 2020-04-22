package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailDomainFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.next();
        Matcher m = Pattern.compile("^(\\w+)@(\\w+)(\\.(\\w+))+$").matcher(in);
        if(m.matches()) {
            System.out.println(m.group(2));
            System.out.println(m.group(4));
        }
    }
}
