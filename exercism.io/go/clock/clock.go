package clock

import "fmt"

// Clock repersents the hour and minute of a moment.
type Clock struct {
	hour   int
	minute int
}

// New returns the time represented in string.
func New(h int, m int) Clock {
	// Normalize time.
	minute := m % 60
	hour := h
	if minute < 0 {
		minute += 60
		hour--
	}

	hour = ((m / 60) + hour) % 24
	if hour < 0 {
		hour += 24
	}

	return Clock{
		hour:   hour,
		minute: minute,
	}
}

// String returns the string representation of the Time.
func (t Clock) String() string {
	return fmt.Sprintf("%02d:%02d", t.hour, t.minute)
}

// Subtract reduces the time with input minutes.
func (t Clock) Subtract(m int) Clock {
	return New(t.hour, t.minute-m)
}

// Add increases the time with input minutes.
func (t Clock) Add(m int) Clock {
	return New(t.hour, t.minute+m)
}
