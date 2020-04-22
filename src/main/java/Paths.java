import java.util.Scanner;

public class Paths {

    public static final int MAX_LENGTH = 10000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int g = sc.nextInt();
        int[][] connects = new int[g][3];
        for(int i = 0; i < g; i++){
            connects[i][0] = sc.nextInt();
            connects[i][1] = sc.nextInt();
            connects[i][2] = sc.nextInt();
        }
        int[][] shortPaths = new int[n][n];
        for(int x = 0; x < n; x++){
            for(int y = 0; y < n; y++){
                if(x == y){
                    shortPaths[x][y] = 0;
                }
                else{
                    shortPaths[x][y] = MAX_LENGTH;
                }
                for(int i = 0; i < g; i++){
                    shortPaths[connects[i][0] - 1][connects[i][1] - 1] = connects[i][2];
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int x = 0; x < n; x++){
                for(int y = 0; y < n; y++){
                    shortPaths[x][y] = Math.min(shortPaths[x][y], shortPaths[x][i] + shortPaths[i][y]);
                }
            }
            print(shortPaths);
            System.out.println();
        }
    }

    public static void print(int[][] n){
        for(int x = 0; x < n.length; x++){
            for(int y = 0; y < n.length; y++){
                if(n[x][y] < MAX_LENGTH) {
                    System.out.printf("%-5d", n[x][y]);
                }
                else{
                    System.out.printf("%-5s","-");
                }
            }
            System.out.println();
        }
    }

}
