
class Solution:
    def countDaysTogether(self, arriveAlice: str, leaveAlice: str,
                          arriveBob: str, leaveBob: str) -> int:

        def day(date):
            days = [31, 28, 31, 30, 31, 30,
                    31, 31, 30, 31, 30, 31]

            month = int(date[:2])
            d = int(date[3:])

            for i in range(month - 1):
                d += days[i]

            return d

        start = max(day(arriveAlice), day(arriveBob))
        end = min(day(leaveAlice), day(leaveBob))

        return max(0, end - start + 1)
