package algorithms;

import java.util.Arrays;

public class Karacuba {
    public static boolean[] multiply(boolean[] a, boolean[] b){
        if(a.length == 1 && b.length == 1) return new boolean[]{a[0] && b[0]};
        int x = Math.max(a.length, b.length) / 2;
        boolean[] bx = x <= a.length ? Arrays.copyOf(a, x) : Arrays.copyOf(a, a.length);
        boolean[] ax = x < a.length ? Arrays.copyOfRange(a, x, a.length) : new boolean[]{false};
        boolean[] dx = x <= b.length ? Arrays.copyOf(b, x) : Arrays.copyOf(b, b.length);
        boolean[] cx = x < b.length ? Arrays.copyOfRange(b, x, b.length) : new boolean[]{false};
        boolean[] m1 = multiply(sum(ax, bx), sum(cx, dx));
        boolean[] m2 = multiply(ax, cx);
        boolean[] m3 = multiply(bx, dx);
        boolean[] m4 = sub(m1, m2);
        m4 = sub(m4, m3);
        boolean[] s1 = m3;
        boolean[] s2 = new boolean[x + m4.length];
        for(int i = 0; i < s2.length; i++){
            if(i < x){
                s2[i] = false;
            }
            else {
                s2[i] = m4[i - x];
            }
        }
        boolean[] s3 = new boolean[2 * x + m2.length];
        for(int i = 0; i < s3.length; i++){
            if(i < x * 2){
                s3[i] = false;
            }
            else {
                s3[i] = m2[i - x * 2];
            }
        }
        boolean[] r = sum(s1, sum(s2, s3));
        return r;
    }

    private static boolean[] sum(boolean[] a, boolean[] b){
        int n = Math.max(a.length, b.length);
        boolean[] c = new boolean[n + 1];
        boolean plus = false;
        boolean temp;
        for(int i = 0; i < c.length; i++){
            if(i < Math.min(a.length, b.length)) {
                c[i] = (!a[i] && b[i]) || (a[i] && !b[i]);
                c[i] = (!c[i] && plus) || (!plus && c[i]);
                plus = (a[i] && b[i]) || (a[i] && plus) || (b[i] && plus);
            }
            else if(i >= a.length && i >= b.length){
                c[i] = plus;
            }
            else if(i >= a.length){
                temp = b[i] && plus;
                c[i] = (!b[i] && plus) || (!plus && b[i]);;
                plus = temp;
            }
            else if(i >= b.length){
                temp = a[i] && plus;
                c[i] = (!a[i] && plus) || (!plus && a[i]);;
                plus = temp;
            }
        }
        if(!c[c.length - 1]){
            c = Arrays.copyOf(c, c.length - 1);
        }
        return c;
    }

    private static boolean[] sub(boolean[] a, boolean[] b){
        int n = a.length;
        boolean[] c = new boolean[n];
        boolean minus = false;
        for(int i = 0; i < c.length; i++){
            if(i < Math.min(a.length, b.length)) {
                c[i] = (!a[i] && b[i]) || (a[i] && !b[i]);
                c[i] = (!c[i] && minus) || (!minus && c[i]);
                minus = (!a[i] && b[i]) || (!a[i] && minus) || (b[i] && minus);
            }
            else if(i >= b.length){
                c[i] = (!a[i] && minus) || (a[i] && !minus);
                minus = !a[i] && minus;
            }
        }
        int i = c.length - 1;
        while (!c[i] && i > 0){
            i--;
        }
        c = Arrays.copyOf(c, i + 1);
        return c;
    }

    private static boolean[] intToBoolean(int a){
        int n = 0;
        int b = a;
        while(b > 0){
            n++;
            b /= 2;
        }
        boolean[] r = new boolean[n];
        for(int i = 0; i < n; i++){
            r[i] = a % 2 == 1;
            a /= 2;
        }
        return r;
    }

    private static int booleanToInt(boolean[] b){
        int r = 0;
        int p = 1;
        for(int i = 0; i < b.length; i++){
            if(b[i]){
                r += p;
            }
            p *= 2;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(booleanToInt(multiply(intToBoolean(58), intToBoolean(26))));
    }
}
