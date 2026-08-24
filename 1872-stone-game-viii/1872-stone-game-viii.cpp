class Solution {
public:
    int stoneGameVIII(vector<int>& stones) {
        int n = stones.size();

        // Convert stones into prefix sums
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // Base case
        int best = stones[n - 1];

        // DP from right to left
        for (int i = n - 2; i > 0; i--) {
            best = max(best, stones[i] - best);
        }

        return best;
    }
};