class Solution {
    public boolean canAliceWin(int n) {
        int move = 10;
        int turns = 0;

        while (n >= move) {
            n -= move;
            move--;
            turns++;
        }

        return turns % 2 == 1;
    }
}