package erratum

import (
	"fmt"
)

// Use opens the resource and calls Frob(input).
func Use(o ResourceOpener, input string) (err error) {
	var rc Resource
	for rc, err = o(); err != nil; rc, err = o() {
		if _, yes := err.(TransientError); yes {
			continue
		}
		// Other error.
		return
	}
	defer rc.Close()

	defer func() {
		switch rerr := recover().(type) {
		case nil:
			// Do nothing
		case FrobError:
			rc.Defrob(rerr.defrobTag)
			err = rerr
		case error:
			err = rerr
		default:
			err = fmt.Errorf("panic from unknown error: %v", rerr)
		}
	}()
	rc.Frob(input)
	return err
}
