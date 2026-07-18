public class RotatingSLList<Item> extends SLList<Item> {

    public void rotateRight() {
        Item x = removeLast();
        addFirst(x);
    }

    public static void main(String[] args) {
        RotatingSLList<Integer> rsl = new RotatingSLList<>();
        /* Creates SList: [10, 11, 12, 13]*/
        rsl.addlast(10);
        rsl.addlast(11);
        rsl.addlast(12);
        rsl.addlast(13);


        /* Should be: [13, 10, 11, 12] */
        rsl.rotateRight();
        rsl.print();
    }
}
