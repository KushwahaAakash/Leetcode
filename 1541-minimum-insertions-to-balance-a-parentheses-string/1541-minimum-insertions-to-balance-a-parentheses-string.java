
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else {
                // Check whether the next character is ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    ans++;
                }

                // Match this closing pair with an opening bracket
                if (open > 0) {
                    open--;
                } else {
                    ans++;
                }
            }
        }

        return ans + 2 * open;
    }
}