class Solution {
public:
    int minimumDeletions(vector<int>& nums) {
        int n = nums.size();

        int minIdx = 0;
        int maxIdx = 0;

        // Find indices of minimum and maximum
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx])
                minIdx = i;

            if (nums[i] > nums[maxIdx])
                maxIdx = i;
        }

        // Put smaller index in left
        // and larger index in right
        int left = min(minIdx, maxIdx);
        int right = max(minIdx, maxIdx);

        // Option 1: Remove from front
        int removeFront = right + 1;

        // Option 2: Remove from back
        int removeBack = n - left;

        // Option 3: Remove from both sides
        int removeBoth = (left + 1) + (n - right);

        return min({removeFront, removeBack, removeBoth});
    }
};