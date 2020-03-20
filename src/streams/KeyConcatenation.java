package streams;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyConcatenation {
    public static <K, V> String concatenate(Map<K, V> m){
        String s;
        s = m.keySet().stream().map(Object::toString).reduce((a, b) -> {return a + b;}).get();
        return s;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 1);
        map.put("d", 1);
        map.put("e", 1);
        System.out.println(concatenate(map));
    }
}
