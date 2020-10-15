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
		line, _, err := reader.ReadLine()
		if err != nil {
			break
		}

		var a, b, c int
		fmt.Sscanf(string(line), "%d%d%d", &a, &b, &c)
		if validateTriangle(a, b, c) {
			fmt.Printf("%d %d %d is valid\n", a, b, c)
			count++
		} else {
			fmt.Printf("%d %d %d is NOT valid\n", a, b, c)
		}
	}
	fmt.Println(count)
}

func validateTriangle(a, b, c int) bool {
	if a*b*c <= 0 {
		return false
	}
	if a+b <= c || a+c <= b || b+c <= a {
		return false
	}
	return true
}
