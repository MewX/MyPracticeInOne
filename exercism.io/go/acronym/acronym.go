package acronym

import (
	"strings"
	// Alternatively.
	// u "unicode"
)

// Abbreviate generates the acronym for the input string.
func Abbreviate(s string) string {
	sections := strings.FieldsFunc(s, doSplit)

	var sb strings.Builder
	for _, sec := range sections {
		sb.WriteByte(sec[0] & ^byte(0x20))

		// Alternatively.
		//sb.WriteByte(byte(int8(sec[0]) & ^0x20))
		// Or.
		// sb.WriteByte(byte(u.ToUpper(rune(sec[0]))))
	}
	return sb.String()
}

// doSplit returns true if we want to split at selected characters.
func doSplit(r rune) bool {
	return r == '-' || r == ' ' || r == '_'
}
