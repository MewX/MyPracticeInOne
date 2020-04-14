package hamming

import (
	"errors"
)

// Distance of the two strings.
func Distance(a, b string) (int, error) {
	var ra = []rune(a)
	var rb = []rune(b)

	if len(ra) != len(rb) {
		return 0, errors.New("lengths do not match")
	}

	var c int
	for i := 0; i < len(ra); i++ {
		if ra[i] != rb[i] {
			c++
		}
	}
	return c, nil
}
