package main

import (
	"bufio"
	"fmt"
	"os"
)

// main does the triangle validation.
func main() {
	count := 0
	reader := bufio.NewReader(os.Stdin)
	for true {
		line1, _, err := reader.ReadLine()

		// BUG: https://github.com/golang/go/issues/42006
		copyOfLine1 := make([]byte, len(line1))
		copy(copyOfLine1, line1)

		if err != nil {
			break
		}
		line2, _, _ := reader.ReadLine()
		copyOfLine2 := make([]byte, len(line2))
		copy(copyOfLine2, line2)

		line3, _, _ := reader.ReadLine()
		copyOfLine3 := make([]byte, len(line3))
		copy(copyOfLine3, line3)

		var a1, b1, c1 int
		var a2, b2, c2 int
		var a3, b3, c3 int
		fmt.Sscanf(string(copyOfLine1), "%d%d%d", &a1, &a2, &a3)
		fmt.Sscanf(string(copyOfLine2), "%d%d%d", &b1, &b2, &b3)
		fmt.Sscanf(string(copyOfLine3), "%d%d%d", &c1, &c2, &c3)
		count += validateTriangle(a1, b1, c1) +
			validateTriangle(a2, b2, c2) +
			validateTriangle(a3, b3, c3)
	}
	fmt.Println(count)
}

func validateTriangle(a, b, c int) int {
	ret := 1
	if a*b*c <= 0 {
		ret = 0
	} else if a+b <= c || a+c <= b || b+c <= a {
		ret = 0
	}

	if ret != 0 {
		fmt.Printf("%d %d %d is valid\n", a, b, c)
	} else {
		fmt.Printf("%d %d %d is NOT valid\n", a, b, c)
	}
	return ret
}
