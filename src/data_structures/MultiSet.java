package data_structures;

import java.util.Arrays;
import java.util.Iterator;

public class MultiSet<T> implements Iterable<T>{
    private EndlessArray<T> set;
    private EndlessArray<Integer> amounts;

    public MultiSet(){
        set = new EndlessArray<>();
        amounts = new EndlessArray<>();
    }

    public void add(T e){
        int i = set.indexOf(e);
        if(i == -1){
            set.add(e);
            amounts.add(1);
        }
        else {
            int r = amounts.get(i);
            amounts.remove(i);
            amounts.add(++r,i);
        }
    }

    public boolean contains(T e){
        return set.indexOf(e) != -1;
    }

    public boolean remove(T e){
        int i = set.indexOf(e);
        if(i == -1){
            return false;
        }
        else {
            int r = amounts.get(i) - 1;
            amounts.remove(i);
            if(r == 0){
                set.remove(i);
            }
            else {
                amounts.add(r, i);
            }
            return true;
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

    public MultiSet<T> merge(MultiSet<T> another){
        MultiSet r = new MultiSet();
        for(int i = 0; i < set.getSize(); i++){
            for(int j = 0; j < amounts.get(i); i++) {
                r.add(set.get(i));
            }
        }
        for(int i = 0; i < another.set.getSize(); i++){
            for(int j = 0; j < another.amounts.get(i); i++) {
                r.add(another.set.get(i));
            }
        }
        return r;
    }

    public int getAmount(T e){
        int index = set.indexOf(e);
        if(index == -1){
            return 0;
        }
        return amounts.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator<T>(set);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiSet<T> other = (MultiSet<T>)o;
        if(this.set.getSize() != other.set.getSize()) return false;
        for(int i = 0; i < set.getSize(); i++){
            if(!(other.contains(set.get(i)) && this.getAmount(set.get(i)) == this.getAmount(set.get(i)))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MultiSet<Integer> set = new MultiSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(5);
        set.contains(2);
        set.contains(6);
        set.remove(2);
        System.out.println(set.remove(33));
        System.out.println(Arrays.toString(set.toArray()));
        System.out.println(set.set);
        System.out.println(set.amounts);
        MultiSet<Integer> anotherSet = new MultiSet<>();
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
