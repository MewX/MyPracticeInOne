package robotname

import (
	"errors"
	"fmt"
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
		fmt.Sprintf("%03d", r.Int31n(1000))
}

// Name creates a new robot name.
func (r *Robot) Name() (string, error) {
	if len(r.name) == 5 {
		return r.name, nil
	}

	// Generate a new name.
	if len(existingRobots) == 26*26*10*10*10 {
		return "", errors.New("exhausted robot names")
	}

	r.name = randomName()
	for existingRobots[r.name] {
		r.name = randomName()
	}
	existingRobots[r.name] = true
	return r.name, nil
}

// Reset clears the robot name.
func (r *Robot) Reset() {
	r.name = ""
}
