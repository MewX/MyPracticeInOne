package tournament

import (
	"bufio"
	"errors"
	"fmt"
	"io"
	"sort"
	"strings"
)

var teamMap map[string]*team
var matchResultInverseMap = map[string]string{
	"win":  "loss",
	"loss": "win",
	"draw": "draw",
}

type team struct {
	name        string
	matchPlayed int
	win         int
	draw        int
	loss        int
	points      int
}

func (t *team) addMatch(result string) {
	t.matchPlayed++
	switch result {
	case "win":
		t.win++
		t.points += 3
	case "draw":
		t.draw++
		t.points++
	case "loss":
		t.loss++
	}
}

func addMatchForTeam(tname string, result string) {
	if _, ok := teamMap[tname]; !ok {
		teamMap[tname] = &team{
			name: tname,
		}
	}
	teamMap[tname].addMatch(result)
}

func generateMatchResults() string {
	l := make([]*team, 0, len(teamMap))
	for _, t := range teamMap {
		l = append(l, t)
	}

	sort.Slice(l, func(i, j int) bool {
		return l[i].points > l[j].points ||
			l[i].points == l[j].points &&
				strings.Compare(l[i].name, l[j].name) < 0
	})

	s := "Team                           | MP |  W |  D |  L |  P\n"
	for _, t := range l {
		s += fmt.Sprintf("%-30s | %2d | %2d | %2d | %2d | %2d\n",
			t.name, t.matchPlayed, t.win, t.draw, t.loss, t.points)
	}
	return s
}

// Tally calculates the competition results.
func Tally(reader io.Reader, writer io.Writer) error {
	teamMap = map[string]*team{}
	r := bufio.NewReader(reader)
	for true {
		bys, _, err := r.ReadLine()
		if err != nil {
			break
		} else if len(bys) == 0 || bys[0] == '#' {
			continue
		}

		secs := strings.Split(string(bys), ";")
		if len(secs) != 3 {
			return errors.New("input format wrong")
		} else if _, ok := matchResultInverseMap[secs[2]]; !ok {
			return errors.New("unrecognized match result: " + secs[2])
		}

		addMatchForTeam(secs[0], secs[2])
		addMatchForTeam(secs[1], matchResultInverseMap[secs[2]])
	}

	writer.Write([]byte(generateMatchResults()))
	return nil
}
