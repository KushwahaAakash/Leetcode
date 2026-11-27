
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int cheapest = Integer.MAX_VALUE;

        for (int[] item : items) {
            cheapest = Math.min(cheapest, item[1]);
        }

        int[] gain = new int[n];

        for (int i = 0; i < n; i++) {
            gain[i] = 1;
            int factor = items[i][0];

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factor == 0) {
                    gain[i]++;
                }
            }
        }

        // dp[c] = maximum gain using exactly c budget
        int[] dp = new int[budget + 1];

        for (int i = 1; i <= budget; i++) {
            dp[i] = -1;
        }

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int value = gain[i];

            for (int c = budget; c >= price; c--) {
                if (dp[c - price] != -1) {
                    dp[c] = Math.max(
                        dp[c],
                        dp[c - price] + value
                    );
                }
            }
        }

        int ans = 0;

        for (int c = 0; c <= budget; c++) {
            if (dp[c] != -1) {
                ans = Math.max(
                    ans,
                    dp[c] + (budget - c) / cheapest
                );
            }
        }

        return ans;
    }
}
