package luhn

// Valid tells if the input string follows the Luhn formula.
func Valid(str string) bool {
	var sum, count int
	var secondNumberFlipper bool
	for i := len(str) - 1; i >= 0; i-- {
		// Can be space.
		if str[i] == ' ' {
			continue
		}

		// Returning false if containing non-digits.
		if str[i] < '0' || '9' < str[i] {
			return false
		}

		num := int(str[i] - '0')
		if secondNumberFlipper {
			// Double every second digit.
			num += num
			if num > 9 {
				num -= 9
			}
		}
		sum += num
		count++

		// Flip the flipper.
		secondNumberFlipper = !secondNumberFlipper
	}
	return count > 1 && sum%10 == 0
}
