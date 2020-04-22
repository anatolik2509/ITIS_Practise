import java.io.*;

public class Task_9 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter wr = new PrintWriter(System.out);
        in.nextToken();
        int n = (int)in.nval;
        int beacons[] = new int[n];
        int powers[] = new int[n];
        int a[] = new int[n];
        int b;
        int sum = n;
        int lastSum = n + 1;
        for(int i = 0; i < n; i++) {
            in.nextToken();
            beacons[i] = (int) in.nval;
            in.nextToken();
            powers[i] = (int) in.nval;
        }
        int y = 1;
        int minSum = n;
        int l = 0;
        while(y < n && beacons[n - y] - powers[n - y] <= beacons[0]) y++;
        while (y - 1 < minSum) {
            sum = y - 1;
            int x = beacons[n - 1] + 1;
            for (int i = n - y; i >= 0; i--) {
                if (x > beacons[i]) {
                    x = beacons[i] - powers[i];
                }
                else{
                    sum++;
                }
            }
            if(minSum > sum) minSum = sum;
            y++;

        }
        System.out.println(minSum);
    }
}
