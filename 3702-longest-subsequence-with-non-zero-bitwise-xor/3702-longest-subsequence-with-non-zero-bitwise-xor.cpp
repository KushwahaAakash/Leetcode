class Solution {
public:
    int longestSubsequence(vector<int>& nums) {
        int n = nums.size();
        int totalXor = 0;
        bool hasNonZero = false;

        for (int x : nums) {
            totalXor ^= x;

            if (x != 0) {
                hasNonZero = true;
            }
        }

        // Entire array has non-zero XOR
        if (totalXor != 0) {
            return n;
        }

        // XOR is zero and all elements are zero
        if (!hasNonZero) {
            return 0;
        }

        // XOR is zero, but at least one element is non-zero
        return n - 1;
    }
};