package robotname

import (
	"errors"
	"math/rand"
	"time"
)

var r = rand.New(rand.NewSource(time.Now().UnixNano()))
var existingRobots = make(map[string]bool)

// Robot is the robot.
type Robot struct {
	name string
}

func randomName() string {
	return string('A'+r.Int31n(26)) + string('A'+r.Int31n(26)) +
		string('0'+r.Int31n(10)) + string('0'+r.Int31n(10)) +
		string('0'+r.Int31n(10))
}

// Name creates a new robot name.
func (r *Robot) Name() (string, error) {
	if len(r.name) == 5 {
		return r.name, nil
	}

	// Generate a new name.
	for len(existingRobots) < 26*26*10*10*10 {
		name := randomName()
		if _, ok := existingRobots[name]; !ok {
			existingRobots[name] = true
			r.name = name
			return name, nil
		}
	}
	return "", errors.New("exhausted robot names")
}

// Reset clears the robot name.
func (r *Robot) Reset() {
	r.name = ""
}
