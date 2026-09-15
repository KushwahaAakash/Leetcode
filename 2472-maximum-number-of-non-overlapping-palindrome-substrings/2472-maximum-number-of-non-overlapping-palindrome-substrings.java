class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        boolean[][] pal = new boolean[n][n];

        // Build palindrome DP table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        int ans = 0;
        int lastEnd = -1;

        // Process substrings by increasing ending index
        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end - k + 1; start++) {

                if (start > lastEnd && pal[start][end]) {
                    ans++;
                    lastEnd = end;
                    break;
                }
            }
        }

        return ans;
    }
}