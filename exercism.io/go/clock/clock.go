package clock

import "fmt"

// Clock repersents a time in minutes.
type Clock struct {
	minutes int
}

// New returns the time represented in string, and makes sure the time is within
// [0,24) hours.
func New(h int, m int) Clock {
	// Normalize time.
	const minutesInADay = 24 * 60
	minutes := (h*60 + m) % minutesInADay
	if minutes < 0 {
		minutes += minutesInADay
	}
	return Clock{
		minutes: minutes,
	}
}

// String returns the string representation of the Time.
func (c Clock) String() string {
	return fmt.Sprintf("%02d:%02d", c.minutes/60, c.minutes%60)
}

// Subtract reduces the time with input minutes.
func (c Clock) Subtract(m int) Clock {
	return New(0, c.minutes-m)
}

// Add increases the time with input minutes.
func (c Clock) Add(m int) Clock {
	return New(0, c.minutes+m)
}
