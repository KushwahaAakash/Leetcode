
import java.util.*;

class Solution {
    List<Integer>[] tree;
    List<Integer>[] newTree;
    int[] last;
    int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        tree = new ArrayList[n];
        newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        build(0, parent, s);

        ans = new int[n];
        dfs(0);

        return ans;
    }

    void build(int u, int[] parent, String s) {
        int c = s.charAt(u) - 'a';

        int prev = last[c];

        int newParent = (prev == -1) ? parent[u] : prev;

        if (newParent != -1) {
            newTree[newParent].add(u);
        }

        last[c] = u;

        for (int v : tree[u]) {
            build(v, parent, s);
        }

        last[c] = prev;
    }

    int dfs(int u) {
        int size = 1;

        for (int v : newTree[u]) {
            size += dfs(v);
        }

        return ans[u] = size;
    }
}
