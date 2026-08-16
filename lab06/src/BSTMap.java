import java.util.*;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K, V> {
    private int size;

    //Traverse the left subtree, print the current key,
    //then traverse the right subtree.
    public void printInOrder(Entry map) {
        if(map == null) {
            return;
        }

        printInOrder(map.left);
        System.out.println(map.key);
        printInOrder(map.right);

    }


    @Override
    //find key,if exit ,give value to it, if null, find the correct null site,
    // create new entry and connect the map increase size.
    public void put(K key, V value) {
        if (map == null) {
            map = new Entry(key, value, null, null);
            size = size + 1;
            return;
        }
        Entry current = map;
        Entry parent = null;
        while (current != null) {
            if (key.compareTo(current.key) == 0) {
                current.val = value;
                return;
            } else if (key.compareTo(current.key) < 0) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }
        if (key.compareTo(parent.key) < 0) {
            parent.left = new Entry(key, value, null, null);
        } else {
            parent.right = new Entry(key, value, null, null);
        }
        size = size + 1;


    }

    @Override
    //find key
    public V get(K key) {
        if (map == null) {
            return null;
        }
        Entry lookup = map.get(key);
        if (lookup == null) {
            return null;
        }
        return lookup.val;
    }

    @Override
    // return true if the map contains the given key.
    public boolean containsKey(K key) {
        if (map == null) {
            return false;
        }
        return map.get(key) != null;
    }

    @Override
    // return the number of key-value pairs.
    public int size() {
        return size;
    }

    @Override
    // remove all key-value pairs of the map.
    public void clear() {
        size = 0;
        map = null;

    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter();
    }

    /**
     * Keys and values are stored in a BST of Entry objects.
     * This variable stores the first pair in this BST.
     */
    private Entry map;

    /**
     * Represents one node in the BST that stores the key-value pairs
     * in the dictionary.
     */
    private class Entry {

        /**
         * Stores the key,value, left child, right child.
         */
        Entry(K k, V v, Entry n, Entry m) {
            key = k;
            val = v;
            left = n;
            right = m;
        }

        /**
         * Returns the Entry in this BST of key-value pairs whose key
         * is equal to KEY, or null if no such Entry exists.
         */
        Entry get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }
            if (k.compareTo(key) < 0) {
                if (left == null) {
                    return null;
                }
                return left.get(k);
            } else {
                if (right == null) {
                    return null;
                }
                return right.get(k);
            }
        }

        /**
         * Stores the key of the key-value pair of this node in the BST.
         */
        K key;
        /**
         * Stores the value of the key-value pair of this node in the BST.
         */
        V val;
        /**
         * Stores the left/right Entry in the BST.
         */
        BSTMap.Entry left;
        BSTMap.Entry right;
    }

    private class BSTMapIter implements Iterator<K> {
        List<K> keys = new ArrayList<>();
        int position;

        public BSTMapIter() {
            addKeysInOrder(map);
        }

        public void addKeysInOrder(Entry map) {
            if(map == null) {
                return;
            }
            addKeysInOrder(map.left);
            keys.add(map.key);
            addKeysInOrder(map.right);

        }

        @Override
        public boolean hasNext() {
            return position < keys.size();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int current = position;
            position++;
            return keys.get(current);

        }

    }


}
