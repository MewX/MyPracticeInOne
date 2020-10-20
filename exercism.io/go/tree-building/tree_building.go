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

// AppendChild appends another node to the Children list.
func (n *Node) AppendChild(node *Node) {
	n.Children = append(n.Children, node)
	sort.Slice(n.Children, func(i, j int) bool {
		return n.Children[i].ID < n.Children[j].ID
	})
}

// Build constructs the tree.
func Build(records []Record) (*Node, error) {
	// Adding 1 on size to avoid out of index when returning nodeList[0].
	nodeList := make([]*Node, len(records)+1)
	for _, r := range records {
		if r.ID < 0 || r.ID >= len(records) || nodeList[r.ID] != nil ||
			r.ID < r.Parent || r.ID == r.Parent && r.ID != 0 {
			return nil, errors.New("invalid node ID")
		}
		nodeList[r.ID] = &Node{
			ID: r.ID,
		}
	}

	// Now, all r.ID are guaranteed to be valid.
	for _, r := range records {
		if r.Parent < 0 || r.Parent >= len(records) ||
			nodeList[r.Parent] == nil {
			return nil, errors.New("invalid parent ID")
		} else if r.ID != 0 {
			nodeList[r.Parent].AppendChild(nodeList[r.ID])
		}
	}
	return nodeList[0], nil
}
