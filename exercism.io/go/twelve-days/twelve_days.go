package twelve

import (
	"fmt"
	"strings"
)

type gift struct {
	ordinal string
	names   string
}

var dayToGiftMap = map[int]gift{
	1:  {"first", "a Partridge in a Pear Tree"},
	2:  {"second", "two Turtle Doves"},
	3:  {"third", "three French Hens"},
	4:  {"fourth", "four Calling Birds"},
	5:  {"fifth", "five Gold Rings"},
	6:  {"sixth", "six Geese-a-Laying"},
	7:  {"seventh", "seven Swans-a-Swimming"},
	8:  {"eighth", "eight Maids-a-Milking"},
	9:  {"ninth", "nine Ladies Dancing"},
	10: {"tenth", "ten Lords-a-Leaping"},
	11: {"eleventh", "eleven Pipers Piping"},
	12: {"twelfth", "twelve Drummers Drumming"},
}

// Verse returns the line of lyrics for the input day.
func Verse(day int) string {
	gift, ok := dayToGiftMap[day]
	if !ok {
		panic(fmt.Sprintf("invalid input day: %d", day))
	}

	var sb strings.Builder
	sb.WriteString(fmt.Sprintf(
		"On the %s day of Christmas my true love gave to me: ", gift.ordinal))
	for d := day; d >= 1; d-- {
		giftConnector := ", "
		if d == 1 {
			if day > 1 {
				sb.WriteString("and ")
			}
			giftConnector = "."
		}
		sb.WriteString(dayToGiftMap[d].names + giftConnector)
	}
	return sb.String()
}

// Song returns the lyrics to 'The Twelve Days of Christmas'.
func Song() string {
	var sb strings.Builder
	for day := 1; day <= 12; day++ {
		sb.WriteString(Verse(day))
		if day != 12 {
			sb.WriteString("\n")
		}
	}
	return sb.String()
}
