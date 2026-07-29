class Solution {

    static final int LIMIT = 1_000_001;
    int[][] C;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray())
            freq[ch - 'a']++;

        String mid = "";

        int[] half = new int[26];
        int len = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1)
                mid = String.valueOf((char) ('a' + i));

            half[i] = freq[i] / 2;
            len += half[i];
        }

        buildComb(len);

        if (countWays(half, len) < k)
            return "";

        StringBuilder first = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                int ways = countWays(half, len - pos - 1);

                if (ways >= k) {
                    first.append((char) ('a' + c));
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        String second = new StringBuilder(first).reverse().toString();

        return first.toString() + mid + second;
    }

    private void buildComb(int n) {

        C = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            C[i] = new int[i + 1];
            C[i][0] = C[i][i] = 1;

            for (int j = 1; j < i; j++) {
                long val = (long) C[i - 1][j - 1] + C[i - 1][j];
                C[i][j] = (int) Math.min(LIMIT, val);
            }
        }
    }

    private int countWays(int[] cnt, int total) {

        long ans = 1;
        int rem = total;

        for (int x : cnt) {
            if (x == 0)
                continue;

            ans *= C[rem][x];

            if (ans >= LIMIT)
                return LIMIT;

            rem -= x;
        }

        return (int) ans;
    }
}