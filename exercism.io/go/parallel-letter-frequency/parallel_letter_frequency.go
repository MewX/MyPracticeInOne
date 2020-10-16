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
func freqencyWorker(s string, wg *sync.WaitGroup, m *sync.Map) {
	defer wg.Done()
	fm := Frequency(s)
	for k, v := range fm {
		ori, ok := m.Load(k)
		if !ok {
			ori = 0
		}
		m.Store(k, v+ori.(int))
	}
}

// ConcurrentFrequency calculates the rune frequency from all strings
// concurrently.
func ConcurrentFrequency(strs []string) FreqMap {
	// Thread-safe map
	var m sync.Map
	var wg sync.WaitGroup
	for _, str := range strs {
		wg.Add(1)
		go freqencyWorker(str, &wg, &m)
	}
	wg.Wait()

	// Save to FreqMap.
	fm := FreqMap{}
	m.Range(func(k, v interface{}) bool {
		fm[k.(rune)] = v.(int)
		return true
	})
	return fm
}
