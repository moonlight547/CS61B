import net.sf.saxon.om.Item;

public class SLList<Pineapple> implements List61B<Pineapple> {

    private class IntNode {
        public Pineapple item;
        public IntNode next;

        public  IntNode(Pineapple i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /* The first item, if it exists, is at sentinel.next. */
    private IntNode sentinel;
    private int size;

    /* Creates a new SLList with one item, namely x. */
    public SLList(Pineapple x) {
        sentinel = new IntNode(null,null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /* Adds item x to the front of the list. */
    public void addFirst(Pineapple x) {
        sentinel.next = new IntNode(x, sentinel.next);
         size += 1;
    }

    /* Create an empty SLList. */
    public SLList() {
        sentinel = new IntNode(null,null);
        size = 0;

    }

    /* Gets the first item in the list. */
    public Pineapple getFirst() {
        return sentinel.next.item;
    }

    /* Add x to the end of the list. */
    public void addlast(Pineapple x) {
        size += 1;
        IntNode p = sentinel;



        /* Scan p until it reaches the end of the list. */
        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x, null);


    }

    /* Returns the size of the list. */
    public int size() {
        return size;

    }

    /* Returns the size of the list,starting at IntNode p. */
//    private int size(IntNode p) {
//        if (p.next == null) {
//            return 1;
//        }
//        return 1 + size(p.next);
//    }





    public static  void main(String[] args) {
        SLList<String> L = new SLList<String>();
        L.addFirst("what");
        L.addFirst("the");
        L.addFirst("dog");
        L.addFirst("doin");

        System.out.println(L.getFirst()) ;
    }


}
