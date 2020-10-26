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

func addMatch(a team, b team, result string) (team, team, error) {
	var e error = nil
	a.matchPlayed++
	b.matchPlayed++
	switch result {
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
		e = errors.New("unknown match result")
	}
	return a, b, e
}

func writeMatchResults(teamMap map[string]team, w io.Writer) {
	names := make([]string, 0, len(teamMap))
	for key := range teamMap {
		names = append(names, key)
	}

	sort.Slice(names, func(i, j int) bool {
		ti, tj := teamMap[names[i]], teamMap[names[j]]
		return ti.points > tj.points ||
			ti.points == tj.points && names[i] < names[j]
	})

	fmt.Fprintf(w, "Team                           | MP |  W |  D |  L |  P\n")
	for _, name := range names {
		t := teamMap[name]
		fmt.Fprintf(w, "%-30s | %2d | %2d | %2d | %2d | %2d\n",
			name, t.matchPlayed, t.win, t.draw, t.loss, t.points)
	}
}

// Tally calculates the competition results.
func Tally(reader io.Reader, writer io.Writer) error {
	teamMap := map[string]team{}
	scanner := bufio.NewScanner(reader)
	for scanner.Scan() {
		line := scanner.Text()
		if line == "" || strings.HasPrefix(line, "#") {
			continue
		}

		secs := strings.Split(line, ";")
		if len(secs) != 3 {
			return errors.New("input format wrong")
		}

		var err error
		teamMap[secs[0]], teamMap[secs[1]], err =
			addMatch(teamMap[secs[0]], teamMap[secs[1]], secs[2])
		if err != nil {
			return err
		}
	}

	writeMatchResults(teamMap, writer)
	return nil
}
