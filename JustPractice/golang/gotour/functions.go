package main

import (
	"fmt"
	"math"
	"reflect"
	"time"
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

}
