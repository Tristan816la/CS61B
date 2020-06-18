package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int trials;
    private double[] fraction;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        Percolation a = pf.make(N);
        trials = T;
        for (int i = 0; i < T; i++) {
            int numOpen = 0;
            while (!a.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                a.open(row, col);
                numOpen++;
            }
            fraction[i] = (double) numOpen / (N * N);
        }
    }   // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(fraction);
    }           // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(fraction);
    }          // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return (mean() - 1.96 * stddev() / Math.sqrt(trials));
    }        // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return (mean() + 1.96 * stddev() / Math.sqrt(trials));
    }

}
