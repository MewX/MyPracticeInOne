package grains

import "errors"

// Square returns the grains in the n-th square.
func Square(n int) (uint64, error) {
	if n <= 0 || n > 64 {
		return 0, errors.New("square id must be between 1 - 64")
	}
	return 1 << (n - 1), nil
}

// Total returns the total number of grains in the chess board.
func Total() uint64 {
	// Every bit is set to 1 for a uint64.
	// return ^uint64(0)

	// Or, using 2^63 - 1. This operation won't trigger overflow error as it is
	// evaluated at compile time as constant literal value.
	// https://golang.org/ref/spec#Constant_expressions
	return 1<<64 - 1
}
