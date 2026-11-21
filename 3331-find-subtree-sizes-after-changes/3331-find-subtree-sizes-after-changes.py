
class Solution:
    def findSubtreeSizes(self, parent, s):
        n = len(parent)

        tree = [[] for _ in range(n)]
        newTree = [[] for _ in range(n)]

        for i in range(1, n):
            tree[parent[i]].append(i)

        last = [-1] * 26

        def build(u):
            c = ord(s[u]) - ord('a')

            prev = last[c]

            newParent = parent[u] if prev == -1 else prev

            if newParent != -1:
                newTree[newParent].append(u)

            last[c] = u

            for v in tree[u]:
                build(v)

            last[c] = prev

        build(0)

        ans = [0] * n

        def dfs(u):
            size = 1

            for v in newTree[u]:
                size += dfs(v)

            ans[u] = size
            return size

        dfs(0)

        return ans
