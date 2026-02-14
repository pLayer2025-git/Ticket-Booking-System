import java.util.concurrent.TimeUnit;

/**
 * 2. Shared Resource representing ticket count.
 */
class BookingOffice {
    private int availableTickets = 3;

    /**
     * 4 & 5. Thread-safe booking logic using 'synchronized'.
     * Try removing the 'synchronized' keyword to see a Race Condition!
     */
    public synchronized void bookTicket(String userName, int requestedTickets) {
        System.out.println("[INFO] " + userName + " is checking for " + requestedTickets + " tickets...");

        // 6. Use sleep() to simulate real processing delay
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        if (availableTickets >= requestedTickets) {
            System.out.println("[SUCCESS] " + requestedTickets + " ticket(s) booked for " + userName);
            availableTickets -= requestedTickets;
            System.out.println("[REMAINING] Tickets left: " + availableTickets);
        } else {
            System.out.println("[FAILED] Not enough tickets for " + userName + " (Remaining: " + availableTickets + ")");
        }
    }
}

/**
 * 1. Thread class representing users booking tickets.
 */
class UserThread extends Thread {
    private BookingOffice office;
    private String name;
    private int ticketsNeeded;

    public UserThread(BookingOffice office, String name, int ticketsNeeded) {
        this.office = office;
        this.name = name;
        this.ticketsNeeded = ticketsNeeded;
    }

    @Override
    public void run() {
        // 8. Print booking logs clearly.
        office.bookTicket(name, ticketsNeeded);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        BookingOffice sharedOffice = new BookingOffice();

        // Creating multiple threads (Users)
        UserThread user1 = new UserThread(sharedOffice, "Alice", 1);
        UserThread user2 = new UserThread(sharedOffice, "Bob", 2);
        UserThread user3 = new UserThread(sharedOffice, "Charlie", 1);
        UserThread user4 = new UserThread(sharedOffice, "Dave", 1);

        // 7. Thread Lifecycle: Moving from NEW to RUNNABLE
        System.out.println("--- Starting Booking Simulation ---");
        user1.start();
        user2.start();
        user3.start();
        user4.start();
    }
}