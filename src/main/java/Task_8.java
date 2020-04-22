import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Task_8 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter wr = new PrintWriter(System.out);
        in.nextToken();
        int n = (int)in.nval;
        Pair pairs[] = new Pair[n];
        int a[] = new int[n];
        int b;
        int sum = 0;
        int minSum = n;
        int x;
        int y;
        for(int i = 0; i < n; i++){
            in.nextToken();
            x = (int)in.nval;
            in.nextToken();
            y = (int)in.nval;
            pairs[i] = new Pair(x, y);
        }
        Arrays.sort(pairs, Comparator.comparingInt(Pair::getB));
        for(int i = 0; i < n; i++){
            b = i;
            if(pairs[i].getB() - pairs[i].getP() < pairs[0].getB() && i != 0){
                a[i] = - sum + i;
            }
            else {
                while (b > 0 && pairs[b - 1].getB() >= pairs[i].getB() - pairs[i].getP()) {
                    b--;
                    a[i] -= a[b];
                }
                a[i] += i - b;
            }
            sum += a[i];
            if(sum + n - i - 1 < minSum){
                minSum = sum + n - i - 1;
            }
        }
        System.out.println(minSum);
    }
    private static class Pair{
        int b;
        int p;

        public Pair(int r, int n) {
            this.b = r;
            this.p = n;
        }

        public int getB() {
            return b;
        }

        public int getP() {
            return p;
        }
    }
}
