package hamming

import "errors"

func Distance(a, b string) (int, error) {
	if len(a) != len(b) {
		return -1, errors.New("Lengths do not match.")
	}

	var c int
	for i := 0; i < len(a); i ++ {
		if a[i] != b[i] {
			c += 1
		}
	}
  return c, nil
}
