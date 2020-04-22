package collections;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] arr;
    private int cursor;

    public ArrayIterator(T[] arr) {
        this.arr = arr;
        cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return arr.length > cursor;
    }

    @Override
    public T next() {
        return arr[cursor++];
    }

    @Override
    public void remove() {
        T[] newArr = (T[])new Object[arr.length - 1];
        for(int i = 0; i < newArr.length; i++){
            if(i < cursor - 1){
                newArr[i] = arr[i];
            }
            else {
                newArr[i] = arr[i + 1];
            }
        }
        cursor--;
    }
}
