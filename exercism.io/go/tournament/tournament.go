package tournament

import (
	"bufio"
	"errors"
	"fmt"
	"io"
	"sort"
	"strings"
)

type team struct {
	matchPlayed int
	win         int
	draw        int
	loss        int
	points      int
}

// Tally calculates the competition results.
func Tally(reader io.Reader, writer io.Writer) error {
	// Read each line from the reader and parse them into 3-token slices.
	lines := [][]string{}
	scanner := bufio.NewScanner(reader)
	for scanner.Scan() {
		line := scanner.Text()
		if line == "" || strings.HasPrefix(line, "#") {
			continue
		}

		secs := strings.Split(line, ";")
		if len(secs) != 3 {
			return errors.New("input format wrong for line: " + line)
		}
		lines = append(lines, secs)
	}

	// Update team match based on the inputs.
	teamMap := map[string]team{}
	for _, sections := range lines {
		a, b := teamMap[sections[0]], teamMap[sections[1]]
		a.matchPlayed++
		b.matchPlayed++
		switch sections[2] {
		case "win":
			a.win++
			b.loss++
			a.points += 3
		case "draw":
			a.draw++
			b.draw++
			a.points++
			b.points++
		case "loss":
			a.loss++
			b.win++
			b.points += 3
		default:
			return errors.New("unknown match result: " + sections[2])
		}
		teamMap[sections[0]], teamMap[sections[1]] = a, b
	}

	// Write match results.
	names := make([]string, 0, len(teamMap))
	for key := range teamMap {
		names = append(names, key)
	}

	sort.Slice(names, func(i, j int) bool {
		pti, ptj := teamMap[names[i]].points, teamMap[names[j]].points
		return pti > ptj || pti == ptj && names[i] < names[j]
	})

	fmt.Fprintf(writer, "%-30s | MP |  W |  D |  L |  P\n", "Team")
	for _, name := range names {
		t := teamMap[name]
		fmt.Fprintf(writer, "%-30s | %2d | %2d | %2d | %2d | %2d\n",
			name, t.matchPlayed, t.win, t.draw, t.loss, t.points)
	}
	return nil
}
