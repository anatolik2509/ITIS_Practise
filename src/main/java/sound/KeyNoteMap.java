package sound;

import java.util.Map;
import java.util.TreeMap;

public class KeyNoteMap {
    private static final Map<Character, Integer> map = new TreeMap<>();

    public static void init(){
        map.put('q', 34);
        map.put('w', 36);
        map.put('e', 38);
        map.put('r', 40);
        map.put('t', 42);
        map.put('y', 44);
        map.put('u', 46);
        map.put('i', 48);
        map.put('o', 56);
        map.put('p', 58);
        map.put('a', 60);
        map.put('s', 62);
        map.put('d', 64);
        map.put('f', 66);
        map.put('g', 68);
        map.put('h', 70);
        map.put('j', 72);
        map.put('k', 74);
        map.put('l', 76);
        map.put('z', 78);
        map.put('x', 80);
        map.put('c', 82);
        map.put('v', 84);
        map.put('b', 86);
        map.put('n', 88);
        map.put('m', 90);
    }

    public static int get(char c){
        return map.get(c);
    }
}
