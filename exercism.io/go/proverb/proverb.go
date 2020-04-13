package proverb

import "fmt"

// Generate the proverb.
func Proverb(rhyme []string) []string {
	var p []string = make([]string, len(rhyme))

	// Generate the first N-1 sections.
	for i := 1; i < len(rhyme); i++ {
		p[i-1] = fmt.Sprintf("For want of a %s the %s was lost.", rhyme[i-1], rhyme[i])
	}

	if len(rhyme) > 0 {
		p[len(rhyme)-1] = fmt.Sprintf("And all for the want of a %s.", rhyme[0])
	}
	return p
}
