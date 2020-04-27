package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        Matcher m = Pattern.compile("(\\w+)(\\.(\\w+))+").matcher(in);
        while(m.find()){

            System.out.println(m.group(0));
        }
    }
}
