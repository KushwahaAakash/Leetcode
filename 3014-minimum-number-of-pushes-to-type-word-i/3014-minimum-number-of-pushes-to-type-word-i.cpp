class Solution {
public:
    int minimumPushes(string word) {
        vector<int> freq(26, 0);

        for (char c : word)
            freq[c - 'a']++;

        sort(freq.begin(), freq.end(), greater<int>());

        int ans = 0;
        int idx = 0;

        for (int f : freq) {
            if (f == 0) break;
            ans += f * (idx / 8 + 1);
            idx++;
        }

        return ans;
    }
};