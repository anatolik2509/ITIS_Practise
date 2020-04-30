package net;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadByURI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        URI uri = null;
        try {
            uri = new URI(sc.nextLine());
            URLConnection connection = uri.toURL().openConnection();
            InputStream stream = connection.getInputStream();
            String path = uri.toURL().getPath();
            int b = path.lastIndexOf('/');
            String name = path.substring(b == -1 ? 0:b);
            String type = connection.getContentType();
            int e = type.indexOf(';', 0);
            String ext;
            if(e == -1){
                ext = '.' + type.substring(type.indexOf('/', 0) + 1);
            }
            else {
                ext = '.' + type.substring(type.indexOf('/', 0) + 1, e);
            }
            if(name.lastIndexOf('.') == -1 || name.substring(name.lastIndexOf('.')).compareTo(ext) != 0){
                name += ext;
            }
            FileOutputStream outputStream = new FileOutputStream("C:/test" + name);
            int in;
            while(((in = stream.read()) != -1)){
                outputStream.write(in);
            }
            outputStream.flush();
            outputStream.close();
        }
        catch (URISyntaxException | MalformedURLException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
