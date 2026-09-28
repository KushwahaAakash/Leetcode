class Solution {
public:
    int maxDepth(string s) {
         stack<char> parentheses;
    int max_depth = 0;
    for (char c : s) {
        if (c == '(') {
            parentheses.push(c);
            max_depth = max(max_depth, (int)parentheses.size());
        } else if (c == ')') {
            parentheses.pop();
        }
    }
    return max_depth;
    }
};