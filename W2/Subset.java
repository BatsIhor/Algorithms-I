/*************************************************************************
 * Name: Mariano Simone
 * Email: mljsimone@gmail.com
 *
 * Compilation:  javac Subset.java
 * Execution: echo A B C D E F G H I | java Subset 3
 * Dependencies: StdIn, StdOut
 *
 * Description: Read N strings and print K of them in random order.
 *
 *************************************************************************/

public class Subset {
    
    public static void main(String[] args) {
        
        int n = 0;
        String[] strings = new String[1];
        
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            
            strings[n++] = s;
            
            if (n == strings.length) {
                String[] newStrings = new String[n * 2];
                
                for (int i = 0; i < n; i++) {
                    newStrings[i] = strings[i];
                }
                
                strings = newStrings;
            }
            
            int r = StdRandom.uniform(n);
            
            String swap = strings[n-1];
            strings[n-1] = strings[r];
            strings[r] = swap;
        }
        
        int k = Integer.parseInt(args[0]);
        
        for (int i = 0; i < k && i < n; i++) {
            StdOut.println(strings[i]);
        }
    }
}