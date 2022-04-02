import java.util.*;

class MovieTheaterBooking {

    final int noOfRows = 10;  //Given bounds for theater
    final int noOfColumns = 20;
    int theaterSeatCapacity = noOfRows * noOfColumns;  // Max theater Seat Capacity
    String[][] seatMap;  //  Array to keep track of who is assigned to which seat
    int lastFilledSeatIndex[]; // To track the last index of seat filled in every row
    LinkedHashMap<String, ArrayList<String>> bookings;

    MovieTheaterBooking() {
        seatMap = new String[noOfRows][noOfColumns];
        lastFilledSeatIndex = new int[]{-4, -4, -4, -4, -4, -4, -4, -4, -4, -4};  // Index of each row where the last seat is booked
        bookings = new LinkedHashMap<>();
    }

    /**
     * This method is used to handle the pre-booking checks like negative input and seats capacity
     *
     * @param reservationId        This is the first parameter holding ReservationId
     * @param reservationSeatCount This is the second parameter containing the Requested ReservationSeatCount
     * @param fileProcessor        This is the third parameter to write the error messages to output file
     * @return bookings This returns the bookings map with reservationId as Key and allocated list of tickets as values
     */

    public LinkedHashMap<String, ArrayList<String>> prebooking_checks(String reservationId, String reservationSeatCount, FileWriter fileProcessor) {

        //Checks to handle the negative input seat count and seats capacity
        int i_ReservationSeatCount;
        try {
            i_ReservationSeatCount = Integer.parseInt(reservationSeatCount);
            System.out.println(i_ReservationSeatCount + "   " + theaterSeatCapacity);
        } catch (NumberFormatException e) {
            ArrayList<String> lt = new ArrayList<>();
            lt.add("The number of seats should be an Integer.");
            bookings.put(reservationId, lt);
            return bookings;
        }
        if (i_ReservationSeatCount <= 0) {
            ArrayList<String> lt = new ArrayList<>();
            lt.add("The number of seats cannot be negative.");
            bookings.put(reservationId, lt);
            return bookings;
        }

        if (i_ReservationSeatCount > theaterSeatCapacity) {
            ArrayList<String> lt = new ArrayList<>();
            lt.add("The number of seats requested is greater than Theater Capacity");
            bookings.put(reservationId, lt);
            fileProcessor.writeToFile(bookings);
        } else {
            //Allocate seats as per the reservation request count
            bookTickets(reservationId, i_ReservationSeatCount);
        }
        return bookings;
    }

    public void printTheatreMap() {
        for (int r = noOfRows - 1; r >= 0; r--) {
            System.out.print(String.valueOf((char) (r + 65)) + " :\t");
            for (int c = 0; c < noOfColumns; c++) {
                System.out.print(seatMap[r][c] + "\t");
            }
            System.out.println("");
        }
        System.out.println("-------------------------------------------------------------------- Movie Screen -----------------------------------------------------------");
    }

    /**
     * This method is used to book tickets for the input requested
     *
     * @param reservationId          This is the first parameter holding ReservationId
     * @param i_reservationSeatCount This is the second parameter containing the integer value of  Requested ReservationSeatCount
     */
    public void bookTickets(String reservationId, int i_reservationSeatCount) {

        // Starting the allocation of seats from top to bottom row and left to right in each row.
        for (int r = noOfRows - 1; r >= 0; r--) {
            // Tracks the number of seats empty in each row.
            int seatsEmpty = noOfColumns - lastFilledSeatIndex[r] - 4;
            if (seatsEmpty >= i_reservationSeatCount) {
                fillSeats(reservationId, i_reservationSeatCount, r);
                return;
            }
        }
        /* If the seatsEmpty is less than the no of reservation seat count,
            calculate the seats remaining in each row (including the buffer of 3 seats also).
         1. if there are no seats the that particular row, iterate through the next row....
         2. Find out the Seats that are less w.r.t to the input request in that particular row
         3. If lessSeats is equal to 1 then, trying ot allocate the seats in group of 2 and 3*/

        int maxConse = Integer.MIN_VALUE;
        for (int i = 0; i < noOfRows; i++) {
            maxConse = Math.max(maxConse, noOfColumns - lastFilledSeatIndex[i] - 4);
        }
        for (int r = noOfRows - 1; r >= 0; r--) {
            int seatsRem = noOfColumns - lastFilledSeatIndex[r] - 4;
            if (seatsRem <= 0) {
                continue;
            }
            int lessSeats = i_reservationSeatCount - seatsRem;
            if (lessSeats == 1 && maxConse >= 2) {
                fillSeats(reservationId, i_reservationSeatCount - 2, r);
                bookTickets(reservationId, 2);  // Should end the loop here
            } else {
                fillSeats(reservationId, i_reservationSeatCount - lessSeats, r);
                bookTickets(reservationId, lessSeats);
            }
            break;
        }
    }

    /**
     * This method is used to fill seats in a row for the number of seats requested and update the theater capacity
     *
     * @param reservationId          This is the first parameter holding ReservationId
     * @param i_reservationSeatCount This is the second parameter containing the integer value of  Requested ReservationSeatCount
     * @param row                    This is the third parameter which holds the row value.
     */

    public void fillSeats(String reservationId, int i_reservationSeatCount, int row) {
        int startFill = lastFilledSeatIndex[row] + 4;
        int endFill = startFill + i_reservationSeatCount - 1;
        for (int i = startFill; i <= endFill; i++) {
            seatMap[row][i] = reservationId;
            //Checking if seats are already allocated to reservationId, if true adding the new seats to the existing entry.
            if (bookings.containsKey(reservationId)) {
                bookings.get(reservationId).add(String.valueOf((char) (row + 65)) + String.valueOf(i + 1));
            } else {
                //If no entry is present, add a new entry to the bookings with reservationId and list of tickets booked.
                ArrayList<String> lt = new ArrayList<>();
                lt.add(String.valueOf((char) (row + 65)) + String.valueOf(i + 1));
                bookings.put(reservationId, lt);
            }
        }
        lastFilledSeatIndex[row] = endFill; // pointed the last seat booked in that row.
        int diff = noOfColumns - (endFill + 1);
        //If the remaining seats in the row are greater than or equal to 3 then we can still reserve in that row. Updating the total theater seat capacity
        if (diff >= 3) {
            theaterSeatCapacity = theaterSeatCapacity - i_reservationSeatCount - 3;
        } else {
            theaterSeatCapacity = theaterSeatCapacity - i_reservationSeatCount - diff;
        }
    }
}
