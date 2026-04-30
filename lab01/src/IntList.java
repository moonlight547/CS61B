public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public int get(int i) {
        IntList p = this;

        while (i >0) {
            p = p.rest;
            i = i - 1;
        }
        return p.first;
    }
    public static void main (String[] args) {
        IntList L = new IntList(15,null);
        L = new IntList (10,L);
        L = new IntList (5,L);

        System.out.println("Item at index 0:"+L.get(0));
        System.out.println("Item at index 1:"+L.get(1));
        System.out.println("Item at index 2:"+L.get(2));
    }
}


