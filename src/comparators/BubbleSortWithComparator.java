package comparators;

import java.util.Comparator;

public class BubbleSortWithComparator {
    public static <T> void sort(T[] arr, Comparator<? super T> comparator){
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < arr.length; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = true;
                }
            }
        }
    }
}
