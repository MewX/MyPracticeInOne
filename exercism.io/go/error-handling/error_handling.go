package erratum

// Use opens the resource and calls Frob(input).
func Use(o ResourceOpener, input string) (e error) {
	var rc Resource
	for rc, e = o(); e != nil; rc, e = o() {
		if _, yes := e.(TransientError); yes {
			continue
		}
		// Other error.
		return
	}
	defer rc.Close()

	defer func() {
		rerr := recover()
		if froberr, yes := rerr.(FrobError); yes {
			rc.Defrob(froberr.defrobTag)
		}
		if rerr != nil {
			e = rerr.(error)
		}
	}()
	rc.Frob(input)
	return
}
