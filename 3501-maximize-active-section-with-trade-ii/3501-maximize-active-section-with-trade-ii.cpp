class Solution {
public:
    int evaluateEdge(int i, int L, int R, int segL, int segR,
                     vector<int>& type,
                     vector<int>& start,
                     vector<int>& endIdx) {

        if (i <= segL || i >= segR) return 0;
        if (type[i] == 0) return 0;

        int leftLen = 0;
        if (i - 1 == segL)
            leftLen = max(0, endIdx[i - 1] - L + 1);
        else
            leftLen = endIdx[i - 1] - start[i - 1] + 1;

        int rightLen = 0;
        if (i + 1 == segR)
            rightLen = max(0, R - start[i + 1] + 1);
        else
            rightLen = endIdx[i + 1] - start[i + 1] + 1;

        return leftLen + rightLen;
    }

    vector<int> maxActiveSectionsAfterTrade(string s, vector<vector<int>>& queries) {

        int n = s.size();
        int totalOnes = 0;

        for (char c : s)
            if (c == '1')
                totalOnes++;

        vector<int> typeList;
        vector<int> startList;
        vector<int> endList;

        for (int i = 0; i < n;) {

            int j = i;
            while (j < n && s[j] == s[i])
                j++;

            typeList.push_back(s[i] - '0');
            startList.push_back(i);
            endList.push_back(j - 1);

            i = j;
        }

        int N = typeList.size();

        vector<int> type = typeList;
        vector<int> start = startList;
        vector<int> endIdx = endList;

        vector<int> posToSeg(n);

        for (int i = 0; i < N; i++) {
            for (int j = start[i]; j <= endIdx[i]; j++)
                posToSeg[j] = i;
        }

        vector<int> gain(N, 0);

        for (int i = 1; i < N - 1; i++) {
            if (type[i] == 1) {
                gain[i] =
                    (endIdx[i - 1] - start[i - 1] + 1) +
                    (endIdx[i + 1] - start[i + 1] + 1);
            }
        }

        vector<int> lg(N + 1);

        for (int i = 2; i <= N; i++)
            lg[i] = lg[i / 2] + 1;

        int K = lg[N] + 1;

        vector<vector<int>> st(K, vector<int>(N));

        for (int i = 0; i < N; i++)
            st[0][i] = gain[i];

        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= N; i++) {
                st[j][i] = max(
                    st[j - 1][i],
                    st[j - 1][i + (1 << (j - 1))]
                );
            }
        }

        vector<int> res;

        for (auto &q : queries) {

            int L = q[0];
            int R = q[1];

            int segL = posToSeg[L];
            int segR = posToSeg[R];

            if (segR - segL < 2) {
                res.push_back(totalOnes);
                continue;
            }

            int maxGain = 0;

            maxGain = max(maxGain,
                evaluateEdge(segL + 1, L, R,
                             segL, segR,
                             type, start, endIdx));

            maxGain = max(maxGain,
                evaluateEdge(segR - 1, L, R,
                             segL, segR,
                             type, start, endIdx));

            if (segL + 2 <= segR - 2) {

                int left = segL + 2;
                int right = segR - 2;

                int j = lg[right - left + 1];

                maxGain = max(
                    maxGain,
                    max(st[j][left],
                        st[j][right - (1 << j) + 1])
                );
            }

            res.push_back(totalOnes + maxGain);
        }

        return res;
    }
};