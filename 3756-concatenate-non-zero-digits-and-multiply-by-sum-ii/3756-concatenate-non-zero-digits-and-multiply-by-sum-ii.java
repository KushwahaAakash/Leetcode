class Solution {

    static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();

        long[] prefNum = new long[n + 1];
        int[] prefSum = new int[n + 1];
        int[] cnt = new int[n + 1];
        long[] pow10 = new long[n + 1];

        pow10[0] = 1;
        for (int i = 1; i <= n; i++)
            pow10[i] = (pow10[i - 1] * 10) % MOD;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            prefNum[i + 1] = prefNum[i];
            prefSum[i + 1] = prefSum[i];
            cnt[i + 1] = cnt[i];

            if (c != '0') {
                int d = c - '0';
                prefNum[i + 1] = (prefNum[i] * 10 + d) % MOD;
                prefSum[i + 1] += d;
                cnt[i + 1]++;
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int digits = cnt[r + 1] - cnt[l];

            long num = prefNum[r + 1] - (prefNum[l] * pow10[digits]) % MOD;
            if (num < 0)
                num += MOD;

            long sum = prefSum[r + 1] - prefSum[l];

            ans[i] = (int) ((num * sum) % MOD);
        }

        return ans;
    }
}