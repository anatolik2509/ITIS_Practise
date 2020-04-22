package data_structures;

import collections.MyNewCollection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EndlessArrayReverseIterator<T> implements Iterator<T> {
    private EndlessArray<T> data;

    private int cursor;

    public EndlessArrayReverseIterator(EndlessArray data){
        this.data = data;
        cursor = data.getSize() - 1;
    }

    @Override
    public boolean hasNext() {
        return cursor >= 0;
    }

    @Override
    public T next() {
        try {
            T r = data.get(cursor);
            cursor--;
            return r;
        }
        catch (ArrayIndexOutOfBoundsException ex){
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        data.remove(cursor + 1);
    }


}
