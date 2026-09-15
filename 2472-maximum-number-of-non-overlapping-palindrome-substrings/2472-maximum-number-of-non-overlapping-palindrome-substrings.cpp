class Solution {
public:
    int maxPalindromes(string s, int k) {
        int n = s.size();

        vector<vector<bool>> pal(n, vector<bool>(n, false));

        // Build palindrome table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s[i] == s[j] &&
                    (j - i <= 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        int ans = 0;
        int lastEnd = -1;

        // Process intervals in increasing ending index
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
};