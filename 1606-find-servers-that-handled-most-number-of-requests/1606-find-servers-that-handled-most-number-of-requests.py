
import heapq

class Solution:
    def busiestServers(self, k, arrival, load):

        n = len(arrival)

        count = [0] * k

        # Fenwick tree: initially every server is available
        bit = [0] + [i & -i for i in range(1, k + 1)]

        def update(i, val):
            i += 1
            while i <= k:
                bit[i] += val
                i += i & -i

        def query(i):
            s = 0
            while i > 0:
                s += bit[i]
                i -= i & -i
            return s

        def kth(target):
            idx = 0
            step = 1 << (k.bit_length() - 1)

            while step:
                nxt = idx + step
                if nxt <= k and bit[nxt] < target:
                    idx = nxt
                    target -= bit[nxt]
                step >>= 1

            return idx

        # [finishTime, serverIndex]
        busy = []

        max_count = 0

        for i in range(n):

            time = arrival[i]

            # Release completed servers
            while busy and busy[0][0] <= time:
                end, server = heapq.heappop(busy)
                update(server, 1)

            total = query(k)

            if total == 0:
                continue

            preferred = i % k

            # Number of available servers before preferred
            before = query(preferred)

            if total > before:
                server = kth(before + 1)
            else:
                server = kth(1)

            # Mark server as busy
            update(server, -1)

            count[server] += 1
            max_count = max(max_count, count[server])

            heapq.heappush(
                busy,
                (time + load[i], server)
            )

        return [
            i for i in range(k)
            if count[i] == max_count
        ]
