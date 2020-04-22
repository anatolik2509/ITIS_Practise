package streams;

import java.util.Collection;

public class StringLengths {
    public static int sum(Collection<String> collection){
        return collection.stream().map(String::length).filter((a) -> a > 5).reduce((a, b) -> a + b).get();
    }
}
