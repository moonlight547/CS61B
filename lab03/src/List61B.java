public interface List61B<Item> {

    default public void print() {
        for (int i = 0; i < size(); i += 1 ) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
    new*
    public void insert(Item x, int position);
    new*
    public void addFirst(Item x);
    new*
    public void addLast(Item y);

    public Item getFirst();



    /** Returns the item from the back of the list. */
    public Item getLast();

    public Item removeLast();

    /** Gets the ith item in the list (0 is the front). */
    public Item  get(int i);

    /** Returns the number of the items in the list. */
    public int size() ;

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() ;
}
