
class Solution:
    def maximumSaleItems(self, items: list[list[int]], budget: int) -> int:
        n = len(items)

        max_factor = max(item[0] for item in items)
        freq = [0] * (max_factor + 1)

        min_price = float('inf')

        for factor, price in items:
            freq[factor] += 1
            min_price = min(min_price, price)

        # Count how many item types each factor can unlock
        gain = [0] * (max_factor + 1)

        for f in range(1, max_factor + 1):
            count = 0

            for multiple in range(f, max_factor + 1, f):
                count += freq[multiple]

            gain[f] = count - 1

        # Cheapest bonus purchases first
        items.sort(key=lambda x: x[1])

        ans = 0

        for factor, price in items:
            if price >= 2 * min_price:
                break

            if gain[factor] <= 0:
                continue

            take = min(gain[factor], budget // price)

            ans += 2 * take
            budget -= take * price

        # Spend remaining budget on cheapest normal items
        ans += budget // min_price

        return ans
