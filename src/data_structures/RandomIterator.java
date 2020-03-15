package data_structures;

import java.util.Iterator;
import java.util.Random;

public class RandomIterator<T> implements Iterator<T> {
    private T[] arr;
    private Random random;

    public RandomIterator(T[] arr, int seed) {
        this.arr = arr;
        random = new Random(seed);
    }

    public RandomIterator(T[] arr) {
        this.arr = arr;
        random = new Random();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return arr[random.nextInt(arr.length)];
    }

    public static void main(String[] args) {
        RandomIterator<Integer> iterator = new RandomIterator<Integer>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        for(int i = 0; i < 10; i++){
            System.out.println(iterator.next());
        }
    }
}
