public class AList<Item> implements List61B<Item> {

    private Item[] items;
    private int size;

    public AList() {
        items = new int[100];
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        int[] a = new int[capacity];
        System.arraycopy(items, 0,a, 0,size);
        items = a;
    }
    }
}

    /** Inserts X into the back of the list. */
    public void addlast(int x) {
        if(size == items. length) {
            resize(size+1);

        items[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public int getlast() {
        return items[size-1];
    }

    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i];
    }

    /** Returns the number of the items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
     public int removeLast() {
         int x = getLast();
         size -= 1;
         return x;
     }




}
