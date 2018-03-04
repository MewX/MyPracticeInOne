package main

import (
	"fmt"
	"math"
	"math/rand"
	"math/cmplx"
	"time"
)

func add(x, y int,) int {
	return x + y
}

// multiple return
func swapStrings(a, b string) (string, string) {
	return b, a
}

// naked return
func split(sum int) (x, y int) {
	x = sum * 4/ 9
	y = sum - x
	return
}

var globalVar int // default 0

func main() {
	fmt.Println("Hello world!")
	fmt.Println("The time is", time.Now())

	rand.Seed(time.Now().UTC().UnixNano())
	fmt.Println("The random namber is: ", rand.Intn(10))
	fmt.Printf("Interesting number %g.\n", math.Sqrt(7))

	fmt.Println(add(12, 24))

	a,b := swapStrings("AAA", "BBB")
	fmt.Println(a, b)

	// use global var
	var localVar bool // default false
	fmt.Println(globalVar, localVar)

	// initialise many variables
	const c, python, java = true, false, "no!"
	fmt.Println(c, python, java)

	// types
	sampleType := "yes!" // type explicit
	fmt.Println(sampleType)

	var (
		factoredVar1 = false
		factoredVar2 uint64 = 1 << (64 - 1)
		factoredVar3 complex128 = cmplx.Sqrt(-5 + 12i)
	)
	fmt.Printf("Type %T, value: %v\n", factoredVar1, factoredVar1)
	fmt.Printf("Type %T, value: %v\n", factoredVar2, factoredVar2)
	fmt.Printf("Type %T, value: %v\n", factoredVar3, factoredVar3)

	// auto const type
	const big = 1 << 99
	//fmt.Println(int(big)) // overflows
	fmt.Println(float64(big))

}
