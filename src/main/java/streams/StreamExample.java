package streams;

import java.util.*;

public class StreamExample {
    public static <T> void printElements(List<? extends T> l1, List<? extends T> l2, Comparator<? super T> comparator){
        Optional<? extends T> max = l2.stream().max(comparator);
        l1.forEach((a) -> {
            if(comparator.compare(a, max.get()) > 0){
                System.out.println(a);
            }
        });
    }

    public static void main(String[] args) {
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < 10; i++){
            a1.add(r.nextInt(100));
            a2.add(r.nextInt(70));
        }
        System.out.println(a1);
        System.out.println(a2);
        printElements(a1, a2, (a, b) -> {return a - b;});
    }
}
