
from collections import deque

class Solution:
    def countNonDecreasingSubarrays(self, nums, k):

        dq = deque()

        ans = 0
        cost = 0

        j = len(nums) - 1

        for i in range(len(nums) - 1, -1, -1):

            num = nums[i]
            count = 1

            while dq and dq[-1][0] < num:

                val, cnt = dq.pop()

                cost += (num - val) * cnt
                count += cnt

            dq.append([num, count])

            while cost > k:

                val, cnt = dq[0]

                cost -= val - nums[j]

                if cnt == 1:
                    dq.popleft()
                else:
                    dq[0][1] -= 1

                j -= 1

            ans += j - i + 1

        return ans
