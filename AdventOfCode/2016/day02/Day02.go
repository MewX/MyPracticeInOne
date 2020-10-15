package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	// Starting key in the key pad.
	key := 5

	// Read each line
	ret := ""
	reader := bufio.NewReader(os.Stdin)
	for true {
		line, _, err := reader.ReadLine()
		if err != nil {
			break
		}

		for _, c := range line {
			key = move(key, c)
		}
		ret += strconv.Itoa(key)
	}
	fmt.Println()
	fmt.Println(ret)
}

// move returns the final key after the movement.
func move(key int, op byte) int {
	fmt.Printf("(%d then %c)", key, op)
	switch op {
	case 'L':
		if key%3 == 1 {
			return key
		}
		return key - 1
	case 'R':
		if key%3 == 0 {
			return key
		}
		return key + 1
	case 'U':
		if key <= 3 {
			return key
		}
		return key - 3
	case 'D':
		if key >= 7 {
			return key
		}
		return key + 3
	}
	return -1
}
