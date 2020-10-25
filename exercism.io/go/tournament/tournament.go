package tournament

import (
	"bufio"
	"errors"
	"fmt"
	"io"
	"sort"
	"strings"
)

var teamMap map[string]team

type team struct {
	matchPlayed int
	win         int
	draw        int
	loss        int
	points      int
}

func addMatch(a team, b team, result string) (team, team) {
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
	}
	return a, b
}

func writeMatchResults(w io.Writer) {
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
	teamMap = map[string]team{}
	r := bufio.NewReader(reader)
	for true {
		bys, _, err := r.ReadLine()
		if err != nil {
			break
		} else if len(bys) == 0 || bys[0] == '#' {
			continue
		}

		secs := strings.Split(string(bys), ";")
		if len(secs) != 3 ||
			secs[2] != "draw" && secs[2] != "loss" && secs[2] != "win" {
			return errors.New("input format wrong")
		}

		teamMap[secs[0]], teamMap[secs[1]] =
			addMatch(teamMap[secs[0]], teamMap[secs[1]], secs[2])
	}

	writeMatchResults(writer)
	return nil
}
