package comparators;

import java.lang.Comparable;
import java.util.ArrayList;

public class BubbleSort {
    public static void sort(Comparable[] arr){
        boolean flag = true;
        while(flag){
            flag = false;
            for(int j = 0; j < arr.length; j++){
                if(arr[j].compareTo(arr[j + 1]) > 0){
                    Comparable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
        }
    }
}
