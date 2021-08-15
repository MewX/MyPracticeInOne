package hello

import (
	"testing"
)

func Test_hello(t *testing.T) {
	expected := "hello"
	actual := hello()
	if actual != expected {
		t.Errorf("expected %q, got %q", expected, actual)
	}
}
