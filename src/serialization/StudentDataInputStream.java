package serialization;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class StudentDataInputStream extends DataInputStream {

    public StudentDataInputStream(InputStream in) {
        super(in);
    }

    public Student readStudent() throws IOException {
        return new Student(readUTF(), Student.Gender.valueOf(readUTF()), readByte(), readByte(), readShort(), readUTF());
    }
}
