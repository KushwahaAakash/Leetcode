
import heapq

class Solution:
    def assignTasks(self, servers, tasks):

        available = [(w, i) for i, w in enumerate(servers)]
        heapq.heapify(available)

        busy = []
        ans = []
        time = 0

        for i, task in enumerate(tasks):

            time = max(time, i)

            while busy and busy[0][0] <= time:
                end, weight, idx = heapq.heappop(busy)
                heapq.heappush(available, (weight, idx))

            if not available:
                time = busy[0][0]

                while busy and busy[0][0] <= time:
                    end, weight, idx = heapq.heappop(busy)
                    heapq.heappush(available, (weight, idx))

            weight, idx = heapq.heappop(available)

            ans.append(idx)

            heapq.heappush(busy, (time + task, weight, idx))

        return ans
