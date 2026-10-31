
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        right[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                long ways = ((long) right[i][j] + down[i][j]) % MOD;

                if (grid[i][j] == 0) {

                    // Move right
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (int) ((right[i][j + 1] + ways) % MOD);
                    }

                    // Move down
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (int) ((down[i + 1][j] + ways) % MOD);
                    }

                } else {

                    // Mirror: right becomes down
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (int) ((down[i + 1][j] + right[i][j]) % MOD);
                    }

                    // Mirror: down becomes right
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (int) ((right[i][j + 1] + down[i][j]) % MOD);
                    }
                }
            }
        }

        return (int) (((long) right[m - 1][n - 1]
                     + down[m - 1][n - 1]) % MOD);
    }
}
