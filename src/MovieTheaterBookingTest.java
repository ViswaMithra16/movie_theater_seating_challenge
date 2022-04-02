import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Test;

public class MovieTheaterBookingTest {

    @Test
    public void seatAllocationTest() {
        LinkedHashMap<String, ArrayList<String>> response = new LinkedHashMap<>();
        FileWriter fileProcessor = new FileWriter();
        ArrayList<String> list = new ArrayList<>();
        list.add("The number of seats should be an Integer.");
        MovieTheaterBooking theater = new MovieTheaterBooking();
        try {
            response = theater.prebooking_checks("R00A", "A", fileProcessor);
            System.out.println("Response--> " + response.get("R00A"));
            Assert.assertEquals(response.get("R00A"), list);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "File Name should be valid");
        }
    }

    @Test
    public void bookingTestCase() {
        LinkedHashMap<String, ArrayList<String>> response = new LinkedHashMap<>();
        FileWriter fileProcessor = new FileWriter();
        ArrayList<String> list = new ArrayList<>();
        list.add("J1");
        list.add("J2");
        MovieTheaterBooking theater = new MovieTheaterBooking();
        try {
            response = theater.prebooking_checks("R00A", "2", fileProcessor);
            System.out.println("Response--> " + response.get("R00A"));
            Assert.assertEquals(response.get("R00A"), list);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "File Name should be valid");
        }
    }

    @Test
    public void bookingMoreThanCapacity() {
        LinkedHashMap<String, ArrayList<String>> response = new LinkedHashMap<>();
        FileWriter fileProcessor = new FileWriter();
        ArrayList<String> list = new ArrayList<>();
        list.add("The number of seats requested is greater than Theater Capacity");
        MovieTheaterBooking theater = new MovieTheaterBooking();
        try {
            response = theater.prebooking_checks("R00A", "250", fileProcessor);
            System.out.println("Response--> " + response.get("R00A"));
            Assert.assertEquals(response.get("R00A"), list);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "File Name should be valid");
        }
    }


    @Test
    public void bookingSeatsWithNegativeCount() {
        LinkedHashMap<String, ArrayList<String>> response = new LinkedHashMap<>();
        FileWriter fileProcessor = new FileWriter();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();
        actual.add("The number of seats cannot be negative.");
        list.add("-1");
        MovieTheaterBooking theater = new MovieTheaterBooking();
        try {
            response = theater.prebooking_checks("R001", "-1", fileProcessor);
            System.out.println("Response--> " + response.get("R001"));
            Assert.assertEquals(response.get("R001"), actual);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "------------------ ");
        }
    }



}
