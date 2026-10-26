
class Solution:
    def countIslands(self, grid, k):
        m, n = len(grid), len(grid[0])
        count = 0

        def dfs(i, j):
            if (i < 0 or j < 0 or i >= m or j >= n
                    or grid[i][j] == 0):
                return 0

            value = grid[i][j]
            grid[i][j] = 0

            return (value
                    + dfs(i + 1, j)
                    + dfs(i - 1, j)
                    + dfs(i, j + 1)
                    + dfs(i, j - 1))

        for i in range(m):
            for j in range(n):
                if grid[i][j] > 0:
                    total = dfs(i, j)

                    if total % k == 0:
                        count += 1

        return count
