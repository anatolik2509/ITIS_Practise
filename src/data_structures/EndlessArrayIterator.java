package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EndlessArrayIterator<T> implements Iterator<T> {
    private EndlessArray<T> data;

    private int cursor;

    public EndlessArrayIterator(EndlessArray data){
        this.data = data;
        cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return data.getSize() > cursor;
    }

    @Override
    public T next() {
        try {
            T r = data.get(cursor);
            cursor++;
            return r;
        }
        catch (ArrayIndexOutOfBoundsException ex){
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        data.remove(cursor - 1);
        cursor--;
    }

//    public static void main(String[] args) {
//        String s[] = new String[]{null, "3", "1", "a", null, "b", "c", null, "kfdlhgsdixlhfrlksjhg", "dsfdsds", "d"};
//        ArrayIterator i = new ArrayIterator(s);
//        while(i.hasNext()){
//            System.out.println(i.next());
//        }
//    }
}
