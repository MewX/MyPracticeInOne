package clock

import "fmt"

// Clock repersents the hour and minute of a moment.
type Clock struct {
	hour   int
	minute int
}

// New returns the time represented in string.
func New(h int, m int) Clock {
	t := Clock{
		hour:   h,
		minute: m,
	}
	return t.Add(0)
}

// String returns the string representation of the Time.
func (t Clock) String() string {
	return fmt.Sprintf("%02d:%02d", t.hour, t.minute)
}

// Subtract reduces the time with input minutes.
func (t Clock) Subtract(m int) Clock {
	return t.Add(-m)
}

// Add increases the time with input minutes.
func (t Clock) Add(m int) Clock {
	totalMinutes := m + t.minute
	t.minute = totalMinutes % 60
	if t.minute < 0 {
		t.minute += 60
		t.hour--
	}
	t.hour = ((totalMinutes / 60) + t.hour) % 24
	if t.hour < 0 {
		t.hour += 24
	}
	return t
}
