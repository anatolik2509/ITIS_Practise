package serialization;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class StudentObjectOutputStream extends ObjectOutputStream {

    public StudentObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public void writeStudent(Student s) throws IOException{
        writeObject(s);
    }
}
