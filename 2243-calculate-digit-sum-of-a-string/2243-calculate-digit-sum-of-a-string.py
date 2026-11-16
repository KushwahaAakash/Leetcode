
class Solution:
    def digitSum(self, s: str, k: int) -> str:
        while len(s) > k:
            res = ""

            for i in range(0, len(s), k):
                group = s[i:i+k]
                res += str(sum(int(c) for c in group))

            s = res

        return s
