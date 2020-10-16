package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
	"unicode"
)

type kv struct {
	Key   rune
	Value int
}

func main() {
	count := 0
	reader := bufio.NewReader(os.Stdin)
	for true {
		input, _, err := reader.ReadLine()
		if err != nil {
			break
		}

		// Trim input.
		token := strings.TrimSpace(string(input))
		if len(token) < 7 {
			continue
		}

		checksum := calculateChecksum(token)
		cmp := extractChecksum(token)
		if checksum == cmp {
			fmt.Println(token + " is valid")
			n, _ := strconv.Atoi(extractSectorNum(token))
			count += n
		} else {
			fmt.Println(token + " is NOT valid - actual " + checksum)
		}
	}
	fmt.Println(count)
}

func calculateChecksum(s string) string {
	// Map token to frequency map.
	m := make(map[rune]int)
	for _, c := range s[:strings.Index(s, "[")] {
		if !unicode.IsLetter(c) {
			continue
		}

		if _, ok := m[c]; !ok {
			m[c] = 0
		}
		m[c]++
	}

	// Pick the top 5.
	var ss []kv
	for k, v := range m {
		ss = append(ss, kv{k, v})
	}
	sort.Slice(ss, func(i, j int) bool {
		// Descending by value; if same value, ascending by key.
		return ss[i].Value > ss[j].Value ||
			ss[i].Value == ss[j].Value && ss[i].Key < ss[j].Key
	})

	// The input have 5 or more letters.
	ret := ""
	for i := 0; i < 5; i++ {
		ret += string(ss[i].Key)
	}
	return ret
}

func extractChecksum(s string) string {
	return s[strings.Index(s, "[")+1 : strings.Index(s, "]")]
}

func extractSectorNum(s string) string {
	return s[strings.IndexAny(s, "0123456789") : strings.Index(s, "[")]
}
