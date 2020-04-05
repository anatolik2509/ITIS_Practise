package serialization;

import java.io.IOException;
import java.io.OutputStream;

public class StudentOutputStream extends OutputStream {
    OutputStream out;

    public StudentOutputStream(OutputStream out) {
        this.out = out;
    }

    public void writeStudent(Student s) throws IOException{
        char[] name = s.getName().toCharArray();
        out.write(name.length);
        for(char c : name){
            out.write(c >> 8);
            out.write(c);
        }
        out.write(s.getGender() == Student.Gender.MALE ? 0 : 1);
        out.write(s.getBirthDay());
        out.write(s.getBirthMonth());
        out.write(s.getBirthYear() >> 8);
        out.write(s.getBirthYear());
        char[] group = s.getGroup().toCharArray();
        out.write(group.length);
        for(char c : group){
            out.write(c >> 8);
            out.write(c);
        }
        out.flush();
    }

    public static OutputStream nullOutputStream() {
        return OutputStream.nullOutputStream();
    }

    @Override
    public void write(int i) throws IOException {
        out.write(i);
    }

    @Override
    public void write(byte[] b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
