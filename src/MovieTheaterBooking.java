

import java.util.*;
import java.io.*;


class MovieTheaterBooking
{
    final int noOfRows=10;  //Given bounds for theater
    final int noOfColumns=20;
    int theaterSeatCapacity=noOfRows*noOfColumns;  // Max theater Seat Capacity
    String seatmap[][];   //  Array to keep track of who is assigned to which seat
    int lastFilledSeatIndex[];
    LinkedHashMap<String,ArrayList<String>> Bookings;

    MovieTheaterBooking()
    {
        seatmap=new String[noOfRows][noOfColumns];
        lastFilledSeatIndex=new int[]{-4,-4,-4,-4,-4,-4,-4,-4,-4,-4};  // Index of each row where the last seat is booked
        Bookings=new LinkedHashMap<>();
    }

    public void prebooking_checks(String ReservationId,String ReservationSeatCount, FileWriter fileProcessor)
    {

        //Checks to handle the negative input seatcount and seats capacity
        int i_ReservationSeatCount;
        try {
            i_ReservationSeatCount = Integer.parseInt(ReservationSeatCount);
           // System.out.println(i_ReservationSeatCount + "   " + theaterSeatCapacity);
        }

        catch(NumberFormatException e) {
            //System.out.println("The number of seats should be an Integer");
            ArrayList<String> lt=new ArrayList<>();
            lt.add("The number of seats should be an Integer.");
            Bookings.put(ReservationId,lt);
            //fileProcessor.writeToFile("The number of seats should be an Integer.");
            return;
        }

        if(i_ReservationSeatCount<=0)
        {
            ArrayList<String> lt=new ArrayList<>();
            lt.add("The number of seats cannot be negative.");
            Bookings.put(ReservationId,lt);
            //System.out.println("The number of seats should not be Negative or Zero");
            return;
        }

        if(i_ReservationSeatCount>theaterSeatCapacity)
        {
            ArrayList<String> lt=new ArrayList<>();
            lt.add("The number of seats requested is greater than Theater Capacity");
            Bookings.put(ReservationId,lt);
            fileProcessor.writeToFile(Bookings);
            //System.out.println("The number of seats should not be greater than Theater Capacity");

        }
        else{
            //allocate seats as per the reservation request count
            bookTickets(ReservationId,i_ReservationSeatCount);
        }

        printTheatreMap();
    }

    public void printTheatreMap()
    {
        for(int r=noOfRows-1;r>=0;r--)
        {
            System.out.print(String.valueOf((char)(r+65))+" :\t");
            for(int c=0;c<noOfColumns;c++)
            {
                System.out.print(seatmap[r][c]+"\t");
            }
            System.out.println("");
        }
        System.out.println("-------------------------------------------------------------------- Movie Screen -----------------------------------------------------------");
    }

    public void bookTickets(String ReservationId,int i_ReservationSeatCount)
    {

        // starting the allocation of seats from top to bottom row and left to right in each row.
        for(int r=noOfRows-1;r>=0;r--)
        {
            // tracks the number of seats empty in each row.
            int seatsEmpty=noOfColumns-lastFilledSeatIndex[r]-4;
            if(seatsEmpty>=i_ReservationSeatCount)
            {
                fillSeats(ReservationId,i_ReservationSeatCount,r);
                return;
            }
        }
        // if the seatsEmpty is less than the no of reservation seat count,
        //calculate the seats remaining in each row (including the buffer of 3 seats also).
        // 1. if there are no seats the that particular row, iterate through the next row....
        // 2. Find out the Seats that are less w.r.t to the input request in that particular row
        // 3. If lessSeats is equal to 1 then, trying ot allocate the seats in group of 2 and 3

        int MaxConse= Integer.MIN_VALUE;
        for(int i=0;i<noOfRows;i++) {
            MaxConse = Math.max(MaxConse, noOfColumns-lastFilledSeatIndex[i]-4);
        }


        for(int r=noOfRows-1;r>=0;r--)
        {
            int seatsRem=noOfColumns-lastFilledSeatIndex[r]-4;
            if(seatsRem<=0)
            {
                continue;
            }
            int lessSeats=i_ReservationSeatCount-seatsRem;
            if(lessSeats==1 && MaxConse>=2)
            {
                fillSeats(ReservationId, i_ReservationSeatCount - 2, r);
                bookTickets(ReservationId, 2);  // Should end the loop here
                break;
            }
            else
            {
                fillSeats(ReservationId,i_ReservationSeatCount-lessSeats,r);
                bookTickets(ReservationId,lessSeats);
                break;
            }

        }
    }

    public void fillSeats(String ReservationId,int i_ReservationSeatCount,int row)
    {
        // Find the start and end position where the seats are booked in current row.
        int startFill=lastFilledSeatIndex[row]+4;
        int endFill=startFill+i_ReservationSeatCount-1;
        for(int i=startFill;i<=endFill;i++)
        {
            seatmap[row][i]=ReservationId;
            //Checking if seats are already allocated to reservationId, if true adding the new seats to the existing entry.
            if(Bookings.containsKey(ReservationId))
            {
                Bookings.get(ReservationId).add(String.valueOf((char)(row+65))+String.valueOf(i+1));
            }
            else
            {
                //If no entry is present, add a new entry to the bookings with reservationId and list of tickets booked.
                ArrayList<String> lt=new ArrayList<>();
                lt.add(String.valueOf((char)(row+65))+String.valueOf(i+1));
                Bookings.put(ReservationId,lt);
            }
        }
        lastFilledSeatIndex[row]=endFill; // pointed the last seat booked in that row.
        int diff = noOfColumns - (endFill+1);
        // updating the total theater seat capacity
        //If the remaining seats in the row are greater than or equal to 3 then we can still reserve in that row .
        if(diff>=3){
            theaterSeatCapacity=theaterSeatCapacity-i_ReservationSeatCount-3;
        }else{
            theaterSeatCapacity=theaterSeatCapacity-i_ReservationSeatCount-diff;
        }
        //theaterSeatCapacity=theaterSeatCapacity-i_ReservationSeatCount;
    }

}