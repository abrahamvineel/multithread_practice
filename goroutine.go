package main

import (
	"fmt"
)

func greet(name string) {
	fmt.Printf("hello %s", name)
}

func main() {
	go greet("world")
}
