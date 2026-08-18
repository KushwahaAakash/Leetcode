class Solution {
public:
    int largestInteger(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> count(51, 0);

        // Iterate through every subarray of size k
        for (int i = 0; i <= n - k; i++) {
            bool present[51] = {};

            // Mark distinct elements in the current subarray
            for (int j = i; j < i + k; j++) {
                present[nums[j]] = true;
            }

            // Count this subarray for each distinct element
            for (int x = 0; x <= 50; x++) {
                if (present[x]) {
                    count[x]++;
                }
            }
        }

        // Find the largest element appearing in exactly one subarray
        for (int x = 50; x >= 0; x--) {
            if (count[x] == 1) {
                return x;
            }
        }

        return -1;
    }
};