package triangle

import "math"

// Kind of triangles.
type Kind byte

const (
	// NaT - Not a triangle.
	NaT = 'N'
	// Equ - Equilateral triangle.
	Equ = 'E'
	// Iso - Isosceles triangle.
	Iso = 'I'
	// Sca - Scalene triangle.
	Sca = 'S'
)

func notTriangle(a, b, c float64) bool {
	// Check if input is NOT a valid triangle.
	return a*b*c <= 0 || a+b < c || b+c < a || a+c < b || math.IsInf(a+b+c, 0) || math.IsNaN(a+b+c)
}

// KindFromSides returns the triangle kind based on 3 side lengths from input.
func KindFromSides(a, b, c float64) Kind {
	switch {
	case notTriangle(a, b, c):
		return NaT
	case a == b && b == c:
		return Equ
	case a == b, a == c, b == c:
		return Iso
	default:
		return Sca
	}
}
