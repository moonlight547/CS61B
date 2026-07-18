import net.sf.saxon.value.AnyExternalObject;

import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private class Node {
        T item;
        Node prev;
        Node next;
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node();
        newNode.item = x;
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;

    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node();
        newNode.item = x;
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel.next;

        while(p != sentinel){
            returnList.add(p.item);
            p = p.next;
        }


        return returnList;
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
        if(isEmpty()) {
            return null;
        }

        T removedItem = sentinel.next.item;

        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;

        return removedItem;

    }

    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }

        T removedItem = sentinel.prev.item;

        sentinel.prev = sentinel.prev.prev;
        sentinel.prev = sentinel;
        size--;

        return removedItem;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;

    }

    @Override
    public T getRecursive(int index)  {
        if (index < 0 || index >= size) {
            return null;
        }

        Node node = sentinel.next;
        return getRecursiveHelper(node,index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        else {
            return getRecursiveHelper(node.next, index - 1);
        }
    }
}


