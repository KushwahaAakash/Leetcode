
class Solution:
    def maximumSaleItems(self, items: list[list[int]], budget: int) -> int:
        n = len(items)

        cheapest = min(price for factor, price in items)

        gain = [1] * n

        for i in range(n):
            factor = items[i][0]

            for j in range(n):
                if i != j and items[j][0] % factor == 0:
                    gain[i] += 1

        # dp[c] = maximum gain using exactly c budget
        dp = [-1] * (budget + 1)
        dp[0] = 0

        for i in range(n):
            price = items[i][1]
            value = gain[i]

            for c in range(budget, price - 1, -1):
                if dp[c - price] != -1:
                    dp[c] = max(dp[c], dp[c - price] + value)

        ans = 0

        for c in range(budget + 1):
            if dp[c] != -1:
                ans = max(ans, dp[c] + (budget - c) // cheapest)

        return ans
