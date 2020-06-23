import java.util.*;

public class MyTrieSet implements TrieSet61B {
    private static final int R = 256;
    private Node root;

    private class Node {
        private char value;
        private boolean isKey;
        private Map<Character, Node> next = new HashMap<>();

        private Node(char v, boolean i) {
            value = v;
            isKey = i;
        }

        private Node() {
            value = '\0';
            isKey = false;
        }
    }

    public MyTrieSet() {
        root = new Node();
    }


    /**
     * Clears all items out of Trie
     */
    public void clear() {
        root = null;
    }

    /**
     * Returns true if the Trie contains KEY, false otherwise
     */

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() == 0 || root == null)
            return false;
        Node p = root;
        for (int i = 0; i < key.length(); i++) {
            char current = key.charAt(i);
            Node next = p.next.get(current);
            if (next == null) {
                return false;
            }
            p = next;
        }
        return p.isKey;
    }

    /**
     * Inserts string KEY into Trie
     */
    @Override
    public void add(String key) {
        if (key == null || key.length() == 0 || root == null) {
            return;
        }
        Node p = root;
        for (int i = 0; i < key.length(); i++) {
            char current = key.charAt(i);
            if (!p.next.containsKey(current)) {
                p.next.put(current, new Node(current, false));
            }
            p = p.next.get(current);
        }
        p.isKey = true;
    }

    /**
     * Returns a list of all words that start with PREFIX
     */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0 || root == null) {
            throw new NoSuchElementException();
        }
        List<String> result = new ArrayList<>();
        Node p = root;
        // Get to the branch with such a prefix
        for (int i = 0; i < prefix.length(); i++) {
            Node childNode = p.next.get((prefix.charAt(i)));
            if (childNode == null) {
                throw new NoSuchElementException();
            } else {
                p = childNode;
            }
        }
        // P is now at the branch, scan every branch of p to get the list of words
        if (p.isKey) {
            result.add(prefix);
        }
        for (Node i : p.next.values()) {
            if (i != null) {
                keysWithPrefix(result, prefix, i);
            }
        }
        return result;
    }

    private void keysWithPrefix(List<String> r, String p, Node n) {
        if (n.isKey) {
            r.add(p + n.value);
        }
        for (Node i : n.next.values()) {
            if (i != null) {
                keysWithPrefix(r, p + n.value, i);
            }
        }
    }

    /**
     * Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        if (key == null || key.length() == 0 || root == null) {
            throw new NoSuchElementException();
        }
        StringBuilder longestPrefix = new StringBuilder();
        Node p = root;
        for (int i = 0; i < key.length(); i++) {
            Node current = p.next.get(key.charAt(i));
            if (current != null) {
                longestPrefix.append(current.value);
                p = current;
            }
        }
        return longestPrefix.toString();
    }
}
