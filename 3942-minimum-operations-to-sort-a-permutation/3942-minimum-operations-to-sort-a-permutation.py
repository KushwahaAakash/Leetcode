
class Solution:
    def minOperations(self, nums: list[int]) -> int:
        n = len(nums)
        ans = float('inf')

        # Check increasing cyclic order
        k = nums[0]
        for i in range(n):
            if nums[i] != (k + i) % n:
                break
        else:
            ans = min(ans, (n - k) % n, k + 2)

        # Check decreasing cyclic order
        k = nums[0]
        for i in range(n):
            if nums[i] != (k - i) % n:
                break
        else:
            shift = (n - 1 - k) % n
            ans = min(ans, 1 + min(shift, (n - shift) % n))

        return -1 if ans == float('inf') else ans
