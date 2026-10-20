from typing import List

class Solution:
    def sortableIntegers(self, nums: List[int]) -> int:
        n = len(nums)
        a = nums
        total = 0

        for k in range(1, n + 1):
            if n % k:
                continue
            ok = True
            prev_max = 0
            for s in range(0, n, k):
                cnt = 0
                pos = -1
                for i in range(s, s + k - 1):
                    if a[i] > a[i + 1]:
                        cnt += 1
                        pos = i
                        if cnt > 1:
                            break
                if cnt > 1:
                    ok = False
                    break
                if cnt == 0:
                    mn, mx = a[s], a[s + k - 1]
                else:
                    if a[s + k - 1] > a[s]:
                        ok = False
                        break
                    mn, mx = a[pos + 1], a[pos]
                if mn < prev_max:
                    ok = False
                    break
                prev_max = mx
            if ok:
                total += k
        return total