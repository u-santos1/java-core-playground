package collections;

 


public class MyHashMap<K, V> {

    
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int size; 
    private static final int DEFAULT_CAPACITY = 16; 

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.buckets = new Node[DEFAULT_CAPACITY];
    }

    private int getBucketIndex(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode()) % buckets.length;
    }

    
    public void put(K key, V value){
        int index = getBucketIndex(key);

        Node<K, V> currentNode = buckets[index];

        if (currentNode == null){
            buckets[index] = new Node<>(key, value);
            size++;
            return;
        }
        
        Node<K, V> prevNode = null;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                currentNode.value = value;
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        prevNode.next = new Node<>(key, value); 
        size++;
    }

    
    public V get(K key) {
        return null;
    }

    public V remove(K key) {
        return null;
    }

    public int size() {
        return size;
    }
}
