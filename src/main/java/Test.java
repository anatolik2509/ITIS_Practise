import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;
import serialization.Student;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, IOException, URISyntaxException {
        URI uri = new URI("https://yandex.ru/news/story/Tulskaya_oblast_vvela_cifrovye_propuska--f1c3c36231541fe35110d4019f9deed2?from=main_portal&lang=ru&lr=43&mlid=1588091561.glob_225.f1c3c362&msid=1588092352.78061.85141.5131&persistent_id=95846039&stid=258ivSJ3mCQ4w9V5ZeuM&t=1588091561&utm_medium=topnews_news&utm_source=morda_desktop&wan=1");
        System.out.println(uri.toURL().getPath());
    }
}
