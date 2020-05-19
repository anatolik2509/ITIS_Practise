package threads;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Scanner;

public class FileDownload {
    public static final String URL = "https://r13---sn-n8v7znze.googlevideo.com/videoplayback?expire=1589947902&ei=nlnEXvCPJZWMxN8P_tO7kAI&ip=54.229.251.214&id=o-AA3r3-2xa9Oa9AHgRgN7wqMGrJNk-mPR5EmJ_q2_fELG&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&gir=yes&clen=8674318&ratebypass=yes&dur=169.853&lmt=1409707796872361&fvip=4&c=WEB&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgZF0WPo9RrOLARlbTgMVO6-R7hBKf6YvgiBzgKGoJXRcCIHLHRpUDmW1Cf0_3ol1MSw-CCCeE4DbgYvkVM1sl8O-T&video_id=0NaezPgQ4WE&title=%D0%93%D0%B5%D0%BD%D0%B4%D0%B0%D0%BB%D1%8C%D1%84+%D0%B6%D0%B6%D0%B5%D1%82&rm=sn-q0ce77z&req_id=82bdf6992a8a3ee&ipbypass=yes&redirect_counter=2&cm2rm=sn-uxajvnxg8pjxg0-31oe7z&cms_redirect=yes&mh=19&mip=178.204.217.19&mm=29&mn=sn-n8v7znze&ms=rdu&mt=1589926229&mv=m&mvi=12&pl=19&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIhALp4NQODvN1-rGZEPqd76Mkn7stLZKsq49mD6niLOdNKAiBM3NGFEjk7UKY5iK-Zkzx6u9pdMBcEVx_m8py-C1kPVA%3D%3D";
    public static final long SIZE = 8674318;

    private static long count = 0;
    private static boolean download = false;

    public static void main(String[] args) {
        Downloader d = new Downloader();
        Thread downloadThread = new Thread(d);
        UserInteract interact = new UserInteract();
        Thread interactThread = new Thread(interact);
        downloadThread.start();
        interactThread.start();
    }

    private static class Downloader implements Runnable{

        @Override
        public void run() {
            URI uri = null;
            try {
                uri = new URI(URL);
                URLConnection connection = uri.toURL().openConnection();
                InputStream stream = connection.getInputStream();
                String path = uri.toURL().getPath();
                int b = path.lastIndexOf('/');
                String name = "video";
                String type = connection.getContentType();
                MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
                MimeType type1 = mimeTypes.getRegisteredMimeType(type);
                String ext = type1.getExtension();
                name = name + ext;
                FileOutputStream outputStream = new FileOutputStream("C:/download/" + name);
                int in;
                while(((in = stream.read()) != -1)){
                    while (!download){
                        Thread.sleep(100);
                    }
                    count++;
                    outputStream.write(in);
                }
                outputStream.flush();
                outputStream.close();
                System.out.println("Done");
            }
            catch (URISyntaxException | MalformedURLException e){
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (MimeTypeException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class UserInteract implements Runnable{

        @Override
        public void run() {
            Scanner sc = new Scanner(System.in);
            String command = sc.next();
            boolean exit = false;
            while (!exit){
                switch (command){
                    case "start":
                        download = true;
                        System.out.println("Download started");
                        break;
                    case "status":
                        System.out.println(count * 100 / SIZE + "%");
                        break;
                    case "stop":
                        download = false;
                        System.out.println("Download stopped");
                        if(count < SIZE){
                            System.out.println("DOWNLOAD DONT COMPLETED!!!");
                        }
                        break;
                    case "exit":
                        System.out.println("Goodbye");
                        exit = true;
                        break;
                    default:
                        System.out.println("Unknown command");
                        break;
                }
                if(exit) break;
                command = sc.next();
            }
        }
    }
}
