package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF status;
    private WeightedQuickUnionUF statusWBot;
    private int size;
    private int top = 0;
    private int numOpen = 0;
    private int bottom = size * size + 1;
    private int[][] neighborIndex = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        grid = new boolean[N][N];
        status = new WeightedQuickUnionUF(N * N + 1); // Added top
        statusWBot = new WeightedQuickUnionUF(N * N + 2); // Added top & bot
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        size = N;
    }                // create N-by-N grid, with all sites initially blocked

    // Translate the 2D position to a Unique 1D index
    private int translate(int row, int col) {
        return size * row + col + 1; // + 1 to avoid top
    }

    public void open(int row, int col) {
        if (row > size || col > size)
            throw new IndexOutOfBoundsException();
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpen++;
        }
        int index = translate(row, col);
        if (row == 0) {
            statusWBot.union(top, index);
            status.union(top, index);
        } else if (row == size - 1) {
            statusWBot.union(index, bottom);
        }
        for (int i = 0; i < neighborIndex.length; i++) {
            int nRow = row + neighborIndex[i][0];
            int nCol = col + neighborIndex[i][1];
            if (nRow >= 0 && nRow < size && nCol >= 0 && nCol < size) {
                if (isOpen(nRow, nCol)) {
                    int t = translate(nRow, nCol);
                    status.union(index, t);
                    statusWBot.union(index, t);
                }
            }
        }
    }       // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0)
            throw new IndexOutOfBoundsException();
        return grid[row][col];
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0)
            throw new IndexOutOfBoundsException();
        if (!isOpen(row, col)) return false;
        int index = translate(row, col);
        return status.connected(index, top);
    }  // is the site (row, col) full?

    public int numberOfOpenSites() {
        return numOpen;
    }           // number of open sites

    public boolean percolates() {
        return statusWBot.connected(top, bottom);
    }              // does the system percolate?

    public static void main(String[] args) {

    }   // use for unit testing (not required, but keep this here for the autograder)
}
