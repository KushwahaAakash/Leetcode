class Solution:
    def canAliceWin(self, n: int) -> bool:
        move = 10

        while n >= move:
            n -= move
            move -= 1

        return move % 2 == 1