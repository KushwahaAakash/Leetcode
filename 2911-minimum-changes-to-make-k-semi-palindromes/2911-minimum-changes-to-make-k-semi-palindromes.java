
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // Precompute divisors for every length
        List<Integer>[] factors = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            factors[i] = new ArrayList<>();
        }

        for (int d = 1; d < n; d++) {
            for (int len = d * 2; len <= n; len += d) {
                factors[len].add(d);
            }
        }

        // Precompute cost of every substring
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                int minCost = n;

                for (int d : factors[len]) {
                    int changes = 0;

                    for (int offset = 0; offset < d; offset++) {
                        int l = i + offset;
                        int r = j - d + 1 + offset;

                        while (l < r) {
                            if (s.charAt(l) != s.charAt(r)) {
                                changes++;
                            }

                            l += d;
                            r -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[i][j] = minCost;
            }
        }

        // Suffix DP
        int INF = 1000000;
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[n][0] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int parts = 1; parts <= k; parts++) {

                for (int j = i + 1; j < n; j++) {

                    if (dp[j + 1][parts - 1] == INF) {
                        continue;
                    }

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        cost[i][j] + dp[j + 1][parts - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}
