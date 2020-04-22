package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class StudentObjectInputStream extends ObjectInputStream {

    public StudentObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    public Student readStudent() throws IOException, ClassNotFoundException {
        return (Student)readObject();
    }
}
