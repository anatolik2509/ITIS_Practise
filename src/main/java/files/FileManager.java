package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileManager {

    private FileSystem fileSystem;
    private File currentFile;
    private File bufferFile;
    private boolean cut;
    private boolean exit;

    public static final Pattern OPEN_REG_EX = Pattern.compile("^open ([1-9][0-9]*)");
    public static final Pattern INFO_REG_EX = Pattern.compile("^info ([1-9][0-9]*)");
    public static final Pattern CREATE_REG_EX = Pattern.compile("^create (\\w+)");
    public static final Pattern DELETE_REG_EX = Pattern.compile("^delete ([1-9][0-9]*)");
    public static final Pattern EXECUTE_REG_EX = Pattern.compile("^execute ([1-9][0-9]*)");
    public static final Pattern UP_REG_EX = Pattern.compile("^up");
    public static final Pattern COPY_REG_EX = Pattern.compile("^copy ([1-9][0-9]*)");
    public static final Pattern CUT_REG_EX = Pattern.compile("^cut ([1-9][0-9]*)");
    public static final Pattern PASTE_REG_EX = Pattern.compile("^paste");
    public static final Pattern EXIT_REG_EX = Pattern.compile("^exit");

    public void init() throws IOException {
        Scanner sc = new Scanner(System.in);
        exit = false;
        fileSystem = FileSystems.getDefault();
        currentFile = new File("C:/");
        bufferFile = null;
        cut = false;
        String command;
        CommandPerformer performer = new CommandPerformer();
        while(!exit){
            int count = 1;
            System.out.println(currentFile);
            File files[] = new File[currentFile.listFiles().length];
            for(File f : currentFile.listFiles()){
                if(!f.isHidden()) {
                    files[count - 1] = f;
                    System.out.printf("%d) %s\n", count, f.getName(), f.getTotalSpace());
                    count++;
                }
            }
            command = sc.nextLine();
            System.out.println();
            performer.perform(command, this);
        }
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public File getBufferFile() {
        return bufferFile;
    }

    public void setBufferFile(File bufferFile) {
        this.bufferFile = bufferFile;
    }

    public boolean isCut() {
        return cut;
    }

    public void setCut(boolean cut) {
        this.cut = cut;
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

    public void setFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public void exit(){
        exit = true;
    }

    public static void main(String[] args){
        FileManager fileManager = new FileManager();
        try{
            fileManager.init();
        }
        catch (IOException e){
            System.out.println("Error!\n" + e.getMessage());
        }
    }



}
