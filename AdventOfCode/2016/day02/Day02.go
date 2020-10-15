package main

import (
	"bufio"
	"fmt"
	"os"
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
			key = move2(key, c)
		}
		ret += fmt.Sprintf("%X", key)
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

var tops = map[int]bool{5: true, 2: true, 1: true, 4: true, 9: true}
var bottoms = map[int]bool{5: true, 10: true, 13: true, 12: true, 9: true}
var lefts = map[int]bool{5: true, 2: true, 1: true, 10: true, 13: true}
var rights = map[int]bool{9: true, 4: true, 1: true, 13: true, 12: true}

// move2 is based on another keypad.
func move2(key int, op byte) int {
	fmt.Printf("(%X then %c)", key, op)
	switch op {
	case 'L':
		if _, ok := lefts[key]; ok {
			return key
		}
		return key - 1
	case 'R':
		if _, ok := rights[key]; ok {
			return key
		}
		return key + 1
	case 'U':
		if _, ok := tops[key]; ok {
			return key
		} else if key >= 13 {
			return key - 2
		} else if key >= 5 {
			return key - 4
		} else if key >= 2 {
			return key - 2
		}
	case 'D':
		if _, ok := bottoms[key]; ok {
			return key
		} else if key <= 1 {
			return key + 2
		} else if key <= 9 {
			return key + 4
		} else if key <= 12 {
			return key + 2
		}
	}
	return -1
}