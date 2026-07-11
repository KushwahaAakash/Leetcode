class Solution {
public:
    int nodes;
    int degreeSum;

    void dfs(int u, vector<vector<int>>& graph, vector<bool>& vis) {

        vis[u] = true;

        nodes++;

        degreeSum += graph[u].size();

        for (int v : graph[u]) {
            if (!vis[v])
                dfs(v, graph, vis);
        }
    }

    int countCompleteComponents(int n, vector<vector<int>>& edges) {

        vector<vector<int>> graph(n);

        for (auto &e : edges) {
            graph[e[0]].push_back(e[1]);
            graph[e[1]].push_back(e[0]);
        }

        vector<bool> vis(n, false);

        int ans = 0;

        for (int i = 0; i < n; i++) {

            if (vis[i])
                continue;

            nodes = 0;
            degreeSum = 0;

            dfs(i, graph, vis);

            int edgeCount = degreeSum / 2;

            if (edgeCount == nodes * (nodes - 1) / 2)
                ans++;
        }

        return ans;
    }
};