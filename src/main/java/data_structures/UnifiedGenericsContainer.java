package data_structures;

public class UnifiedGenericsContainer<T1, T2> {
    private final T1 obj1;
    private final T2 obj2;

    public UnifiedGenericsContainer(T1 obj1, T2 obj2){
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public T1 getValue1() {
        return obj1;
    }

    public Class getType1(){
        return this.obj1.getClass();
    }

    public T2 getValue2() {
        return obj2;
    }

    public Class getType2(){
        return this.obj2.getClass();
    }

    public static void main(String[] args) {
        UnifiedGenericsContainer<Integer, String> container = new UnifiedGenericsContainer<>(6, "comparators.Apple");
        System.out.println(container.getValue1());
        System.out.println(container.getValue2().length());
    }

}
