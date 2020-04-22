package comparators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class LambdaComparatorExample {
    public static void main(String[] args) {
        int n = 100;
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for(int i = 0; i < n; i++){
            arr[i] = random.nextInt(1000);
        }
        Arrays.sort(arr, (a, b) -> {return a - b;});
        System.out.println(Arrays.toString(arr));
    }
}
