import java.util.Arrays;
import java.util.Random;

public class Sum {
    public static void execute(){
        Random r = new Random();
        int n = r.nextInt(5) + 5;
        int[] digits = new int[n];
        boolean[] use = new boolean[n];
        for(int i = 0; i < n; i++){
            digits[i] = r.nextInt(10) + 1;
        }
        int k = r.nextInt(10) + 10;
        Arrays.sort(digits);
        int sum = 0;
        System.out.println(Arrays.toString(digits));
        System.out.println(k);
        int limit = 100;
        int iter = 0;
        while(sum != k && iter < limit) {
            for (int i = 0; i < n && sum != k; i++) {
                if (digits[i] <= k - sum && !use[i]) {
                    use[i] = true;
                    sum += digits[i];
                } else {
                    boolean flag = false;
                    for (int j = 0; j < i && !flag; j++) {
                        if (digits[i] - digits[j] <= k - sum && use[j]) {
                            flag = true;
                            use[j] = false;
                            use[i] = true;
                            sum -= digits[j];
                            sum += digits[i];
                        }
                    }
                    if (!flag) {
                        flag = false;
                        for (int j = 0; j < n && !flag; j++) {
                            if (use[j]) {
                                use[j] = false;
                                sum -= digits[j];
                                flag = true;
                            }
                        }
                    }
                }
            }
            iter++;
        }
        for(int i = 0 ; i < n; i++){
            if(use[i]){
                System.out.print(digits[i] + " ");
            }
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        for(int i = 0; i < 20; i++){
            execute();
        }
    }
}
