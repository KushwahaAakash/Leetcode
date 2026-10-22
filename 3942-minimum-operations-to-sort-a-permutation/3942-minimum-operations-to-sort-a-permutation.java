
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        int k = nums[0];
        boolean inc = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (k + i) % n) {
                inc = false;
                break;
            }
        }

        if (inc) {
            ans = Math.min(ans,
                    Math.min((n - k) % n, k + 2));
        }

        boolean dec = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (k - i + n) % n) {
                dec = false;
                break;
            }
        }

        if (dec) {
            int shift = (n - 1 - k + n) % n;
            ans = Math.min(ans,
                    1 + Math.min(shift, (n - shift) % n));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
