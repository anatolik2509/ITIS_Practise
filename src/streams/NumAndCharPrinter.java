package streams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class NumAndCharPrinter {
    public void print(int n, char c, String path){
        try(FileOutputStream stream = new FileOutputStream(new File(path))){
            stream.write(n>>24);
            stream.write(n>>16);
            stream.write(n>>8);
            stream.write(n);
            stream.write(c>>8);
            stream.write(c);
            stream.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NumAndCharPrinter n = new NumAndCharPrinter();
        n.print(16777400, 'ы', "test.txt");
    }
}
