class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n + 1];
        int[] suffixSum = new int[n];

        // Initialize dp with -1 (unvisited)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        // Compute suffix sums to make sum calculation easier
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = piles[i] + suffixSum[i + 1];
        }

        // Start the dp process
        return dpFunc(0, 1, piles, dp, suffixSum);
    }

    private int dpFunc(int i, int M, int[] piles, int[][] dp, int[] suffixSum) {
        int n = piles.length;
        if (i >= n) {
            return 0;
        }
        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        int maxStones = 0;
        // Try taking x piles where x ranges from 1 to 2*M
        for (int x = 1; x <= 2 * M && i + x <= n; x++) {
            maxStones = Math.max(maxStones, suffixSum[i] - dpFunc(i + x, Math.max(M, x), piles, dp, suffixSum));
        }

        dp[i][M] = maxStones;
        return maxStones;
    }
}
