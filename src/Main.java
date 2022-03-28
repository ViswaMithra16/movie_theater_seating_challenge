

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Main {

    public static void main(String[] args) {
       // if (args.length > 0) {
            MovieTheaterBooking obj=new MovieTheaterBooking();
            FileWriter fileProcessor = new FileWriter();
            try {
                File file = new File(args[0]);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String nextReservation = bufferedReader.readLine();
                while (nextReservation != null) {
                    String[] nextReservation_arr =nextReservation.split(" ");
                    String ReservationId=nextReservation_arr[0];
                    String ReservationSeatCount=nextReservation_arr[1];
                    obj.prebooking_checks(ReservationId,ReservationSeatCount,fileProcessor);
                    nextReservation = bufferedReader.readLine();
                }
                fileProcessor.writeToFile(obj.Bookings);
//            for(String s1:obj.Bookings.keySet())
//            {
//                ArrayList<String> curr=obj.Bookings.get(s1);
//                System.out.print(s1+" ");
//                for(int i=0;i<curr.size();i++)
//                {
//                    String s2=curr.get(i);
//                    if(i!=curr.size()-1)
//                    {
//                        System.out.print(s2+",");
//                    }
//                    else
//                    {
//                        System.out.print(s2);
//                    }
//                }
//                System.out.println("");
//            }

            } catch (FileNotFoundException ex) {
                System.err.println("Input file not Found.");
                ex.printStackTrace();
                System.exit(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
       // }


    }
}
