package isogram

import (
	"strings"
	"unicode"
)

// IsIsogram checks whether the input string is an isogram.
func IsIsogram(str string) bool {
	mem := make(map[rune]int)
	for _, r := range strings.ToLower(str) {
		if unicode.IsLetter(r) {
			mem[r] = mem[r] + 1
		}
	}

	for _, v := range mem {
		if v > 1 {
			return false
		}
	}
	return true
}
