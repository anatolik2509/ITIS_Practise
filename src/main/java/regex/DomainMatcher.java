package regex;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.regex.Pattern;

public class DomainMatcher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.next();
        System.out.println(Pattern.matches("^(\\w+)(\\.(\\w+))+$", in));
    }
}
