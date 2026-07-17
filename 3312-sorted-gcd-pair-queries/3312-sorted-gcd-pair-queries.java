class Solution {

    public int[] gcdValues(int[] nums, long[] queries) {

        int mx = 0;

        for (int x : nums)
            mx = Math.max(mx, x);

        int[] freq = new int[mx + 1];

        for (int x : nums)
            freq[x]++;

        long[] cnt = new long[mx + 1];

        for (int g = 1; g <= mx; g++) {
            for (int j = g; j <= mx; j += g)
                cnt[g] += freq[j];
        }

        long[] exact = new long[mx + 1];

        for (int g = mx; g >= 1; g--) {

            exact[g] = cnt[g] * (cnt[g] - 1) / 2;

            for (int j = g + g; j <= mx; j += g)
                exact[g] -= exact[j];
        }

        long[] pref = new long[mx + 1];

        for (int g = 1; g <= mx; g++)
            pref[g] = pref[g - 1] + exact[g];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long q = queries[i];

            int l = 1, r = mx;

            while (l < r) {

                int mid = (l + r) / 2;

                if (pref[mid] > q)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}