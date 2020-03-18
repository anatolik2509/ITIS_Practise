package collections;

import data_structures.EndlessArray;
import data_structures.EndlessArrayIterator;
import data_structures.EndlessArrayReverseIterator;

import java.util.*;

/**
 * My navigable set
 * @author Anatoly Antonov
 * @version 1.0
 * @param <T> Type of contains element
 */
public class MyNavigableSet<T> extends AbstractSet<T> implements NavigableSet<T> {

    private EndlessArray<T> array = new EndlessArray();

    private int size;

    private Comparator<? super T> comparator;

    private int direct;

    /**
     * <p>Constructor with comparator and num, that define direction</p>
     * @param comparator Comparator, that compare elements
     * @param direct 1, with direct order, -1 with reverse order
     */
    public MyNavigableSet(Comparator<? super T> comparator, int direct){
        this.array = new EndlessArray<T>();
        size = 0;
        direct = 1;
        this.comparator = comparator;
    }

    /**
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new EndlessArrayIterator<T>(array);
    }

    /**
     * @return Amount of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the greatest element less then given element or null if there no such file
     * @param t Element of set
     * @return Element standing behind t in order
     */
    @Override
    public T lower(T t) {
        int index = array.indexOf(t);
        if(index <= 0) return null;
        return array.get(index - 1);
    }

    /**
     * Returns the greatest element less then or equal to given element
     * @param t given element
     * @return result
     */
    @Override
    public T floor(T t) {
        T r = null;
        boolean flag = true;
        for(int i = 0; i < size && flag; i++){
            if(direct * comparator.compare(t, array.get(i)) > 0){
                flag = false;
            }
            else {
                r = array.get(i);
            }
        }
        return r;
    }

    /**
     * Returns the least element greater then or equal to given element
     * @param t given element
     * @return result
     */
    @Override
    public T ceiling(T t) {
        T r = null;
        boolean flag = true;
        for(int i = size - 1; i >= 0 && flag; i--){
            if(direct * comparator.compare(t, array.get(i)) < 0){
                flag = false;
            }
            else {
                r = array.get(i);
            }
        }
        return r;
    }

    @Override
    public T higher(T t) {
        int index = array.indexOf(t);
        if(index == size - 1 || index == -1) return null;
        return array.get(index + 1);
    }

    @Override
    public T pollFirst() {
        if(size == 0) return null;
        T r = array.get(0);
        array.remove(0);
        size--;
        return r;
    }

    @Override
    public T pollLast() {
        if(size == 0) return null;
        T r = array.get(size - 1);
        array.remove(size - 1);
        size--;
        return r;
    }

    @Override
    public NavigableSet<T> descendingSet() {
        EndlessArray<T> n = new EndlessArray<>();
        for(T e : array){
            n.add(e, 0);
        }
        MyNavigableSet<T> reverseSet = new MyNavigableSet<>(comparator, direct * -1);
        reverseSet.array = array;
        reverseSet.size = size;
        return reverseSet;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new EndlessArrayReverseIterator<>(array);
    }

    @Override
    public NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
        EndlessArray<T> n = new EndlessArray<>();
        for(int i = array.indexOf(fromElement) + (fromInclusive ? 0 : 1); i <= array.indexOf(toElement) - (toInclusive ? 0 : 1); i++){
            n.add(array.get(i));
        }
        MyNavigableSet<T> set = new MyNavigableSet<>(comparator, direct);
        set.array = array;
        set.size = size;
        return set;
    }

    @Override
    public NavigableSet<T> headSet(T toElement, boolean inclusive) {
        return subSet(first(), true, toElement, inclusive);
    }

    @Override
    public NavigableSet<T> tailSet(T fromElement, boolean inclusive) {
        return subSet(fromElement, inclusive, last(), true);
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        return headSet(toElement, false);
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return tailSet(fromElement, true);
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public T first() {
        return array.get(0);
    }

    @Override
    public T last() {
        return array.get(size - 1);
    }

    @Override
    public boolean add(T t) {
        for(int i = 0; i < size; i++){
            if(direct * comparator.compare(array.get(i),t) > 0){
                array.add(t, i);
                return true;
            }
            if(comparator.compare(array.get(i),t) == 0){
                return false;
            }
        }
        array.add(t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        T r = null;
        try{
            r = (T)o;
        }
        catch (ClassCastException e){
            return false;
        }
        int index = array.indexOf(r);
        if(index == -1){
            return false;
        }
        array.remove(index);
        size--;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        T r = null;
        try{
            r = (T)o;
        }
        catch (ClassCastException e){
            return false;
        }
        int index = array.indexOf(r);
        return index != -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MyNavigableSet<?> that = (MyNavigableSet<?>) o;
        if(that.size != size) return false;
        for(int i = 0; i < size; i++){
            if(!array.get(i).equals(that.array.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), array, size, comparator, direct);
    }
}
