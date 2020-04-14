package bob

import "strings"

// Hey returns the words Bob should respond.
func Hey(remark string) string {
	trimmed := strings.TrimSpace(remark)
	empty := trimmed == ""
	if empty {
		return "Fine. Be that way!"
	}

	isQuestion := trimmed[len(trimmed)-1] == '?'
	isYelling := isYelling(&trimmed)
	if isQuestion && isYelling {
		return "Calm down, I know what I'm doing!"
	} else if isQuestion {
		return "Sure."
	} else if isYelling {
		return "Whoa, chill out!"
	}
	return "Whatever."
}

// hasLowerCase tests if a string is yelling.
func isYelling(s *string) bool {
	hasUpperCase := false
	for i := 0; i < len(*s); i++ {
		// One lowercase can indicate not yelling.
		if 'a' <= (*s)[i] && (*s)[i] <= 'z' {
			return false
		}

		// Must have at least one upper case to indicate yelling.
		if 'A' <= (*s)[i] && (*s)[i] <= 'Z' {
			hasUpperCase = true
		}
	}
	return hasUpperCase
}
