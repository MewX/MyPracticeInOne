package diffsquares

// SquareOfSum returns the square of the sum of the first N natural numbers.
func SquareOfSum(n int) int {
	// Gauss sum.
	s := n * (n + 1) / 2
	return s * s
}

// SumOfSquares returns the sum of the squares of the first N natural numbers.
func SumOfSquares(n int) int {
	s := 0
	for i := 1; i <= n; i++ {
		s += i * i
	}
	return s
}

// Difference returns the difference between the square of the sum and the sum
// of the squares of the first N natural numbers.
func Difference(n int) int {
	return SquareOfSum(n) - SumOfSquares(n)
}
