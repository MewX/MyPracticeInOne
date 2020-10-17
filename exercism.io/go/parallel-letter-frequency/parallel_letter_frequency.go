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

// freqencyWorker calculates rune frequency in a single string, and update the
// shared frequency map.
func freqencyWorker(s string, c chan FreqMap) {
	c <- Frequency(s)
}

// ConcurrentFrequency calculates the rune frequency from all strings
// concurrently.
func ConcurrentFrequency(strs []string) FreqMap {
	c := make(chan FreqMap, len(strs))
	for _, str := range strs {
		go freqencyWorker(str, c)
	}

	// Merge all maps.
	m := FreqMap{}
	for range strs {
		for k, v := range <-c {
			m[k] += v
		}
	}
	return m
}
