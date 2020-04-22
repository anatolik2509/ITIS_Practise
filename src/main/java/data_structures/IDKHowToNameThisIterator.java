package data_structures;

import java.util.Iterator;

public class IDKHowToNameThisIterator<T> implements Iterator<T> {

    private T[] arr;
    private int cursor;

    public IDKHowToNameThisIterator(T[] arr) {
        this.arr = arr;
        cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return arr.length > cursor;
    }

    @Override
    public T next() {
        T r;
        if(cursor % 2 == 0){
            r = arr[cursor / 2];
        }
        else {
            r = arr[arr.length - 1 - cursor/2];
        }
        cursor++;
        return r;
    }

    public static void main(String[] args) {
        IDKHowToNameThisIterator<Integer> iterator = new IDKHowToNameThisIterator<Integer>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
