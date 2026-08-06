import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF fullUF;
    private int openSites;
    private int virtualTop;
    private int virtualBottom;
    private int N;


    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if(N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        fullUF = new WeightedQuickUnionUF(N * N + 1);
        virtualTop = N * N;
        virtualBottom = N * N + 1;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }

        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;
        openSites++;

        int current = xyTo1D(row, col);
        if (row < N - 1 && grid[row+1][col]) {
            uf.union(current, xyTo1D(row+1, col));
            fullUF.union(current, xyTo1D(row+1, col));
        }
        if (row > 0 && grid[row-1][col]) {
            uf.union(current, xyTo1D(row-1, col));
            fullUF.union(current, xyTo1D(row-1, col));
        }
        if (col < N - 1 && grid[row][col+1]) {
            uf.union(current, xyTo1D(row,col+1));
            fullUF.union(current, xyTo1D(row,col+1));
        }
        if (col > 0 && grid[row][col-1]) {
            uf.union(current, xyTo1D(row,col-1));
            fullUF.union(current, xyTo1D(row,col-1));
        }

        if(row == 0) {
            uf.union (current, virtualTop);
            fullUF.union(current, virtualTop);
        }

        if(row == N-1) {
            uf.union (current, virtualBottom);
        }



    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        //Check whether the given position is within the grid boundary.
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }

        // check if the site is open.

        if(!isOpen(row, col)) {
            return false;
        }
        // Union-Find uses a 1D array, so convert the 2D grid position into an index.
        int current = xyTo1D(row, col);

        // A full site must be connected to the virtual top site.

        return fullUF.connected(virtualTop, current);

    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        // check if virtualTop is connected to virtualBottom.
        return  uf.connected(virtualTop, virtualBottom);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
