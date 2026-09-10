package hashmap;

import java.util.*;


/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private int size;

    @Override
    public void put(K key, V value) {
        int index;
        Collection<Node> bucket;
        index = Math.floorMod(key.hashCode() , buckets.length);
        bucket = buckets[index];
        for (Node node : bucket) {
            if(node.key.equals(key)) {
                node.value = value;
                return;
            }

        }
        bucket.add(new Node(key, value)) ;
        size++;

        if((double)size /  buckets.length > loadFactor) {
            resize();
        }

    }

    @Override
    public V get(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        Collection<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if(node.key.equals(key)) {
                return node.value;
            }
        }
        return null;

    }

    @Override
    public boolean containsKey(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        Collection<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if(node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length ; i++) {
            buckets[i].clear();
        }

        size = 0;

    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }


    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private double loadFactor;



    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);

    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        buckets = new Collection[initialCapacity];
        this.loadFactor = loadFactor;
        size = 0;

        for(int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
            }

    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new ArrayDeque<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    private void resize() {
        Collection<Node>[] currentBuckets = buckets;
        buckets = new Collection[buckets.length * 2];
        for(int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }

        for (int i = 0; i < currentBuckets.length; i++) {
            for(Node node : currentBuckets[i]) {
                int index = Math.floorMod(node.key.hashCode(), buckets.length);
                Collection<Node> bucket = buckets[index];
                bucket.add(node);
            }

        }

    }

}
