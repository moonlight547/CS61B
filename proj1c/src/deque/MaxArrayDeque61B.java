package deque;

import java.util.*;

@SuppressWarnings("unchecked")

public class MaxArrayDeque61B <T> implements Deque61B<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private Comparator<T> comparator;


    public MaxArrayDeque61B(Comparator<T> c) {
        items = (T[]) new Object[1000];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        comparator = c;

    }

    @Override
    public void addFirst(T x) {
        checkResizeUp();
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size += 1;



    }

    @Override
    public void addLast(T x) {
        checkResizeUp();
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size += 1;


    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int p = Math.floorMod(nextFirst + 1, items.length);
        int i = 0;

        while(i < size ) {
            returnList.add(items[p]);

            p = Math.floorMod(p + 1, items.length);
            i++;
        }

        return  returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        checkResizeDown();

        int firstIndex = Math.floorMod(nextFirst + 1, items.length);

        T removedItem = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size--;

        return removedItem;
    }


    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        checkResizeDown();

        int lastIndex = Math.floorMod(nextLast - 1, items.length);
        T removedItem = items[lastIndex];
        items[lastIndex] =  null;
        nextLast = lastIndex;
        size--;

        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int firstElementIndex = Math.floorMod(nextFirst + 1 , items.length);
        int targetIndex = Math.floorMod(firstElementIndex + index , items.length);

        return items[targetIndex];

    }
    //resizing up
    private void checkResizeUp() {
        if (size == items.length) {
            resize(items.length * 2);
        }
    }

    //resizing down
    private void checkResizeDown() {
        if (items.length >= 16 && size <= items.length * 0.25  ) {
            resize(items.length / 2);
        }
    }

    private void resize(int newCapacity){

        T[] newItems = (T[]) new Object[newCapacity];

        int firstIndex = Math.floorMod(nextFirst + 1, items.length);

        for(int i = 0; i < size; i++) {
            newItems[i] = items[Math.floorMod(firstIndex + i, items.length)];

        }
        items = newItems;
        nextFirst = newItems.length- 1;
        nextLast = size;

    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T> {
        private int curr;
        private int count;
        public ArrayDeque61BIterator() {
            curr = Math.floorMod(nextFirst + 1, items.length);
            count = 0;
        }

        @Override
        public boolean hasNext() {
            if (count < size) {

                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (! hasNext()) {
                throw new NoSuchElementException();
            }

            T item = items[curr];
            curr = Math.floorMod(curr + 1, items.length);
            count ++;
            return  item;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque61B<?> other)) {
            return false;
        }

        if (this.size() != other.size() ) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if(!Objects.equals(this.get(i),other.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return toList().toString();
    }



    public T max() {
        if(isEmpty()) {
            return null;
        }
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        T max = get(0);
        for (int i = 1; i < size; i++) {
            T current = get(i);
            if (c.compare(current, max) > 0) {
                max = current;
            }
        }
        return max;
    }

}
