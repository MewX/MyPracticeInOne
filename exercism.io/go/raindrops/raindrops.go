package raindrops

import (
	"strconv"
	"strings"
)

// Convert a number into a string that contains rain drop sounds.
func Convert(n int) string {
	var sb strings.Builder

	if n%3 == 0 {
		sb.WriteString("Pling")
	}
	if n%5 == 0 {
		sb.WriteString("Plang")
	}
	if n%7 == 0 {
		sb.WriteString("Plong")
	}

	// Not any cases tested above.
	if sb.Len() == 0 {
		sb.WriteString(strconv.Itoa(n))
	}
	return sb.String()
}
