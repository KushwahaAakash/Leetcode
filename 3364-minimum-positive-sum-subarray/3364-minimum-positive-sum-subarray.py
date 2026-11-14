
class Solution:
    def minimumSumSubarray(self, nums, l, r):

        n = len(nums)

        prefix = [0] * (n + 1)

        for i in range(n):
            prefix[i + 1] = prefix[i] + nums[i]

        ans = float('inf')

        for i in range(n):

            for length in range(l, r + 1):

                if i + length > n:
                    break

                total = prefix[i + length] - prefix[i]

                if total > 0:
                    ans = min(ans, total)

        return -1 if ans == float('inf') else ans
