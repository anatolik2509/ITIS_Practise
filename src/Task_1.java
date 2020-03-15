public class Task_1 {
    public static void main(String[] args) {
        int[] a = new int[]{6, 10, 2, 7, 5, 5, 8, 2, 3, 4, 3, 11, 5};
        int k = 0;
        int k1 = 0;
        int max = (int)(Math.pow(2, a.length));
        int sum = 0;
        while(k < max){
            k1 = k;
            for(int i : a){
                if(k1 % 2 == 1){
                    sum += i;
                }
                k1 /= 2;
            }
            if(sum == 15){
                k1 = k;
                for(int i = 0; i < a.length; i++){
                    System.out.print(k1 % 2);
                    k1 /= 2;
                }
                System.out.println();
            }
            k++;
            sum = 0;
        }
    }
}
