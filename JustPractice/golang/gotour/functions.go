package main

import (
	"fmt"
	"math"
	"reflect"
	"time"
	"sync"
)

// Exercise: Stringers
// override .toString() function
type IPAddr [4]byte
func (ip IPAddr) String() string {
	return fmt.Sprintf("%v.%v.%v.%v", ip[0], ip[1], ip[2], ip[3])
}

type MyFloat float64
type Vertex struct {
	X, Y float64
}

type EmptyInterface interface {}

type Abser interface {
	Abs() float64
}

func (v Vertex) Abs() float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

func (f MyFloat) Abs() float64 {
	return math.Abs(float64(f))
}

func (v *Vertex) Scale(f float64) {
	v.X *= f
	v.Y *= f
}

type MyError struct {
	When time.Time
	What string
}

func (e *MyError) Error() string {
	return fmt.Sprintf("at %v, %s",
		e.When, e.What)
}

func run() error {
	return &MyError{
		time.Now(),
		"it didn't work",
	}
}

func say(s string) {
	for i := 0; i < 5; i ++ {
		time.Sleep(100 * time.Microsecond)
		fmt.Println(s)
	}
}

func sum(s []int, c chan int) {
	sum := 0
	for _, v := range s {
		sum += v
	}
	c <- sum
	//close(c) // cannot be invoked after closing
}

func fib(c, quit chan int) {
	x, y := 0, 1
	for {
		select {
		case c <- x:
			x, y = y, x+y
		case <-quit:
			fmt.Println("quit")
			return
		}
	}
}

type SafeCounter struct {
	v map[string] int
	mux sync.Mutex
}

func (c *SafeCounter) Inc (key string) {
	c.mux.Lock()
	c.v[key] ++
	c.mux.Unlock()
}

func (c *SafeCounter) Value(key string) int {
	c.mux.Lock()
	defer c.mux.Unlock()
	return c.v[key]
}

func main() {
	fmt.Println("Another main function")

	// val function call
	v := Vertex{3, 4}
	fmt.Println(v.Abs())

	// custom type
	floatTemp := MyFloat(-1.0)
	fmt.Println(floatTemp.Abs())

	// ref function call
	v.Scale(10)
	fmt.Println(v.Abs())

	// interface tests
	var a Abser
	if a == nil {
		fmt.Println(a)
		//fmt.Println(a.Abs()) // null pointer exception
	}
	f := MyFloat(-math.Sqrt2)
	v = Vertex{-3, -4}

	a = f // MyFloat implements Abs()
	fmt.Println(a.Abs())
	fmt.Printf("(%v, %T)\n", a, a)
	a = &v // a *Vertex implements Abser
	fmt.Println(a.Abs())
	fmt.Printf("(%v, %T)\n", a, a)
	// Note: if the implementation of the interface is for *Vertex receiver
	// the following version won't pass the compilation
	a = v // a vertex implements Abs()
	fmt.Println(a.Abs())
	// interface values
	fmt.Printf("(%v, %T)\n", a, a)

	// empty interface
	var ei interface{}
	fmt.Printf("(%v, %T)\n", ei, ei)
	ei = 42
	fmt.Printf("(%v, %T)\n", ei, ei)
	ei = "string"
	fmt.Printf("(%v, %T)\n", ei, ei)

	// type assertions
	var testStr = ei.(string)
	fmt.Printf("(%v, %T)\n", testStr, testStr)
	//var testInt = ei.(int) // this will fail because the type is not correct
	//fmt.Printf("(%v, %T)\n", testInt, testInt)

	if reflect.TypeOf(ei).Kind() == reflect.String {
		fmt.Println("type is string kind")
	}
	if ei == reflect.Int {
		fmt.Println("type is int")
	} else if ei == reflect.String {
		fmt.Println("type is string")
	}

	// the follow results in a StackOverflow answer: https://stackoverflow.com/a/49206944/4206925
	var ia, ib Abser
	fmt.Println(reflect.TypeOf(ia))
	var ic EmptyInterface
	if ia == ib {
		fmt.Println("ia == ib because they are the same interfaces")
	}
	if ia == ic {
		fmt.Println("ia == ic because they are all <nil>")
	}
	ia = Vertex{1, 2}
	ic = 2
	if ia != ic {
		// https://golang.org/ref/spec#Comparison_operators
		// Interface values are comparable. Two interface values are equal if they have identical dynamic types
		// and equal dynamic values or if both have value nil.
		fmt.Println("ia != ic because they don't have the same values")
	}

	ia = Vertex{1, 2}
	ib = MyFloat(1)
	fmt.Println(reflect.TypeOf(ia))
	fmt.Println(reflect.TypeOf(ia).Kind())
	fmt.Println(reflect.TypeOf(ib))
	fmt.Println(reflect.TypeOf(ib).Kind())

	var ix interface{} = 3
	fmt.Println(reflect.TypeOf(ix))
	fmt.Println(reflect.TypeOf(ix).Kind())
	ix = Vertex{1, 2}
	fmt.Println(reflect.TypeOf(ix))
	fmt.Println(reflect.TypeOf(ix).Kind())

	if reflect.TypeOf(ia) != reflect.TypeOf(ib) {
		fmt.Println("Not equal typeOf")
	}
	if reflect.TypeOf(ia).Kind() != reflect.TypeOf(ib).Kind() {
		fmt.Println("Not equal kind")
	}

	if reflect.TypeOf(ia) == reflect.TypeOf(Vertex{}) {
		fmt.Println("self-defined")
	} else if reflect.TypeOf(ia).Kind() == reflect.Float64 {
		fmt.Println("basic types")
	}
	ib = Vertex{3, 4}
	if reflect.TypeOf(ia) == reflect.TypeOf(ib) {
		fmt.Println("Equal typeOf")
	}
	if reflect.TypeOf(ia).Kind() == reflect.TypeOf(ib).Kind() {
		fmt.Println("Equal kind")
	}

	// output: *main.Abser
	fmt.Println(reflect.TypeOf(reflect.ValueOf(&ia).Interface()))

	// compare using if
	if _, ok := ia.(Vertex); ok {
		fmt.Println("OK")
	}

	// compare using switch
	switch ia.(type) {
	case Vertex:
		// here v has type T
		fmt.Println("OK")
	case MyFloat:
		// here v has type S
	default:
		// no match; here v has the same type as i
	}

	// toString()
	hosts := map[string]IPAddr{
		"loopback":  {127, 0, 0, 1},
		"googleDNS": {8, 8, 8, 8},
	}
	for name, ip := range hosts {
		fmt.Printf("%v: %v\n", name, ip)
	}

	// Error
	if err := run(); err != nil {
		fmt.Println(err)
	}

	// go routines
	go say("world")
	say("hello")

	// channels
	arr := []int{1, 2, 3, 4, 5, 6, 7,}
	c := make(chan int)
	go sum (arr[:len(arr)/2], c)
	go sum (arr[len(arr)/2:], c)
	// the following order is random
	x := <-c
	y := <-c
	fmt.Println(x, y, x + y)

	// buffered channels
	ch := make(chan int, 2)
	ch <- 1
	ch <- 2
	fmt.Println(<-ch)
	fmt.Println(<-ch)

	// select go routines
	quit := make(chan int)
	go func() {
		for i := 0; i < 10; i++ {
			fmt.Println(<-c)
		}
		quit <- 0
	}()
	fib(c, quit)

	// mutex: lock and unlock
	ccc := SafeCounter{v: make(map[string] int)}
	for i := 0; ccc.Value("somekey") < 1000; i ++ {
		go ccc.Inc("somekey")
	}
	fmt.Println(ccc.Value("somekey"))

}
