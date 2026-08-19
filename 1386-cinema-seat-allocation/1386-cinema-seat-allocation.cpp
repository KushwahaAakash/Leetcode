class Solution {
public:
    int maxNumberOfFamilies(int n, vector<vector<int>>& reservedSeats) {
        unordered_map<int, int> rows;

        // Store reserved seats as bitmasks
        for (auto &seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                rows[row] |= (1 << (col - 2));
            }
        }

        // Initially every row can fit 2 families
        int ans = (n - rows.size()) * 2;

        for (auto &[row, mask] : rows) {
            bool left = true;
            bool middle = true;
            bool right = true;

            // Seats 2,3,4,5
            for (int i = 0; i < 4; i++) {
                if (mask & (1 << i)) {
                    left = false;
                }
            }

            // Seats 4,5,6,7
            for (int i = 2; i < 6; i++) {
                if (mask & (1 << i)) {
                    middle = false;
                }
            }

            // Seats 6,7,8,9
            for (int i = 4; i < 8; i++) {
                if (mask & (1 << i)) {
                    right = false;
                }
            }

            if (left && right) {
                ans += 2;
            }
            else if (left || middle || right) {
                ans += 1;
            }
        }

        return ans;
    }
};