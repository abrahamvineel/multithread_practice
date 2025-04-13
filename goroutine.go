package main

// import (
// 	"fmt"
// 	"time"
// )

// func greet(name string) {
// 	fmt.Printf("hello %s", name)
// }

// func printNumbers() {
// 	for i := 1; i <= 5; i++ {
// 		fmt.Printf("number %d", i)
// 		time.Sleep(time.Millisecond * 200)
// 	}

// }

// func main() {
// 	go greet("world")

// 	go printNumbers()

// 	time.Sleep(time.Second * 2)
// }

// import (
// 	"fmt"
// 	"runtime"
// 	"strconv"
// 	"strings"
// 	"sync"
// 	"time"
// )

// type SharedCounter struct {
// 	counter int
// 	mu      sync.Mutex
// }

// func (sc *SharedCounter) Increment() {
// 	sc.mu.Lock()
// 	defer sc.mu.Unlock()

// 	sc.counter++
// 	fmt.Printf("Incremented counter to: %d (by goroutine %d) \n",
// 		sc.counter, getGoroutineId())
// 	time.Sleep(time.Millisecond * 10)
// }

// func (sc *SharedCounter) GetCount() int {
// 	sc.mu.Lock()
// 	defer sc.mu.Unlock()
// 	return sc.counter
// }

// func getGoroutineId() int {
// 	var buf [64]byte
// 	n := runtime.Stack(buf[:], false)
// 	idField := strings.Fields(strings.TrimPrefix(string(buf[:n]), "goroutine "))[0]
// 	id, err := strconv.Atoi(idField)
// 	if err != nil {
// 		panic(fmt.Sprintf("cannot get goroutine id: %v", err))
// 	}
// 	return id
// }

// func worker(counter *SharedCounter, wg *sync.WaitGroup) {
// 	defer wg.Done()
// 	for j := 0; j < 10; j++ {
// 		counter.Increment()
// 	}
// }

// func main() {
// 	var counter SharedCounter
// 	var wg sync.WaitGroup

// 	numGoroutines := 5

// 	for i := 0; i < numGoroutines; i++ {
// 		wg.Add(1)
// 		go worker(&counter, &wg)
// 	}

// 	wg.Wait()

// 	fmt.Printf("Final counter value: %d", counter.GetCount())
// }

import (
	"fmt"
	"math/rand"
	"time"
)

func tempSensorProd(tempChan <-chan int) int {
	rand.Seed(time.Now().UnixNano())
	randomInt := rand.Intn(100)
	tempChan <- randomInt
	return randomInt
}

func tempSensorCons(temp int) {
	fmt.Printf("The current temperature is %d", temp)
}

func main() {

	tempChan := make(chan int)

	go tempSensorProd(tempChan)

	go tempSensorCons(<-tempChan)
}
