package grains

import (
	"errors"
	"math"
)

// Square returns the grains in the n-th square.
func Square(n int) (uint64, error) {
	if n <= 0 || n > 64 {
		return 0, errors.New("square id must be between 1 - 64")
	}
	return uint64(math.Pow(2.0, float64(n-1))), nil
}

// Total returns the total number of grains in the chess board.
func Total() uint64 {
	var sum uint64
	for i := 1; i <= 64; i++ {
		num, _ := Square(i)
		sum += num
	}
	return sum
}
