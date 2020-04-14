package gigasecond

import "time"

var gigaSecond, _ = time.ParseDuration("1000000000s")

// AddGigasecond returns the time that is one gigasecond after input time.
func AddGigasecond(t time.Time) time.Time {
	return t.Add(gigaSecond)
}
