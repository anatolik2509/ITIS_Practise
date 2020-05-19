package net;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.apache.tika.mime.MimeTypesFactory;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadByURI {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        URI uri = null;
        try {
            uri = new URI(sc.nextLine());
            URLConnection connection = uri.toURL().openConnection();
            InputStream stream = connection.getInputStream();
            String path = uri.toURL().getPath();
            int b = path.lastIndexOf('/');
            String name = path.substring(b == -1 ? 0:b);
            if(b == -1){
                name = '/' + name;
            }
            String type = connection.getContentType();
            MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
            MimeType type1 = mimeTypes.getRegisteredMimeType(type);
            String ext = type1.getExtension();
            b = name.lastIndexOf('.');
            if(b == -1 && name.substring(b + 1).compareTo(ext) != 0){
                name = name + ext;
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
        } catch (MimeTypeException e) {
            e.printStackTrace();
        }
    }
}
