class Solution {
public:
    int gcd(int a, int b) {
        while (b) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    long long gcdSum(vector<int>& nums) {

        int n = nums.size();

        vector<int> prefix(n);

        int mx = 0;

        for (int i = 0; i < n; i++) {
            mx = max(mx, nums[i]);
            prefix[i] = gcd(nums[i], mx);
        }

        sort(prefix.begin(), prefix.end());

        long long ans = 0;

        int l = 0, r = n - 1;

        while (l < r) {
            ans += gcd(prefix[l], prefix[r]);
            l++;
            r--;
        }

        return ans;
    }
};