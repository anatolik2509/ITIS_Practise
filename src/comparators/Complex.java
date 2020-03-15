package comparators;

public class Complex implements Number<Complex>{
    private int r;
    private int i;

    public Complex(int r, int i) {
        this.r = r;
        this.i = i;
    }

    public int getR() {
        return r;
    }

    public int getI() {
        return i;
    }

    @Override
    public Complex add(Complex o) {
        return new Complex(this.r + o.getR(), this.i + o.getI());
    }

    @Override
    public Complex subtract(Complex o) {
        return new Complex(this.r - o.getR(), this.i - o.getI());
    }

    @Override
    public Complex multiplyOnInt(int n) {
        return new Complex(this.r * n, this.i * n);
    }
}
