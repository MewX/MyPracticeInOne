package main

import (
	"fmt"
	"math"
	"math/rand"
	"math/cmplx"
	"time"
	"strconv"
	"runtime"
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

func powLocal(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	} else {
		fmt.Printf("%g >= %g\n", v, lim)
	}
	return lim
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

	// for statement
	sum := 0
	for i := 0; i < 10; {
		sum += i
		i ++
	}
	fmt.Println("Sum", sum)

	// while loop
	for sum > 0 {
		sum -= 10
	}
	fmt.Println("sum: ", sum)

	// if statement
	if sum < 0 {
		sum = -sum
	}
	fmt.Println("sum: "+ strconv.Itoa(sum))

	// if else test
	fmt.Println(powLocal(3, 2, 10) + powLocal(3, 3, 20))

	// exercise
	const step = 0.00001
	const x = 2.0
	z := 1.0
	for math.Abs(z * z - x) > step {
		z -= (z*z - x) / (2*z)
		//fmt.Println(z)
	}
	fmt.Printf("%v's sqrt is %v\n", x, z)
	defer fmt.Println("Defer output!") // is at the end of the scope

	// switch with assignments
	fmt.Print("Go is running on ")
	switch os := runtime.GOOS; os {
	case "darwin":
		fmt.Println("OS X.")
	case "linux":
		fmt.Println("Linux.")
	default:
		fmt.Printf("%s.", os)
	}

	// switch with nothing
	fmt.Print("When's Saturday? ")
	today := time.Now().Weekday()
	switch time.Saturday {
	case today + 0:
		fmt.Println("today.")
	case today + 1:
		fmt.Println("tomorrow.")
	case today + 2:
		fmt.Println("two days later.")
	default:
		fmt.Println("too far away.")
	}

	// this is switch with nothing
	t := time.Now()
	switch {
	case t.Hour() < 12:
		fmt.Println("Good morning.")
	case t.Hour() < 17:
		fmt.Println("Good afternoon.")
	default:
		fmt.Println("Good evening.")
	}

}
