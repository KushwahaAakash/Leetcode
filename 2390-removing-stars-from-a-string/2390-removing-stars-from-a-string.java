class Solution {
    public String removeStars(String s) {
        char[] stack = new char[s.length()];
        int top = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '*') {
                top--;
            } else {
                stack[top++] = c;
            }
        }

        return new String(stack, 0, top);
    }
}