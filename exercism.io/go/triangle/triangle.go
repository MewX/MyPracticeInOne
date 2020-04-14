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

// KindFromSides returns the triangle kind based on 3 side lengths from input.
func KindFromSides(a, b, c float64) Kind {
	// Check if input is a valid triangle.
	if math.IsNaN(a) || math.IsNaN(b) || math.IsNaN(c) {
		return NaT
	}
	if math.IsInf(a, 0) || math.IsInf(b, 0) || math.IsInf(c, 0) {
		return NaT
	}
	if a <= 0 || b <= 0 || c <= 0 {
		return NaT
	}
	if a+b < c || a+c < b || b+c < a {
		return NaT
	}

	// Check if is equilateral.
	if a == b && b == c {
		return Equ
	}

	// Check if is isosceles.
	if a == b || a == c || b == c {
		return Iso
	}

	// Normal triangle.
	return Sca
}
