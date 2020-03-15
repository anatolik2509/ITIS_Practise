package data_structures;

import java.util.Iterator;

public class SetIterator<T> implements Iterator<T> {

    EndlessArray<T> data;
    int cursor;

    public SetIterator(EndlessArray<T> data){
        this.data = data;
        cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return data.getSize() > cursor;
    }

    @Override
    public T next() {
        return data.get(cursor++);
    }
}
