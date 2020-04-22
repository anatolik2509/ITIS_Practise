package data_structures;

import java.util.Arrays;
import java.util.Iterator;

public class Set<T> implements Iterable<T>{
    private EndlessArray<T> set;

    public Set(){
        set = new EndlessArray<>();
    }

    public boolean add(T e){
        if(!contains(e)){
            set.add(e);
            return true;
        }
        return false;
    }

    public boolean contains(T e){
        return set.indexOf(e) != -1;
    }

    public boolean remove(T e){
        if(contains(e)){
            set.remove(set.indexOf(e));
            return true;
        }
        else{
            return false;
        }
    }

    public int getSize(){
        return set.getSize();
    }

    public T[] toArray(){
        T[] r = (T[])new Object[getSize()];
        for(int i = 0; i < r.length; i++){
            r[i] = set.get(i);
        }
        return r;
    }

    public Set<T> merge(Set<T> another){
        Set r = new Set();
        for(T e : set){
            r.add(e);
        }
        for(T e : another.set){
            r.add(e);
        }
        return r;
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator<T>(set);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set<T> other = (Set<T>)o;
        if(this.set.getSize() != other.set.getSize()) return false;
        for(T t : this){
            if(!other.contains(t)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Set<Integer> set = new Set<>();
        System.out.println(set.add(1));
        System.out.println(set.add(2));
        System.out.println(set.add(3));
        System.out.println(set.add(4));
        System.out.println(set.add(5));
        System.out.println(set.add(5));
        System.out.println(set.contains(2));
        System.out.println(set.contains(6));
        set.remove(2);
        System.out.println(Arrays.toString(set.toArray()));
        Set<Integer> anotherSet = new Set<>();
        anotherSet.add(9);
        anotherSet.add(5);
        anotherSet.add(19);
        anotherSet.add(1);
        anotherSet.add(8);
        anotherSet.add(666);
        System.out.println(Arrays.toString(anotherSet.toArray()));
        System.out.println(Arrays.toString(set.merge(anotherSet).toArray()));
    }
}
