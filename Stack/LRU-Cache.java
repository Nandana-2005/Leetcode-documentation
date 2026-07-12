/*
==========================================================
                    LRU CACHE
==========================================================

Problem:
Design a data structure that follows the constraints of
a Least Recently Used (LRU) Cache.

Implement:

1. LRUCache(int capacity)
2. int get(int key)
3. void put(int key, int value)

Both operations must run in O(1).

----------------------------------------------------------
Intuition

We need two operations:

1. Search a key in O(1)
2. Remove/Insert the most recently used node in O(1)

Use:

1. HashMap
   Key  -> Node

2. Doubly Linked List

Most Recently Used  <------>  Least Recently Used

head <-> ... <-> ... <-> tail

head.next  = Most Recently Used

tail.prev  = Least Recently Used

----------------------------------------------------------
Approach

HashMap + Doubly Linked List

----------------------------------------------------------
Time Complexity

get()  : O(1)
put()  : O(1)

Space Complexity

O(Capacity)

==========================================================
*/

import java.util.HashMap;

public class LRUCache {

    class Node {

        int key;
        int value;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;

    private final HashMap<Integer, Node> map;

    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {

        this.capacity = capacity;

        map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    // ==========================================================
    // GET
    // ==========================================================

    public int get(int key) {

        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);

        remove(node);
        insert(node);

        return node.value;
    }

    // ==========================================================
    // PUT
    // ==========================================================

    public void put(int key, int value) {

        if (map.containsKey(key)) {

            Node node = map.get(key);

            node.value = value;

            remove(node);
            insert(node);

            return;
        }

        if (map.size() == capacity) {

            Node lru = tail.prev;

            remove(lru);

            map.remove(lru.key);
        }

        Node newNode = new Node(key, value);

        insert(newNode);

        map.put(key, newNode);
    }

    // ==========================================================
    // REMOVE NODE
    // ==========================================================

    private void remove(Node node) {

        Node previous = node.prev;
        Node nextNode = node.next;

        previous.next = nextNode;
        nextNode.prev = previous;
    }

    // ==========================================================
    // INSERT AFTER HEAD
    // ==========================================================

    private void insert(Node node) {

        Node first = head.next;

        node.next = first;
        node.prev = head;

        head.next = node;
        first.prev = node;
    }

    // ==========================================================
    // DRIVER
    // ==========================================================

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        System.out.println(cache.get(1)); // 1

        cache.put(3, 3);

        System.out.println(cache.get(2)); // -1

        cache.put(4, 4);

        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
    }
}
