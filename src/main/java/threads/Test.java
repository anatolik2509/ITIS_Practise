package threads;

public class Test {
    static Counter c = new Counter();
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Run());
        Thread thread2 = new Thread(new Run());
        thread1.start();
        thread2.start();
    }

    private static class Run implements Runnable {

        @Override
        public void run() {
            while(true) {
                synchronized (c) {
                    int a = c.get();
                    a++;
                    c.set(a);
                }
                System.out.println(c.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Counter{
        private int count = 0;
        public int get(){
            return count;
        }
        public void set(int a){
            count = a;
        }
    }
}
