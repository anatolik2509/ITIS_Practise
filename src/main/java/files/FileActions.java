package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileActions {
    public static void copyDirectory(File f, Path destination) throws IOException {
        Files.copy(f.toPath(), destination);
        if(f.isDirectory()) {
            for (File file : f.listFiles()) {
                copyDirectory(file, Paths.get(destination.toString(), file.getName()));
            }
        }
    }

    public static void deleteDirectory(File f){
        if(f.isDirectory()) {
            for (File file : f.listFiles()) {
                deleteDirectory(file);
            }
        }
        f.delete();
    }

    public static long getDirectorySpace(File f){
        if(f.isDirectory()) {
            long r = 0;
            for (File file : f.listFiles()) {
                r += getDirectorySpace(file);
            }
            return r;
        }
        else {
            return f.getTotalSpace();
        }
    }
}
