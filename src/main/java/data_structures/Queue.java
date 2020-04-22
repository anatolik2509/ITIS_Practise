package data_structures;

public class Queue <T>{
    public static final int DEFAULT_START_DIMENSION = 4;
    public static final int EXTEND_MULTIPLIER = 2;
    public static final int SHRINK_DIVIDER = 4;

    private T[] array;
    private int size;
    private int startPos;

    public Queue(){
        array = (T[]) new Object[DEFAULT_START_DIMENSION];
        size = 0;
        startPos = 0;
    }

    private void extendArray(){
        T[] newArray = (T[])new Object[array.length * EXTEND_MULTIPLIER];
        for(int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private void shrinkArray(){

        T[] newArray =(T[]) new Object[array.length / SHRINK_DIVIDER];
        for (int i = startPos; i < size; i++) {
            newArray[i - startPos] = array[i];
        }
        array = newArray;
        size -= startPos;
        startPos = 0;

    }

    public void add(T e){
        if(size == array.length){
            extendArray();
        }
        array[size] = e;
        size++;
    }

    public T pop(){
        T r = array[startPos];
        startPos++;
        if(size - startPos + 1 < array.length / SHRINK_DIVIDER){
            shrinkArray();
        }
        return r;
    }

    public T peek(){
        return array[0];
    }

    public int size(){
        return size - startPos;
    }

    public static void main(String[] args) {
        Queue<Integer> stack = new Queue<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        for(int i = 0; i < 1000; i++){
            stack.add(i);
        }
        System.out.println(stack.size());
        for(int i = 0; i < 1000; i++){
            stack.pop();
        }
        System.out.println(stack.size());
    }
}
