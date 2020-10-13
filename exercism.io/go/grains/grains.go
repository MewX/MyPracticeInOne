package grains

import "errors"

// Square returns the grains in the n-th square.
func Square(n int) (uint64, error) {
	if n <= 0 || n > 64 {
		return 0, errors.New("square id must be between 1 - 64")
	}
	return uint64(1) << (n - 1), nil
}

// Total returns the total number of grains in the chess board.
func Total() uint64 {
	// Every bit is set to 1 for a uint64.
	return ^uint64(0)
}
