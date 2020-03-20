package streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NumAndCharReader {
    public void read(String path){
        try(FileInputStream stream = new FileInputStream(new File(path))){
            int n = (stream.read()<<24) + (stream.read()<<16) + (stream.read()<<8) + stream.read();
            char c = (char)((stream.read()<<8) + stream.read());
            System.out.println(n);
            System.out.println(c);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NumAndCharReader n = new NumAndCharReader();
        n.read("test.txt");
    }
}
