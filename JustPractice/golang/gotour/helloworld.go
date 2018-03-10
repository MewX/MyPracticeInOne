package main

import (
	"fmt"
	"math"
	"math/rand"
	"math/cmplx"
	"time"
	"strconv"
	"runtime"
	"strings"
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

func printSlice(s []int) {
	fmt.Printf("len=%d cap=%d %v\n", len(s), cap(s), s)
}

/*
This is used for generating picture in golang tour! Very funny one!
 */
func Pic(dx, dy int) [][]uint8 {
	var ret = make([][]uint8, dx)
	for i := 0; i < dx; i ++ {
		ret[i] = make([]uint8, dy)
		for j := 0; j < dy; j ++ {
			ret[i][j] = uint8(i + j)
		}
	}
	return ret
}

/*
PASS
 f("I am learning Go!") =
  map[string]int{"learning":1, "Go!":1, "I":1, "am":1}
PASS
 f("The quick brown fox jumped over the lazy dog.") =
  map[string]int{"the":1, "lazy":1, "dog.":1, "fox":1, "jumped":1, "brown":1, "over":1, "The":1, "quick":1}
PASS
 f("I ate a donut. Then I ate another donut.") =
  map[string]int{"I":2, "ate":2, "a":1, "donut.":2, "Then":1, "another":1}
PASS
 f("A man a plan a canal panama.") =
  map[string]int{"panama.":1, "A":1, "man":1, "a":2, "plan":1, "canal":1}
 */
func WordCount(s string) map[string]int {
	var ret = make(map[string]int)
	for _, str := range strings.Split(s, " ") {
		_, cnt := ret[str]
		if cnt {
			ret[str] += 1
		} else {
			ret[str] = 1
		}
	}
	return ret
}

/*
function as parameter
 */
func compute(fn func(int, int) int) int {
	return fn(1, 2)
}

/*
function closures:
it returns an anonymous function
 */
func adder() func(int) int {
	sum := 0
	return func(x int) int {
		sum += x
		return sum
	}
}

/*
Exercise: Fibonacci closure
 */
func fibonacci() func() int {
	var a, b = 0, 1
	return func() int {
		a, b = b, a + b
		return b - a
	}
}

func powLocal(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	} else {
		fmt.Printf("%g >= %g\n", v, lim)
	}
	return lim
}

type myint int
var globalVar myint // default 0

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

	// pointers
	var pointer = &t
	fmt.Println(pointer.Hour())
	*pointer = time.Date(1999,1,1,1,1,1,1, time.UTC)
	fmt.Println(pointer.Hour())

	// struct
	type Vertex struct {
		X, Y int
	}
	fmt.Println(Vertex{1, 2})
	fmt.Println(Vertex{X: 1})

	// arrays, those google freaks!
	var arr [2]int
	arr[0] = 123
	arr[1] = 456
	fmt.Println(arr[0], arr[1])
	fmt.Println(arr)
	primes := []int{2, 3, 5, 7, 11, 13}
	primes[1:4][0] = 1 // 3 is changed to 1 because:
	fmt.Println(primes[1:4]) // note the slices are references to the original array

	// these interesting slices
	s := []int{2, 3, 5, 7, 11, 13}
	printSlice(s)
	s = s[:0] // Slice the slice to give it zero length.
	printSlice(s)
	s = s[:4] // Extend its length.
	printSlice(s)
	s = s[2:] // Drop its first two values.
	printSlice(s)

	// nil
	var snil [] string
	if snil == nil {
		// nil is not null!
		fmt.Println(snil)
	}

	// dynamic slice
	fmt.Println(make([]int, 5))
	fmt.Println(cap(make([]int, 5)))
	fmt.Println(make([]int, 0, 5))
	fmt.Println(cap(make([]int, 0, 5)))
	fmt.Println(append(make([]int, 0, 5), 9, 8, 7, 6, 5, 4))

	// range
	for i, v := range s {
		fmt.Printf("?**%d = %d\n", i, v)
	}
	for _, v := range s {
		fmt.Printf("%d\n", v)
	}

	// map
	m := make(map[string]Vertex)
	m["Bell Labs"] = Vertex{
		40, -74,
	}
	fmt.Println(m["Bell Labs"])
	// literals
	m = map[string]Vertex{
		"test1": {1, 2},
		"test2": {2, 1},
	}
	delete(m, "test1")
	fmt.Println(m)
	elem, ok := m["test2"]
	fmt.Println(elem, ok)
	elem, ok = m["test1"] // ok == false means not exist
	fmt.Println(elem, ok)

	// function closure
	fmt.Println(adder()(1))
	fmt.Println(adder()(2)) // the function variable is remembered

}
