
class Solution:
    def findTheDistanceValue(self, arr1, arr2, d):
        arr2.sort()
        count = 0

        for num in arr1:
            idx = self.lower_bound(arr2, num)

            valid = True

            if idx < len(arr2) and abs(arr2[idx] - num) <= d:
                valid = False

            if idx > 0 and abs(arr2[idx - 1] - num) <= d:
                valid = False

            if valid:
                count += 1

        return count

    def lower_bound(self, arr, target):
        left, right = 0, len(arr)

        while left < right:
            mid = left + (right - left) // 2

            if arr[mid] < target:
                left = mid + 1
            else:
                right = mid

        return left
