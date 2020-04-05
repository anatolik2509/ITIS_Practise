package serialization;

import java.io.*;
import java.util.ArrayList;

public class StudentInputStream extends InputStream {
    InputStream in;

    public StudentInputStream(InputStream in) {
        this.in = in;
    }

    public Student readStudent() throws IOException{
        char[] name = new char[in.read()];
        for(int i = 0; i < name.length; i++){
            name[i] |= in.read() << 8;
            name[i] |= in.read();
        }
        String studentName = new String(name);
        Student.Gender gender = in.read() == 0 ? Student.Gender.MALE : Student.Gender.FEMALE;
        byte birthDay = (byte)in.read();
        byte birthMonth = (byte)in.read();
        short birthYear = (short)((in.read() << 8) + in.read());
        char[] group = new char[in.read()];
        for(int i = 0; i < group.length; i++){
            group[i] |= in.read() << 8;
            group[i] |= in.read();
        }
        String studentGroup = new String(group);
        return new Student(studentName, gender, birthDay, birthMonth, birthYear, studentGroup);
    }

    public static InputStream nullInputStream() {
        return InputStream.nullInputStream();
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return in.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return in.read(b, off, len);
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return in.readAllBytes();
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        return in.readNBytes(len);
    }

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException {
        return in.readNBytes(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public void mark(int readlimit) {
        in.mark(readlimit);
    }

    @Override
    public void reset() throws IOException {
        in.reset();
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        return in.transferTo(out);
    }
}
