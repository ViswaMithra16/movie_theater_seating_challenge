import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        MovieTheaterBooking obj = new MovieTheaterBooking();
        FileWriter fileProcessor = new FileWriter();
        try {
            File file = new File(args[0]);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            LinkedHashMap<String, ArrayList<String>> bookings = new LinkedHashMap<>();
            String nextReservation = bufferedReader.readLine();
            while (nextReservation != null) {
                String[] nextReservation_arr = nextReservation.split(" ");
                String ReservationId = nextReservation_arr[0];
                String ReservationSeatCount = nextReservation_arr[1];
                bookings = obj.prebooking_checks(ReservationId, ReservationSeatCount, fileProcessor);
                obj.printTheatreMap();
                nextReservation = bufferedReader.readLine();
            }
            fileProcessor.writeToFile(bookings);
        } catch (FileNotFoundException ex) {
            System.err.println("Input file not Found.");
            ex.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
