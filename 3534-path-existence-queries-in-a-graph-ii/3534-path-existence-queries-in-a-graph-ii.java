class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        int[] comp = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }

        int cid = 0;
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (value[i] - value[i - 1] > maxDiff)
                cid++;
            comp[i] = cid;
        }

        int[] next = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j + 1 < n && value[j + 1] - value[i] <= maxDiff)
                j++;
            next[i] = j;
            if (j < i + 1)
                j = i + 1;
        }

        int LOG = 17;
        while ((1 << LOG) <= n)
            LOG++;

        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++)
            up[0][i] = next[i];

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {

            int a = pos[queries[qi][0]];
            int b = pos[queries[qi][1]];

            if (a == b) {
                ans[qi] = 0;
                continue;
            }

            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }

            if (comp[a] != comp[b]) {
                ans[qi] = -1;
                continue;
            }

            int cur = a;
            int res = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < b) {
                    cur = up[k][cur];
                    res += 1 << k;
                }
            }

            ans[qi] = res + 1;
        }

        return ans;
    }
}