package tree

import (
	"errors"
	"sort"
)

// Record is the raw input struct.
type Record struct {
	ID     int
	Parent int
}

// Node is the node of the tree.
type Node struct {
	ID       int
	Children []*Node
}

// Build constructs the tree.
func Build(records []Record) (*Node, error) {
	nodeList := make(map[int]*Node)
	sort.Slice(records, func(i, j int) bool {
		return records[i].ID < records[j].ID
	})

	for i, r := range records {
		if r.ID != i || r.ID < r.Parent || r.ID == r.Parent && r.ID != 0 {
			return nil, errors.New("invalid node ID or parent ID")
		}
		nodeList[r.ID] = &Node{r.ID, nil}
		if i != 0 {
			p := nodeList[r.Parent]
			p.Children = append(p.Children, nodeList[r.ID])
		}
	}
	return nodeList[0], nil
}
