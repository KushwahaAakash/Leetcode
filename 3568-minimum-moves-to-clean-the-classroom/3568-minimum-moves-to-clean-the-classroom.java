import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = -1, sc = -1;

        // Assign an ID to every litter cell
        int[][] litterId = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
        }

        int litterCount = 0;

        // Find starting position and litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int fullMask = (1 << litterCount) - 1;

        /*
         * visited[row][col][mask][energy]
         */
        boolean[][][][] visited =
            new boolean[m][n][1 << litterCount][energy + 1];

        /*
         * State:
         * row, col, mask, remaining energy
         */
        class State {
            int r, c, mask, e;

            State(int r, int c, int mask, int e) {
                this.r = r;
                this.c = c;
                this.mask = mask;
                this.e = e;
            }
        }

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(sr, sc, 0, energy));
        visited[sr][sc][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                State cur = queue.poll();

                int r = cur.r;
                int c = cur.c;
                int mask = cur.mask;
                int e = cur.e;

                // All litter collected
                if (mask == fullMask) {
                    return moves;
                }

                // If no energy, we cannot move
                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // One move costs one energy
                    int newEnergy = e - 1;

                    int newMask = mask;

                    // Collect litter
                    if (litterId[nr][nc] != -1) {
                        newMask |= (1 << litterId[nr][nc]);
                    }

                    // Reset energy on R
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    // Visit this state only once
                    if (!visited[nr][nc][newMask][newEnergy]) {

                        visited[nr][nc][newMask][newEnergy] = true;

                        queue.offer(
                            new State(nr, nc, newMask, newEnergy)
                        );
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}