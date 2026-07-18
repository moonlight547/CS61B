public class HoFDemo {
    public static int do10xTimes(IntUnaryFunction f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        System.out.println(do10xTimes(new TenX(),2));
    }
}
