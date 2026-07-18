import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ArraySet<T> implements Iterable<T>{
    private T[] items;
    private int size; // The next item to be added will be the position size;

    /*return an iterator(seer) to ME */
    public Iterator<T> iterator{
        return new ArraySetIterator();
    }
    public class ArraySetIterator implement Iterator<T> {
        private int wizPos;
        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

       public T next {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if the map contains a mapping for the specified key */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i] == null) {
                if ( x == null) {
                    return true;
                }
            }
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in the map.
    Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            return;
            throw new IllegalArgumentException("can't be null");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
    }
    /* Also to do:
    1. Implement a toString method.
    2.Implement an equals() method.
    3. Make ArraySet implement the Iterable<T> interface.
     */

    @Override
    public String toString() {
        StringBuilder returnSB =  new StringBuilder("{");

        for ( int i = 0; i < size - 1; i += 1) {
            returnSB.append(items[i].toString());
            returnSB.append(",");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();

    }

    public static void main(String[] args) {
        Set<Integer> javaset = new HashSet<>();
        javaset.add(5);
        javaset.add(23);
        javaset.add(42);

        Iterator<Integer> seer = javaset.iterator();

        while (seer.hasNext()) {
            int i = seer.next();
        }
        System.out.println(i);


        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        Iterator<Integer> aseer = aset.iterator();
        while(aseer.hasNext()) {
            int i = aseer.next();
            System.out.println(i);
        }

        for (int i : aset) {
            System.out.println(i);

        }
    }


    @Override
    public boolean equals(Object o) {
        if ( o interfaceof ArraySet oas) {
            // check sets are of the same size
            if(oas.size != this.size){
                return false;
            }
            // check that all of MY items are in the other array set
            for (T x :this) {
                if (!oas.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        // o is not an arrayset, so return false
        return false;
    }

    @Override
    public String toString() {
        List<String> listofItems = new Arraylist<>();
        for( T x : this) {
            listofItems.add(x.toString());
        }
        return "{" + String.join(", " , listofItems) + "}";
    }

    public static <Glerp> ArraySet<Glerp> of (Glerp...stuff) {
        ArraySet<Glerp> returnSet = new ArraySet<Glerp>();
        for ( Glerp x : stuff) {
            returnSet.add(x);
        }
        return returnSet;
    }

    ArraySet<String> asetofStrings = ArraySet.of("hi", "I'm", "here");
    System.out.println(asetofStrings);







}
