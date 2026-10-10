
import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        long k = (long) k1 + k2;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
        }

        if (sum <= k) return 0;

        Arrays.sort(diff);

        int left = 0, right = diff[n - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            long need = 0;

            for (int d : diff) {
                if (d > mid) need += d - mid;
            }

            if (need <= k) right = mid;
            else left = mid + 1;
        }

        int level = left;
        long need = 0;

        for (int d : diff) {
            if (d > level) need += d - level;
        }

        long remaining = k - need;
        long ans = 0;

        for (int d : diff) {
            long value = Math.min(d, level);
            ans += value * value;
        }

        // Use any remaining operations to reduce values at the level by one.
        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] >= level && level > 0) {
                ans -= (long) level * level - (long) (level - 1) * (level - 1);
                remaining--;
            }
        }

        return ans;
    }
}
