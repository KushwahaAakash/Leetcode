
import java.util.*;

class Solution {
    int[] tin, tout, depth;
    int[][] up;
    int[] initial, bit;
    int timer, LOG, n;
    List<Integer>[] adj;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        this.n = n;
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[LOG][n];
        initial = new int[n];
        bit = new int[n + 2];

        // Iterative DFS to avoid stack overflow
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] stack = new int[n];
        int[] iter = new int[n];
        int top = 0;
        stack[0] = 0;
        parent[0] = 0;

        while (top >= 0) {
            int u = stack[top];

            if (iter[u] == 0) {
                tin[u] = ++timer;
                initial[u] = (u == 0 ? 0 : initial[parent[u]])
                        ^ (1 << (s.charAt(u) - 'a'));

                up[0][u] = parent[u];
                for (int j = 1; j < LOG; j++)
                    up[j][u] = up[j - 1][up[j - 1][u]];
            }

            if (iter[u] < adj[u].size()) {
                int v = adj[u].get(iter[u]++);
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[++top] = v;
            } else {
                tout[u] = timer;
                top--;
            }
        }

        // Current prefix masks = initial mask XOR Fenwick point query
        List<Boolean> ans = new ArrayList<>();

        char[] chars = s.toCharArray();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                if (chars[u] == c) continue;

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                rangeXor(tin[u], tout[u], delta);
                chars[u] = c;
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);

                int mask = prefix(u) ^ prefix(v)
                         ^ (1 << (chars[l] - 'a'));

                // At most one bit may be set
                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++)
            if ((diff & (1 << j)) != 0)
                u = up[j][u];

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    int prefix(int u) {
        return initial[u] ^ query(tin[u]);
    }

    void rangeXor(int l, int r, int val) {
        update(l, val);
        update(r + 1, val);
    }

    void update(int i, int val) {
        for (; i < bit.length; i += i & -i)
            bit[i] ^= val;
    }

    int query(int i) {
        int res = 0;
        for (; i > 0; i -= i & -i)
            res ^= bit[i];
        return res;
    }
}
