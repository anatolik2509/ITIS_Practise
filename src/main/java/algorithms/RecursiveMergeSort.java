package algorithms;

import java.util.Arrays;

public class RecursiveMergeSort {
    public static int[] sort(int[] a){
        return sortPart(a, 0, a.length);
    }

    private static int[] sortPart(int[] a, int b, int e){
        if(b >= e - 1) return new int[]{a[b]};
        int[] r = new int[e - b];
        int middle = (e + b) / 2;
        int[] arr1 = sortPart(a, b, middle);
        int[] arr2 = sortPart(a, middle, e);
        int count1 = 0;
        int count2 = 0;
        for(int i = 0; i < e - b; i++){
            if(count1 >= arr1.length){
                r[i] = arr2[count2++];
                continue;
            }
            if(count2 >= arr2.length){
                r[i] = arr1[count1++];
                continue;
            }
            if(arr1[count1] > arr2[count2]){
                r[i] = arr2[count2++];
            }
            else {
                r[i] = arr1[count1++];
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,6,4,5,3,6,8,3,9,5,2,6,5};
        System.out.println(Arrays.toString(sort(a)));
    }
}
