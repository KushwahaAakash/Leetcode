
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long b1 = 911382323L, m1 = 1000000007L;
        long b2 = 972663749L, m2 = 1000000009L;

        long[] p1 = new long[n];
        long[] p2 = new long[n];

        p1[0] = p2[0] = 1;

        for (int i = 1; i < n; i++) {
            p1[i] = p1[i - 1] * b1 % m1;
            p2[i] = p2[i - 1] * b2 % m2;
        }

        int left = 1, right = n;

        while (left < right) {
            int k = left + (right - left) / 2;

            if (check(nums, k, b1, m1, b2, m2, p1, p2)) {
                right = k;
            } else {
                left = k + 1;
            }
        }

        return left;
    }

    private boolean check(int[] nums, int k,
                          long b1, long m1,
                          long b2, long m2,
                          long[] p1, long[] p2) {

        int n = nums.length;

        long h1 = 0, h2 = 0;

        HashMap<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < k; i++) {
            h1 = (h1 * b1 + nums[i]) % m1;
            h2 = (h2 * b2 + nums[i]) % m2;
        }

        long key = h1 * m2 + h2;
        map.put(key, 1);

        for (int i = k; i < n; i++) {
            h1 = ((h1 - nums[i-k] * p1[k-1] % m1 + m1)
                    * b1 + nums[i]) % m1;

            h2 = ((h2 - nums[i-k] * p2[k-1] % m2 + m2)
                    * b2 + nums[i]) % m2;

            key = h1 * m2 + h2;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
