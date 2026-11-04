
import java.util.*;

class Solution {

    PriorityQueue<Integer> small =
        new PriorityQueue<>(Collections.reverseOrder());

    PriorityQueue<Integer> large =
        new PriorityQueue<>();

    HashMap<Integer, Integer> delayed = new HashMap<>();

    int smallSize = 0, largeSize = 0;
    long smallSum = 0, largeSum = 0;

    void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();

            if (delayed.getOrDefault(num, 0) == 0)
                break;

            heap.poll();

            delayed.put(num, delayed.get(num) - 1);
        }
    }

    void balance() {
        prune(small);
        prune(large);

        while (smallSize > largeSize + 1) {
            int num = small.poll();

            smallSize--;
            smallSum -= num;

            large.offer(num);
            largeSize++;
            largeSum += num;

            prune(small);
        }

        while (smallSize < largeSize) {
            int num = large.poll();

            largeSize--;
            largeSum -= num;

            small.offer(num);
            smallSize++;
            smallSum += num;

            prune(large);
        }
    }

    void add(int num) {
        prune(small);

        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            smallSize++;
            smallSum += num;
        } else {
            large.offer(num);
            largeSize++;
            largeSum += num;
        }

        balance();
    }

    void remove(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);

        if (num <= small.peek()) {
            smallSize--;
            smallSum -= num;

            if (num == small.peek())
                prune(small);
        } else {
            largeSize--;
            largeSum -= num;

            if (!large.isEmpty() && num == large.peek())
                prune(large);
        }

        balance();
    }

    long getCost() {
        long median = small.peek();

        return median * smallSize - smallSum
             + largeSum - median * largeSize;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        // First window
        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        cost[0] = getCost();

        // Sliding window
        for (int i = x; i < n; i++) {
            remove(nums[i - x]);
            add(nums[i]);

            cost[i - x + 1] = getCost();
        }

        // DP
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (long[] row : dp)
            Arrays.fill(row, INF);

        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {

                if (dp[i][j] == INF)
                    continue;

                // Skip current position
                dp[i + 1][j] = Math.min(
                    dp[i + 1][j],
                    dp[i][j]
                );

                // Select window
                if (j < k && i + x <= n) {
                    dp[i + x][j + 1] = Math.min(
                        dp[i + x][j + 1],
                        dp[i][j] + cost[i]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
