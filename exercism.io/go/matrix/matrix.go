package matrix

import (
	"fmt"
	"strconv"
	"strings"
)

// Matrix represents an integer 2d slice [row][col].
type Matrix [][]int

// Rows returns independent copy of all the rows in the metrix.
func (m Matrix) Rows() [][]int {
	rows := make([][]int, len(m))
	for ir, r := range m {
		rows[ir] = make([]int, len(r))
		for ic, c := range r {
			rows[ir][ic] = c
		}
	}
	return rows
}

// Cols returns independent copy of all the columns in the metrix.
func (m Matrix) Cols() [][]int {
	if len(m) == 0 {
		return [][]int{}
	}

	cols := [][]int{}
	for c := 0; len(m) > 0 && c < len(m[0]); c++ {
		col := make([]int, len(m))
		for r := 0; r < len(m); r++ {
			col[r] = m[r][c]
		}
		cols = append(cols, col)
	}
	return cols
}

// Set updates the value in Row r and Column c.
func (m Matrix) Set(r, c, val int) bool {
	if r < 0 || len(m) <= r || c < 0 || len(m[r]) <= c {
		return false
	}
	m[r][c] = val
	return true
}

// New creates a new matrix based on input.
func New(input string) (Matrix, error) {
	matrix := Matrix{}
	for i, inputRow := range strings.Split(input, "\n") {
		valueStrs := strings.FieldsFunc(
			inputRow, func(c rune) bool { return c == ' ' })
		if i != 0 && len(matrix[0]) != len(valueStrs) {
			return nil, fmt.Errorf(
				"input row \"%s\" size (%d) doesn't match first row size (%d)",
				inputRow, len(valueStrs), len(matrix[0]))
		}

		values := []int{}
		for _, valStr := range valueStrs {
			val, err := strconv.Atoi(valStr)
			if err != nil {
				return nil, fmt.Errorf("invalid value \"%s\"", valStr)
			}
			values = append(values, val)
		}
		matrix = append(matrix, values)
	}
	return matrix, nil
}
