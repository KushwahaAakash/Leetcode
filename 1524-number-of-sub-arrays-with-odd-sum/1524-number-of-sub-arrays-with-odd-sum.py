
class Solution:
    def numOfSubarrays(self, arr):

        MOD = 10**9 + 7

        even = 1
        odd = 0
        parity = 0
        ans = 0

        for num in arr:

            parity ^= (num & 1)

            if parity:
                ans = (ans + even) % MOD
                odd += 1
            else:
                ans = (ans + odd) % MOD
                even += 1

        return ans
