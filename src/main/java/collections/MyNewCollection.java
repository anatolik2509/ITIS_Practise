package collections;

import data_structures.EndlessArray;
import data_structures.EndlessArrayIterator;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public class MyNewCollection<T> extends AbstractCollection<T> {

    private EndlessArray<T> data;

    public MyNewCollection(Collection<? extends T> c){
        data = new EndlessArray<>();
        Iterator<? extends T> iterator = c.iterator();
        while (iterator.hasNext()){
            data.add(iterator.next());
        }
    }

    public MyNewCollection(){
        data = new EndlessArray<>();
    }

    public MyNewCollection(int startCapacity){
        data = new EndlessArray<>(startCapacity);
    }

    @Override
    public boolean add(T t) {
        data.add(t);
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new EndlessArrayIterator<T>(data);
    }

    @Override
    public int size() {
        return data.getSize();
    }

    @Override
    public boolean remove(Object o) {
        Iterator<T> i = this.iterator();
        while(i.hasNext()){
            if(i.next().equals(o)){
                i.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MyNewCollection<?> that = (MyNewCollection<?>) o;
        if (that.data.getSize() != data.getSize()) return false;
        for (int i = 0; i < data.getSize(); i++) {
            if (!data.get(i).equals(that.data.get(i))) {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        MyNewCollection<Integer> collection = new MyNewCollection<>();
        System.out.println(collection.add(1));
        for(Integer i:collection){
            System.out.println(i);
        }
    }
}
