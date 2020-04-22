package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.util.Collection;

public class StudentYamlOutputStream extends OutputStream {
    private DataOutputStream out;

    public StudentYamlOutputStream(OutputStream out) {
        this.out = new DataOutputStream(out);
    }

    public void writeStudents(Student student) throws IOException {
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        om.writeValue((DataOutput) out, student);
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
