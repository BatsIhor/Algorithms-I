/*************************************************************************
 * Name: Mariano Simone
 * Email: mljsimone@gmail.com
 *
 * Compilation:  javac PercolationStats.java
 * Execution:
 * Dependencies: Percolation.java
 *
 * Description: Class in charge of running the simulation.
 *
 *************************************************************************/

public class PercolationStats {
    
    private double[] thresholds;
    
    public PercolationStats(int N, int T) {

        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        
        Percolation percolation;
        
        thresholds = new double[T];
        
        for (int t = 0; t < T; t++) {
            
            int n = 0;
            percolation = new Percolation(N);
            
            while(n < N * N) {
                
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;
                
                if (percolation.isOpen(i, j))
                    continue;
                
                n++;
                percolation.open(i, j);
                
                if (percolation.percolates()) {
                    thresholds[t] = n / (double) (N * N);
                    break;
                }
            }
        }
    }
    
    public double mean() {
        return StdStats.mean(thresholds);
    }
    
    public double stddev() {
        return StdStats.stddev(thresholds);
    }
    
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        
        double mean = stats.mean();
        double stddev = stats.stddev();
        double[] interval = new double[2];
        
        interval[0] = mean - ((1.96 * stddev) / Math.sqrt(T));
        interval[1] = mean + ((1.96 * stddev) / Math.sqrt(T));
        
        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = " + interval[0] + ", " + interval[1]);
    }
}
