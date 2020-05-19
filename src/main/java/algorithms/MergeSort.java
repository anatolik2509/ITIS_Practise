package algorithms;

import java.util.Arrays;

public class MergeSort {
    public static int[] sort(int[] a) {
        int[] arr1 = a;
        int[] arr2 = new int[a.length];
        int step = 1;
        int count1 = 0;
        int count2 = 0;
        while(step < a.length){
            for(int i = 0; i < a.length; i += 2*step){
                for(int j = i; j < Math.min(i + step * 2, a.length); j++){
                    if(i + step + count2 >= a.length || count2 >= step){
                        arr2[j] = arr1[i + count1++];
                        continue;
                    }
                    if(i + count1 >= a.length || count1 >= step){
                        arr2[j] = arr1[i + step + count2++];
                        continue;
                    }
                    if(arr1[i + count1] < arr1[i + step + count2]){
                        arr2[j] = arr1[i + count1++];
                    }
                    else {
                        arr2[j] = arr1[i + step + count2++];
                    }
                }
                count1 = 0;
                count2 = 0;
            }
            step *= 2;
            count1 = 0;
            count2 = 0;
            arr1 = arr2;
            arr2 = new int[a.length];
        }
        return arr1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6,7,8,9};//[1, 2, 3, 3, 4, 5, 5, 5, 6, 6, 6, 8, 9]
        System.out.println(Arrays.toString(sort(a)));
    }
}
