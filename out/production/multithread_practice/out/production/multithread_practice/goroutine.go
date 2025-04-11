package main

import (
	"fmt"
	"time"
)

func greet(name string) {
	fmt.Printf("hello %s", name)
}

func printNumbers() {
	for i := 1; i <= 5; i++ {
		fmt.Printf("number %d", i)
		time.Sleep(time.Millisecond * 200)
	}

}

func main() {
	go greet("world")

	go printNumbers()

	time.Sleep(time.Second * 2)
}
