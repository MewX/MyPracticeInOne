package s228

import (
	"reflect"
	"testing"
)

func testOneCase(t *testing.T, in []int, out []string) {
	actual := summaryRanges(in)
	if !reflect.DeepEqual(actual, out) {
		t.Errorf("expected %q, got %q", out, actual)
	}
}

func Test_summaryRanges(t *testing.T) {
	testOneCase(t, []int{0, 1, 2, 4, 5, 7}, []string{"0->2", "4->5", "7"})
	testOneCase(t, []int{0, 2, 3, 4, 6, 8, 9}, []string{"0", "2->4", "6", "8->9"})
}
