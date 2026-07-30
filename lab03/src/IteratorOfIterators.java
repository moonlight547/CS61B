import java.util.

public class IteratorOfIterators implements Iterator<Integer> {
    Linkedlist<Iterator<Integer>> iterators;

    public IteratorOfIterators(List<Iterator<Integer>>a) {
        iterators = new Linkedlist<>();

        for (Iterator<Integer> iterator : a) {
            if (iterator.hasNext()) {
                iterators.add(iterator);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !iterators.isEmpty();

    }

    @Override
    public Integer next() {
        if(! hasNext()) {
            throw new NoSuchElementException();
        }


        Iterator<Integer> interator = iterators.removeFirst();
        int ans = iterator.next();
        if(iterator.hasNext()) {
            iterators.addlast(iterator);
        }
        return ans;

    }

}
