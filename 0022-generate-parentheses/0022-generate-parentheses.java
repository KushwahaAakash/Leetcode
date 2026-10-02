class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generate("", 0, 0, n, ans);
        return ans;
    }

    public void generate(String s, int open, int close, int n, List<String> ans) {
        if (s.length() == 2 * n) {
            ans.add(s);
            return;
        }

        // Add opening bracket
        if (open < n) {
            generate(s + "(", open + 1, close, n, ans);
        }

        // Add closing bracket only if it won't make invalid parentheses
        if (close < open) {
            generate(s + ")", open, close + 1, n, ans);
        }
    }
}