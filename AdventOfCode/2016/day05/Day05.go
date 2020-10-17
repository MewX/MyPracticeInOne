package main

import (
	"bufio"
	"crypto/md5"
	"fmt"
	"io"
	"os"
	"strings"
)

func main() {
	reader := bufio.NewReader(os.Stdin)
	line, _, _ := reader.ReadLine()
	str := string(line)

	// Part 1
	// fmt.Println(findPart1(str))

	// Part 2
	fmt.Println(findPart2(str))
}

func calcDigest(str string) string {
	h := md5.New()
	io.WriteString(h, str)
	return fmt.Sprintf("%x", h.Sum(nil))
}

func findPart1(str string) string {
	result := ""
	index := uint64(0)
	for len(result) < 8 {
		v := str + fmt.Sprint(index)
		digest := calcDigest(v)
		// fmt.Printf("%s - %s", v, digest)

		if digest[:5] == "00000" {
			result += string(digest[5])
		}
		index++
	}
	return result
}

func findPart2(str string) string {
	result := "--------"
	index := uint64(0)
	for strings.Index(result, "-") >= 0 {
		v := str + fmt.Sprint(index)
		digest := calcDigest(v)

		if digest[:5] == "00000" {
			i := int(digest[5]) - '0'
			// fmt.Printf("%s - %d\n", digest, i)
			if 0 <= i && i < 8 && result[i] == '-' {
				result = result[:i] + string(digest[6]) + result[i+1:]
				fmt.Println(result)
			}
		}
		index++
	}
	return result
}
