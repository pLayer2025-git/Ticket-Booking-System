# Task 15: Multithreaded Ticket Booking Simulation

This project simulates a real-world ticket booking scenario where multiple users (threads) attempt to book tickets simultaneously from a shared pool.

## 🧠 Key Concepts

### 1. Race Condition
Without synchronization, two threads might check the `availableTickets` count at the exact same time, both see that 1 ticket is left, and both proceed to book it. This results in "negative" inventory—a classic concurrency bug.

### 2. Synchronization
The `synchronized` keyword acts as a "Lock." Only one thread can enter the `bookTicket()` method at a time. Other threads are placed in a **BLOCKED** state until the current thread finishes.

### 3. Thread Lifecycle
* **NEW:** Thread is created but not started.
* **RUNNABLE:** `start()` is called; the thread is ready to run.
* **TIMED_WAITING:** Thread is sleeping via `Thread.sleep()`.
* **BLOCKED:** Thread is waiting for the `synchronized` lock.
* **TERMINATED:** The `run()` method has finished.

## 🛠️ How it Works
1.  **BookingOffice:** The shared monitor containing the ticket logic.
2.  **UserThread:** Extends `Thread` to simulate individual users.
3.  **Synchronization:** Ensures that even if Bob and Alice click "Book" at the same millisecond, the system processes them one by one.

## 🚀 Execution
- Compile: `javac TicketBookingSystem.java`
- Run: `java TicketBookingSystem`
