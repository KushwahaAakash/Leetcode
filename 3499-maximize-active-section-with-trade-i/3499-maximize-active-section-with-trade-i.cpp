class Solution {
public:
    int maxActiveSectionsAfterTrade(string s) {

        int n = s.size();

        int ones = 0;
        for (char c : s)
            if (c == '1')
                ones++;

        string t = "1" + s + "1";
        int m = t.size();

        int ans = ones;

        int i = 1;

        while (i < m - 1) {

            if (t[i] == '1') {

                int l = i;
                while (i < m && t[i] == '1')
                    i++;
                int r = i - 1;

                // This 1-block must be surrounded by 0s
                if (t[l - 1] == '0' && t[r + 1] == '0') {

                    int left = 0;
                    int p = l - 1;
                    while (p >= 0 && t[p] == '0') {
                        left++;
                        p--;
                    }

                    int right = 0;
                    p = r + 1;
                    while (p < m && t[p] == '0') {
                        right++;
                        p++;
                    }

                    ans = max(ans, ones + left + right);
                }

            } else {
                i++;
            }
        }

        return min(ans, n);
    }
};