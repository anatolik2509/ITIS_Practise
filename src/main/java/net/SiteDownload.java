package net;

import files.FileActions;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteDownload {

    private static HashSet<String> downloaded = new HashSet<>();

    public static final String OUTPUT = "C:/test";

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(new File("input.txt"));
        File f = new File(OUTPUT);
        FileActions.deleteDirectory(f);
        File file = download(sc.nextLine(), OUTPUT);
        data_structures.Queue<String> queue = new data_structures.Queue<String>();
        Pattern p = Pattern.compile("ref[\\s]*=[\\s]*\"([^\"]*)\"");
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String content = "";
        while (r.ready()){
            content += r.readLine();
        }
        Matcher m = p.matcher(content);
        while (m.find()){
            queue.add(m.group(1));
        }
        while (queue.peek() != null){
            file = download(queue.pop(), OUTPUT);
            if(file != null){
                r = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                content = "";
                while (r.ready()){
                    content += r.readLine();
                }
                m = p.matcher(content);
                while (m.find()){
                    queue.add(m.group(1));
                }
                System.out.println("In queue: " + queue.size());
            }
            Thread.sleep(100);
        }
    }

    public static File download(String url, String outputPath) throws FileNotFoundException {
        URI uri = null;
        String name = "";
        File f = null;
        try {
            uri = new URI(url);
            URLConnection connection = uri.toURL().openConnection();
            InputStream stream = connection.getInputStream();
            name = uri.getPath();
            String type = connection.getContentType();
            MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
            MimeType type1 = mimeTypes.getRegisteredMimeType(type);
            String ext = type1.getExtension();
            name = name + ext;
            f = new File(Paths.get(outputPath, name).toString());
            File f2 = new File(String.valueOf(f.toPath().resolve("..")));
            f2.mkdirs();
            if(!f.exists()) {
                System.out.println(name + " " + url);
                FileOutputStream outputStream = new FileOutputStream(f);
                int in;
                while (((in = stream.read()) != -1)) {
                    outputStream.write(in);
                }
                outputStream.flush();
                outputStream.close();
                downloaded.add(name);
            }
            else {
                return null;
            }
        } catch (URISyntaxException | IOException e){
            System.out.println(e.getMessage());
        } catch (MimeTypeException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            downloaded.add(name);
            return null;
        }
        return f;
    }
}
