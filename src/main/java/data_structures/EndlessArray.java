package data_structures;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Endless array of integers, which can contains undefined quantity of integers.
 * Available actions:
 * <ul>
 *     <li>Add element</li>
 *     <li>Get element</li>
 *     <li>Find index of element</li>
 *     <li>Remove element</li>
 * </ul>
 *
 * @author Antonov Anatoly
 * @version 1.0
 */

public class EndlessArray<T> implements Iterable<T>{

    public static final int DEFAULT_START_DIMENSION = 4;
    public static final int EXTEND_MULTIPLIER = 2;
    public static final int SHRINK_DIVIDER = 4;

    private T[] array;
    private int size;

    /**
     * <p>Class constructor without parameters</p>
     */
    public EndlessArray(){
        array = (T[])new Object[DEFAULT_START_DIMENSION];
        size = 0;
    }

    /**
     * <p>Class constructor with start dimension.</p>
     * @param startDimension start dimension.
     */
    public EndlessArray(int startDimension){
        array = (T[])new Object[startDimension];
        size = 0;
    }

    /**
     * <p>Endless array with elements from finite array.</p>
     * @param startArray array, with start elements.
     */
    public EndlessArray(T[] startArray){
        array = Arrays.copyOf(startArray, startArray.length);
        size = startArray.length;
    }

    /**
     * <p>Returns size of endless array.</p>
     * @return index of last element.
     */
    public int getSize() {
        return size;
    }

    private void extendArray(){
        T[] newArray = (T[])new Object[array.length * EXTEND_MULTIPLIER];
        for(int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private void shrinkArray(){
        if(size < array.length / 4) {
            T[] newArray =(T[]) new Object[array.length / SHRINK_DIVIDER];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    /**
     * <p>Add element to end of array.</p>
     * @param a element.
     */
    public void add(T a){
        if(size == array.length){
            extendArray();
        }
        array[size] = a;
        size++;
    }

    /**
     * <p>Add element to defined index.</p>
     * @param a element.
     * @param index index.
     * @exception IndexOutOfBoundsException No such index.
     */
    public void add(T a, int index){
        if(index <= size) {
            if (size == array.length) {
                extendArray();
            }
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = a;
            size++;
        }
        else{
            throw new IndexOutOfBoundsException("No " + index + " position");
        }
    }

    /**
     * <p>Return integer by index.</p>
     * @param index index of element you need.
     * @return element on index.
     */
    public T get(int index){
        if(index < size){
            return array[index];
        }
        else{
            throw new IndexOutOfBoundsException("No " + index + " element");
        }
    }

    /**
     * <p>Removes element on defined index.</p>
     * @param index index of element you need to remove.
     * @exception IndexOutOfBoundsException No such index.
     */
    public void remove(int index){
        if(index < size){
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[size - 1] = null;
            size--;
            shrinkArray();
        }
        else{
            throw new IndexOutOfBoundsException("No " + index + " element");
        }
    }

    /**
     * <p>Returns index of given element.</p>
     * @param element element of array.
     * @return index of given element or -1 if there's no such element.
     */
    public int indexOf(T element){
        for(int i = 0; i < size; i++){
            if(array[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }

    public static void main(String[] args) {
        EndlessArray<Integer> endlessArray = new EndlessArray<>();
        endlessArray.add(1);
        endlessArray.add(2);
        endlessArray.add(3);
        endlessArray.add(4);
        endlessArray.add(5);
        for (Integer el:endlessArray) {
            System.out.println(el);
        }
//        endlessArray.add(6,3);
//        endlessArray.remove(2);
//        for(int i = 0; i < endlessArray.getSize();i++){
//            System.out.println(endlessArray.get(i));
//        }
//        System.out.println(endlessArray.indexOf(5));
//        for(int i = 0; i < 1000; i++){
//            endlessArray.add(i);
//        }
//        System.out.println(endlessArray.getSize());
//        for(int i = 0; i < 1000; i++){
//            endlessArray.remove(0);
//        }
//        System.out.println(endlessArray.getSize());


    }

    @Override
    public Iterator<T> iterator() {
        return new EndlessArrayIterator<T>(this);
    }
}
