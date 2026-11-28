
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = n;
        int minPrice = Integer.MAX_VALUE;

        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count item types divisible by each factor
        int[] cnt = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {
            for (int j = f; j <= maxFactor; j += f) {
                cnt[f] += freq[j];
            }
        }

        // Process cheaper items first
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        int ans = 0;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // Bonus purchase gives 2 items.
            // Buying 2 cheapest items costs 2 * minPrice.
            if (price >= 2L * minPrice) {
                break;
            }

            int bonus = cnt[factor] - 1;

            if (bonus <= 0) {
                continue;
            }

            int take = Math.min(bonus, budget / price);

            ans += 2 * take;
            budget -= take * price;
        }

        // Buy normal items with remaining budget
        ans += budget / minPrice;

        return ans;
    }
}
