package main

import (
	"bufio"
	"os"
	"io"
	"fmt"
	"crypto/md5"
)

func main() {
	reader := bufio.NewReader(os.Stdin)
	line, _, _ := reader.ReadLine()
	str := string(line)

	result := ""
	index := uint64(0)
	for len(result) < 8 {
		v := str + fmt.Sprint(index)

		// Calculate MD5.
		h := md5.New()
		io.WriteString(h, v)
		digest := fmt.Sprintf("%x", h.Sum(nil))
		// fmt.Printf("%s - %s", v, digest)

		if digest[:5] == "00000" {
			result += string(digest[5])
		} 
		index++
	}
	fmt.Println(result)
}
