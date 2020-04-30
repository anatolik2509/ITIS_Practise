import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import serialization.Student;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, IOException, URISyntaxException {
        FileTypeMap map = MimetypesFileTypeMap.getDefaultFileTypeMap();
        System.out.println(map.getContentType("image/jpeg"));
    }
}
