package net;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DivCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        URI uri = null;
        try {
            uri = new URI(sc.nextLine());
            URLConnection connection = uri.toURL().openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder s = new StringBuilder();
            String in;
            while ((in = reader.readLine()) != null){
                s.append(in + '\n');
            }
            System.out.println(s);
            Matcher m = Pattern.compile("(<\\s*div.*>)|(<\\s*img.*>)").matcher(s);
            int count = 0;
            while(m.find()){
                count++;
            }
            System.out.println(count);
        }
        catch (URISyntaxException | MalformedURLException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
