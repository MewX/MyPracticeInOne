package scale

import (
	"fmt"
	"os"
	"strings"
)

// SFType indicates the scale is sharp or flat or none.
type SFType string

// Defining SFType as an enum.
const (
	SFNone SFType = "N"
	Sharp  SFType = "S"
	Flat   SFType = "F"
)

func oneOf(target string, list []string) int {
	for id, item := range list {
		if item == target {
			return id
		}
	}
	return -1
}

func getSharpOrFlat(tonic string) SFType {
	if tonic == "C" {
		return SFNone
	} else if oneOf(tonic, []string{"G", "D", "A", "E", "B", "F#", "a", "e", "b", "f#", "c#", "g#", "d#"}) >= 0 {
		return Sharp
	} else if oneOf(tonic, []string{"F", "Bb", "Eb", "Ab", "Db", "Gb", "d", "g", "c", "f", "bb", "eb"}) >= 0 {
		return Flat
	}

	fmt.Println("Unable to detect sharp or flat: " + tonic)
	os.Exit(1)
	return SFNone
}

func translateInterval(interval string) int {
	switch interval {
	case "m":
		return 1
	case "M":
		return 2
	case "A":
		return 3
	}
	fmt.Println("Unable to detect interval type: " + interval)
	os.Exit(2)
	return 0
}

func nextTonic(current string, itvl int, sf SFType) string {
	var scale []string
	if sf == SFNone || sf == Sharp {
		scale = []string{"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"}
	} else {
		scale = []string{"A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab"}
	}

	i := oneOf(current, scale)
	if i < 0 {
		fmt.Println("Unable to find tonic in scale: " + current)
		os.Exit(3)
	}

	return scale[(i+itvl)%len(scale)]
}

// Scale returns an array of tonics in a full scale.
func Scale(tonic string, interval string) []string {
	// Detect scale SF type.
	sf := getSharpOrFlat(tonic)

	// First letter must be in upper case
	t := strings.Title(tonic)

	var ret []string
	if len(interval) != 0 {
		ret = make([]string, len(interval))
		ret[0] = t
		for i := 1; i < len(interval); i++ {
			ret[i] = nextTonic(ret[i-1], translateInterval(interval[i-1:i]), sf)
		}
	} else {
		ret = make([]string, 12)
		ret[0] = t
		for i := 1; i < 12; i++ {
			ret[i] = nextTonic(ret[i-1], 1, sf)
		}
	}
	return ret
}
