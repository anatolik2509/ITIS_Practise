package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StrangeIterator<T> implements Iterator<T> {

    private T[] arr;
    private int cursor;

    public StrangeIterator(T[] arr) {
        this.arr = arr;
        cursor = 0;
    }

    @Override
    public boolean hasNext() {
        boolean flag = false;
        for(int i = cursor; i < arr.length && !flag; i += 2){
            if(arr[i] != null) flag = true;
        }
        return flag;
    }

    @Override
    public T next() {
        boolean flag = false;
        try {
            while (arr[cursor] == null) {
                cursor += 2;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new NoSuchElementException();
        }
        T r = arr[cursor];
        cursor += 2;
        return r;
    }

    public static void main(String[] args) {
        StrangeIterator<Integer> iterator = new StrangeIterator<>(new Integer[]{1, 2, 3, null, null, 4, 5, null, 7, 8, null});
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
