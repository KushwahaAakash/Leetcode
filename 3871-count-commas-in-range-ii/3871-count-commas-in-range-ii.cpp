class Solution {
public:
    long long countCommas(long long n) {
        long long ans = 0;
        
        // Start of numbers having at least 1 comma
        long long start = 1000;
        long long commas = 1;

        while (start <= n) {
            long long end = start * 1000 - 1;

            // Only count up to n
            long long count = min(n, end) - start + 1;

            ans += count * commas;

            start *= 1000;
            commas++;
        }

        return ans;
    }
};