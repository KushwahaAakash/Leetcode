
class Solution:
    def minMoves(self, nums: List[int]) -> int:
        max_val = max(nums)
        return max_val * len(nums) - sum(nums)
