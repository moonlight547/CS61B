
public class PassByValueFigure {
    public static void main(String[] args) {
        Walrus walrus = new Walrus(3500, 10.5);
        //new int[]{9}
        int[] hackX = new int[]{9};


        doStuff(walrus, hackX);
        System.out.println(walrus.weight);
        System.out.println(hackX[0]);
    }

    public static void doStuff(Walrus W, int[] arr ) {
        W.weight = W.weight - 100;
        arr[0] = arr[0] - 5;
    }
}