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
}

// Build constructs the tree.
func Build(records []Record) (*Node, error) {
	if len(records) == 0 {
		return nil, nil
	}

	// Maintain a node index to track tree build.
	nodeMap := make(map[int]*Node)

	// Sort records by Parent, then by ID ascendingly.
	// This guarantees the order defined by the problem description: All records
	// have a parent ID lower than their own ID, except for the root record,
	// which has a parent ID that's equal to its own ID.
	sort.SliceStable(records, func(i, j int) bool {
		return records[i].Parent < records[j].Parent ||
			(records[i].Parent == records[j].Parent &&
				records[i].ID < records[j].ID)
	})

	maxID := 0
	for _, r := range records {
		// Check duplicates.
		if _, ok := nodeMap[r.ID]; ok {
			return nil, errors.New("duplicated ID")
		}

		// Adding the new node to the node map.
		node := Node{
			ID:       r.ID,
			Children: nil,
		}
		nodeMap[r.ID] = &node

		// Updating the parent node.
		if r.ID < r.Parent {
			return nil, errors.New("parent ID shouldn't be higher than ID")
		} else if r.ID == r.Parent {
			if r.ID != 0 {
				return nil, errors.New("input contains self-cycle")
			}
			// Else is the root node, which has been added and has no parent.
		} else {
			if _, ok := nodeMap[r.Parent]; !ok {
				return nil, errors.New("parent node does not exist")
			}
			nodeMap[r.Parent].AppendChild(&node)
		}

		// Update maxID so that we don't have to loop through the whole map to
		// check whether all values are within the range.
		if r.ID > maxID {
			maxID = r.ID
		}
	}

	// Check if the map is filled.
	if maxID >= len(records) {
		return nil, errors.New("ID is jumped")
	}

	// At this point, the map at least contains the root.
	return nodeMap[0], nil
}
