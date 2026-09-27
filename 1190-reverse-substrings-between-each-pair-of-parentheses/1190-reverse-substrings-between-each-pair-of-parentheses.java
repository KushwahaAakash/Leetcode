class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        
        // Traverse the string character by character
        for (char c : s.toCharArray()) {
            if (c == ')') {
                // When we encounter a closing parenthesis, we start popping until we find an opening parenthesis
                List<Character> temp = new ArrayList<>();
                
                while (stack.peek() != '(') {
                    temp.add(stack.pop());
                }
                
                // Pop the opening parenthesis '(' from the stack
                stack.pop();
                
                // Push the reversed characters back into the stack
                for (char ch : temp) {
                    stack.push(ch);
                }
            } else {
                // Push every character (including '(') into the stack
                stack.push(c);
            }
        }
        
        // Rebuild the final string from the stack
        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }
        
        return result.toString();
    }
}
