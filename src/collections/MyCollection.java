package collections;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public class MyCollection<T> extends AbstractCollection<T> {

    public static final int DEFAULT_CAPACITY = 0;

    private T[] data;

    public MyCollection(Collection<? extends T> c){
        data = (T[]) new Object[c.size()];
        Iterator<? extends T> iterator = c.iterator();
        int i = 0;
        while (iterator.hasNext()){
            data[i++] = iterator.next();
        }
    }

    public MyCollection(){
        data = (T[])new Object[DEFAULT_CAPACITY];
    }

    public MyCollection(int startCapacity){
        data = (T[])new Object[startCapacity];
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(data);
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MyCollection<?> that = (MyCollection<?>) o;
        if (that.data.length != data.length) return false;
        for (int i = 0; i < data.length; i++) {
            if (!data[i].equals(that.data[i])) {
                return false;
            }
        }
        return true;
    }
}
