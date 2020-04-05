package serialization;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class StudentDataOutputStream extends DataOutputStream {
    public StudentDataOutputStream(OutputStream out) {
        super(out);
    }

    public void writeStudent(Student s) throws IOException {
        writeChars(s.getName());
        writeChars(s.getGender().name());
        write(s.getBirthDay());
        write(s.getBirthMonth());
        writeShort(s.getBirthYear());
        writeChars(s.getGroup());
    }
}
