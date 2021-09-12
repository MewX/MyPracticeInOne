package s228

import "fmt"

func summaryRanges(nums []int) []string {
	var ret []string
	start, end := -1, -1
	for i := 0; i < len(nums)+1; i++ {
		if start == -1 {
			start = i
			continue
		}

		// Note: the above guarantees i > 0.
		if i < len(nums) && nums[i] == nums[i-1]+1 {
			end = i
			continue
		} else {
			i--
		}

		// Reaching here when nums[i] < nums[i - 1] OR i == len(nums).
		if end == -1 {
			ret = append(ret, fmt.Sprintf("%d", nums[start]))
		} else {
			ret = append(ret, fmt.Sprintf("%d->%d", nums[start], nums[end]))
		}
		start, end = -1, -1
	}
	return ret
}
