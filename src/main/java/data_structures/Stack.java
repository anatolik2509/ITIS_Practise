package data_structures;

public class Stack <T>{

    public static final int DEFAULT_START_DIMENSION = 4;
    public static final int EXTEND_MULTIPLIER = 2;
    public static final int SHRINK_DIVIDER = 4;

    private T[] array;
    private int size;

    public Stack(){
        array = (T[]) new Object[DEFAULT_START_DIMENSION];
        size = 0;
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

    public void add(T e){
        if(size == array.length){
            extendArray();
        }
        array[size] = e;
        size++;
    }

    public T pop(){
        if(size == 0){
            return null;
        }
        size--;
        T r = array[size];
        array[size] = null;
        if(size < array.length / SHRINK_DIVIDER){
            shrinkArray();
        }
        return r;
    }

    public T peek(){
        return array[size - 1];
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
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
