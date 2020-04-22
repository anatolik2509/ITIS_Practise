import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Task_7 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter wr = new PrintWriter(System.out);
        in.nextToken();
        int n = (int)in.nval;
        String messages[] = new String[n];
        String s;
        for(int i = 0; i < n; i++){
            in.nextToken();
            s = in.sval;
            messages[i] = s;
        }
        HashSet<String> set = new HashSet<>();
        for(int i = messages.length - 1; i >= 0; i--){
            if(!set.contains(messages[i])){
                wr.println(messages[i]);
                set.add(messages[i]);
            }
        }
        wr.flush();
    }


}
