import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private class HashEntry<K, V> {
        K key;
        V value;
        HashEntry next;

        HashEntry(K k, V v) {
            key = k;
            value = v;
            next = null;
        }
    }

    private int numBucket;
    private int numItem;
    private double loadF;

    private ArrayList<HashEntry<K, V>> table;

    public MyHashMap(int initialSize, double loadFactor) {
        numBucket = initialSize;
        loadF = loadFactor;
        table = new ArrayList<>(initialSize);
        for (int i = 0; i < numBucket; i++) {
            table.add(null);
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        table = new ArrayList<>(table.size());
        for (int i = 0; i < numBucket; i++)
            table.add(null);
        numItem = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException();
        return get(key) != null;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % numBucket;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException();
        int bucketIndex = hash(key);
        HashEntry<K, V> head = table.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key))
                return table.get(bucketIndex).value;
            head = head.next;
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return numItem;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException();
        int bucket = hash(key);
        HashEntry<K, V> head = table.get(bucket);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        numItem++;
        head = table.get(bucket);
        HashEntry<K, V> newE = new HashEntry<>(key, value);
        newE.next = head;
        table.set(bucket, newE);

        if (1.0 * numItem / numBucket > loadF)
            expand();
    }

    private void expand() {
        ArrayList<HashEntry<K, V>> temp = table;
        table = new ArrayList<>(2 * numBucket);
        numBucket = 2 * numBucket;
        for (int i = 0; i < numBucket; i++)
            table.add(null);
        for (HashEntry<K, V> node : temp) {
            while (node != null) {
                int bucket = hash(node.key);
                HashEntry<K, V> head = table.get(bucket);
                while (head != null)
                    head = head.next;
                head = table.get(bucket);
                HashEntry<K, V> newE = new HashEntry<>(node.key, node.value);
                newE.next = head;
                table.set(bucket, newE);
                node = node.next;
            }
        }
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>(numItem);
        for (HashEntry<K, V> node : table) {
            while (node != null) {
                keys.add(node.key);
                node = node.next;
            }
        }
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        int bucket = hash(key);
        HashEntry<K, V> head = table.get(bucket);
        HashEntry<K, V> prev = head;
        while (head != null && !head.key.equals(key)) {
            prev = head;
            head = head.next;
        }
        if (head == null)
            return null;
        else {
            V result = head.value;
            numItem--;
            prev.next = head.next;
            return result;
        }
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        int bucket = hash(key);
        HashEntry<K, V> head = table.get(bucket);
        HashEntry<K, V> prev = head;
        while (head != null && !head.key.equals(key)) {
            prev = head;
            head = head.next;
        }
        if (head == null || head.value != value)
            return null;
        else {
            V result = head.value;
            numItem--;
            prev.next = head.next;
            return result;
        }
    }

    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
