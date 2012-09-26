/*************************************************************************
 * Name: Mariano Simone
 * Email: mljsimone@gmail.com
 *
 * Compilation:  javac Board.java
 * Execution: java Board
 * Dependencies: StdOut
 *
 * Description: Abstract data type representing the state of the board
 *
 *************************************************************************/

public class Board {
    
    /*
     *  The length of a side of the matrix
     */
    final int dimension;
    
    /*
     *  The number of blocks in the wrong position
     */
    final int hamming;
    
    /*
     *  The sum of the distance of every block to his right position
     */
    final int manhattan;
    
    /*
     *  The matrix, represented as a flat array
     */
    final short[] board;
    
    public Board(int[][] blocks) {
        
        dimension = blocks.length;
        
        // Use a smaller type and a flat array to save some memory
        board = new short[dimension * dimension];
        
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                board[dimension * row + column] = (short) blocks[row][column];
            }
        }
        
        hamming = 0;
        manhattan = 0;
    }
    
    public int dimension() {
        return dimension;
    }
    
    public int hamming() {
        return hamming;
    }
    
    public int manhattan() {
        return manhattan;
    }
    
    public boolean isGoal() {
        
        // The last block should be the empty one
        if (board[board.length - 1] != 0)
            return false;
        
        // See if the others are in the right place
        for (int i = board.length - 2; i >= 0; i--)
            if (board[i] != i + 1)
                return false;
            
        return true;
    }
    
    public Board twin() {
        return null;
    }
    
    public boolean equals(Object y) {
        
        if (getClass() != y.getClass())
            return false;
        
        Board that = (Board) y;
        
        if (dimension != that.dimension())
            return false;
        
        if (hamming != that.hamming())
            return false;
        
        if (manhattan != that.manhattan())
            return false;
        
        if (!toString().equals(that.toString()))
            return false;
        
        return true;
    }
    
    public Iterable<Board> neighbors() {
        return null;
    }
    
    public String toString() {
        
        StringBuilder stringBuilder = new StringBuilder(dimension + "\n");
        
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                stringBuilder.append(
                    String.format("%2d ", board[dimension * row + column])
                );
            }
            
            stringBuilder.append("\n");
        }
        
        return stringBuilder.toString();
    }
    
    public static void main(String[] args) {
        
        int N = 3;
        
        int[][] blocks = new int[N][N];
        int[][] blocks2 = new int[N][N];
        
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                blocks[row][column] = 5;
                blocks2[row][column] = 5;
            }
        }
        
        Board board = new Board(blocks);
        Board board2 = new Board(blocks2);
        
        StdOut.println(board2.equals(board));
    }
}