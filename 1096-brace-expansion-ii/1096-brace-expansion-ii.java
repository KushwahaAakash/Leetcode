import java.util.*;

class Solution {
    int pos = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> set = solve(expression);

        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);

        return ans;
    }

    Set<String> solve(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (pos < s.length() && s.charAt(pos) != '}') {

            Set<String> current = new HashSet<>();

            if (s.charAt(pos) == '{') {
                pos++;
                current = solve(s);
                pos++; // skip '}'
            } else {
                current.add(String.valueOf(s.charAt(pos)));
                pos++;
            }

            Set<String> next = new HashSet<>();

            for (String a : result) {
                for (String b : current) {
                    next.add(a + b);
                }
            }

            result = next;

            if (pos < s.length() && s.charAt(pos) == ',') {
                pos++;

                Set<String> right = solve(s);
                result.addAll(right);

                break;
            }
        }

        return result;
    }
}