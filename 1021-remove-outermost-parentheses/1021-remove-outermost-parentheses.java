class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int balance = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                if (balance > 0) {
                    ans.append(c);
                }
                balance++;
            } else {
                balance--;

                if (balance > 0) {
                    ans.append(c);
                }
            }
        }

        return ans.toString();
    }
}