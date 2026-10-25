
class Solution:
    def closedIsland(self, grid):
        m, n = len(grid), len(grid[0])

        def dfs(i, j):
            if (i < 0 or j < 0 or i >= m or j >= n
                    or grid[i][j] == 1):
                return

            grid[i][j] = 1

            dfs(i + 1, j)
            dfs(i - 1, j)
            dfs(i, j + 1)
            dfs(i, j - 1)

        # Remove boundary-connected islands
        for i in range(m):
            dfs(i, 0)
            dfs(i, n - 1)

        for j in range(n):
            dfs(0, j)
            dfs(m - 1, j)

        count = 0

        for i in range(1, m - 1):
            for j in range(1, n - 1):
                if grid[i][j] == 0:
                    count += 1
                    dfs(i, j)

        return count
