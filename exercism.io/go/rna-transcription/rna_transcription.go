package strand

import "bytes"

var ruleMap = map[byte]byte{
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U',
}

// ToRNA - Generate transcripted RNA.
func ToRNA(dna string) string {
	buf := bytes.NewBufferString("")
	for i := 0; i < len(dna); i++ {
		buf.WriteByte(ruleMap[dna[i]])
	}
	return buf.String()
}
