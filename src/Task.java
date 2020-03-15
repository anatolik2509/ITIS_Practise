import java.util.Arrays;
import java.util.Scanner;

public class Task {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[] numbers = new int[n];
        int[][] digits = new int[m][k];
        for(int i = 0; i < n; i++){
            numbers[i] = sc.nextInt();
        }
        for(int i = 0; i < m; i++){
            for (int j = 0; j < k; j++){
                digits[i][j] = sc.nextInt();
            }
        }
        int result = 0;
        for(int i = 0; i < (n - 1); i++){
            if(hasDigitsInLines(numbers[i], copyArray(digits)) && hasDigitsInColumns(numbers[i + 1], copyArray(digits))){
                result++;
            }
        }
        System.out.println(result);
    }

    public static boolean hasDigitsInLines(int num, int[][] digits){
        int n, d;
        boolean flag1, flag2, flag3;
        flag1 = false;
        for(int i = 0; i < digits.length; i++){
            n = num;
            flag2 = true;
            while(n != 0){
                d = n % 10;
                n /= 10;
                flag3 = false;
                for(int j = 0; j < digits[i].length; j++){
                    if(d == digits[i][j]){
                        flag3 = true;
                        digits[i][j] = -1;
                    }
                }
                flag2 = flag2 && flag3;
            }
            flag1 = flag1 || flag2;
        }
        return flag1;
    }

    public static boolean hasDigitsInColumns(int num, int[][] digits){
        int n, d;
        boolean flag1, flag2, flag3;
        flag1 = false;
        for(int i = 0; i < digits.length; i++){
            n = num;
            flag2 = true;
            while(n != 0){
                d = n % 10;
                n /= 10;
                flag3 = false;
                for(int j = 0; j < digits.length && !flag3; j++){
                    if(d == digits[j][i]){
                        flag3 = true;
                        digits[j][i] = -1;
                    }
                }
                flag2 = flag2 && flag3;
            }
            flag1 = flag1 || flag2;
        }
        return flag1;
    }

    public static int[][] copyArray(int[][] n){
        int[][] r = new int[n.length][n[0].length];
        for(int i = 0; i < n.length; i++){
            for(int j = 0; j < n[0].length; j++){
                r[i][j] = n[i][j];
            }
        }
        return r;
    }
}
