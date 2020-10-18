package letter

// FreqMap records the frequency of each rune in a given text.
type FreqMap map[rune]int

// Frequency counts the frequency of each rune in a given text and returns this
// data as a FreqMap.
func Frequency(s string) FreqMap {
	m := FreqMap{}
	for _, r := range s {
		m[r]++
	}
	return m
}

// ConcurrentFrequency calculates the rune frequency from all strings
// concurrently.
func ConcurrentFrequency(strs []string) FreqMap {
	c := make(chan *FreqMap, 10)
	for i := range strs {
		go func(index int) {
			m := Frequency(strs[index])
			c <- &m
		}(i)
	}

	// Merge all maps.
	m := FreqMap{}
	for range strs {
		for k, v := range *(<-c) {
			m[k] += v
		}
	}
	return m
}
