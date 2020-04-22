import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import serialization.Student;

import java.io.PrintWriter;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException {
        Yaml yaml = new Yaml(new Constructor(Student.class));
        Student s = new Student("aa", Student.Gender.MALE, 1, 1, 1, "11-903");
        PrintWriter wr = new PrintWriter(System.out);
        yaml.addTypeDescription(new TypeDescription(Student.class));
        yaml.dump(s, wr);
        System.out.println(yaml.dumpAs(s, Tag.MAP, null));
    }
}
