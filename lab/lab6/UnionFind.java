public class UnionFind {

    private int size;
    private int[] numbers;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        size = n;
        numbers = new int[n];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = -1;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex > size || vertex < 0)
            throw new ArithmeticException("not a valid index.");
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        if (numbers[v1] < 0)
            return -numbers[v1];
        else
            return sizeOf(parent(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return numbers[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (parent(v1) == v2 || parent(v2) == v1)
            return true;
        if (parent(v1) < 0 || parent(v2) < 0)
            return false;
        if (parent(v1) == parent(v2))
            return true;
        return connected(parent(v1), v2) || connected(v1, parent(v2));
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int v1Root = find(v1);
        int v2Root = find(v2);
        if (connected(v1, v2)) // path compression update
            numbers[v1] = v1Root;
        else if (sizeOf(v1) <= sizeOf(v2)) {
            numbers[v1Root] = v2Root;
            numbers[v2Root] -= v1Root;
        } else {
            numbers[v2Root] = v1Root;
            numbers[v1Root] -= v2Root;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if (parent(vertex) < 0)
            return vertex;
        return find(parent(vertex));
    }

}
