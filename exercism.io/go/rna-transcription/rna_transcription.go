package strand

var ruleMap = map[rune]string{
	'G': "C",
	'C': "G",
	'T': "A",
	'A': "U",
}

// ToRNA - Generate transcripted RNA.
func ToRNA(dna string) (ret string) {
	for _, i := range dna {
		ret += ruleMap[i]
	}
	return
}
