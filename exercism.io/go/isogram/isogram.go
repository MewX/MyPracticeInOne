package isogram

import (
	"unicode"
)

// IsIsogram checks whether the input string is an isogram.
func IsIsogram(str string) bool {
	mem := make(map[rune]bool)
	for _, originalR := range str {
		r := unicode.ToLower(originalR)
		if !unicode.IsLetter(r) {
			continue
		}

		if mem[r] {
			return false
		}
		mem[r] = true
	}

	return true
}
