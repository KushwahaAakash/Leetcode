class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[i] = earliest index in word1 where word2[i]
        // can be matched when matching word2[i...m-1].
        int[] suf = new int[m];

        int p = n - 1;

        for (int i = m - 1; i >= 0; i--) {
            while (p >= 0 && word1.charAt(p) != word2.charAt(i)) {
                p--;
            }

            if (p < 0) {
                break;
            }

            suf[i] = p;
            p--;
        }

        int[] ans = new int[m];

        int j = 0;
        boolean usedChange = false;

        for (int i = 0; i < n && j < m; i++) {

            // Exact match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            // Use the one allowed modification
            else if (!usedChange) {

                // If this is the last character,
                // we can always change it.
                if (j == m - 1) {
                    ans[j] = i;
                    j++;
                    usedChange = true;
                }

                // Check whether the remaining part can be
                // matched exactly after using mismatch here.
                else if (suf[j + 1] > i) {
                    ans[j] = i;
                    j++;
                    usedChange = true;
                }
            }
        }

        // Could not construct a valid sequence
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}