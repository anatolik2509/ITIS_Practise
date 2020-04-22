package streams;

import java.util.HashSet;
import java.util.Set;

public class StringSetCounter {
    public static long countStrings(Set<String> set){
        return set.stream().filter((s) -> {
            int r = 0;
            char c;
            for(int i = 0; i < s.length(); i++){
                c = s.charAt(i);
                if(c == 'e' || c == 'y' || c == 'u' || c == 'i' || c == 'o' || c == 'a'){
                    r++;
                }
            }
            return r > 3;
        }).count();
    }

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("abcde");
        set.add("udfkhglsi");
        set.add("aaaauefgi");
        set.add("fyeasc");
        set.add("jnfdsfj");
        set.add("aeyubc");
        set.add("aaaaaaaaa");
        set.add("bbbbbbb");
        set.add("woauhserilgh");
        System.out.println(countStrings(set));
    }
}
