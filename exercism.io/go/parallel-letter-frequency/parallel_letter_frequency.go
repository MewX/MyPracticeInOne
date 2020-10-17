package letter

import "sync"

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
func freqencyWorker(s string, wg *sync.WaitGroup, m *FreqMap, mu *sync.Mutex) {
	defer wg.Done()
	fm := Frequency(s)
	for k, v := range fm {
		mu.Lock()
		ori, ok := (*m)[k]
		if !ok {
			ori = 0
		}
		(*m)[k] = ori + v
		mu.Unlock()
	}
}

// ConcurrentFrequency calculates the rune frequency from all strings
// concurrently.
func ConcurrentFrequency(strs []string) FreqMap {
	m := FreqMap{}
	var wg sync.WaitGroup
	var mu sync.Mutex
	for _, str := range strs {
		wg.Add(1)
		go freqencyWorker(str, &wg, &m, &mu)
	}
	wg.Wait()
	return m
}
