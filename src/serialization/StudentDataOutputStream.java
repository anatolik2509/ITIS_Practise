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
        writeUTF(s.getName());
        writeUTF(s.getGender().name());
        writeByte(s.getBirthDay());
        writeByte(s.getBirthMonth());
        writeShort(s.getBirthYear());
        writeUTF(s.getGroup());
    }
}
