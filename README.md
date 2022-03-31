# Movie Theater Seating Challenge

## Description

Implement an algorithm for assigning seats within a movie theater to fulfill reservation requests. 
Given a theatre with seating capacity of 20 seats in each of the 10 rows (200 seats), the algorithm needs to assign seats to the customers to maximize theatre utilization and guarantees customer satisfaction.

## Summary
This algorithm arranges the seats for the customer considering the customer satisfaction and maximum theater utilization by allocating majority seats of reservation together.

## Approach

1. The Greedy approach can be followed as the main function of this approach is decision is taken on basis of currently available information.
2. In the challenge given, we allocate the seats on first come first server basis and try allocating the entire group reservation in a single row.
3. To increase the customer satisfaction we start reserving the seats from top left of the theater.

## Input

Input file contains input for each Reservation request with Id and number of seats.The order of the lines in the file reflects the order in which the reservation requests were received.Each line in the file will be comprised of a reservation identifier, followed by a space, and then the number of seats requested. The reservation identifier will have the format: R####. 

### Sample Input File
```
R001 2 
R002 4
R003 4 
R004 3 
```

## Output

The program should output a file containing the seating assignments for each request. Each row in the file should include the reservation number followed by a space, and then a comma-delimited list of the assigned seats. 

### Sample Output Example

```
R001 I1,I2
R002 F16,F17,F18,F19 
R003 A1,A2,A3,A4 
R004 J4,J5,J6
```

## Assumptions

1. Seats will be reserved on First come basis.
2. First reservation Id will get better seat than the later reservation id’s from input file.
3. The seats will get allocated from top to bottom and left to right.
4. The algorithm will try to allocate seats with entire reservation in a single row I.e if the requested  seats are 18 it tries to allocate the 18 seats in a single row.
5. If the seats are not possible to allocate seats in a single row, then we consider booking the reservation in groups. But there is an exception to this case. For example if there are just 2 seats available separately and then a reservation of two seats is made, then these people will have to sit separately. 

## Steps to run

Open your terminal window / command prompt. Go to the folder where the unzipped file is saved. Navigate to the folder "src". Run the command:
```
javac Main.java
```

Run the following command to start the application


```
java Main "/Users/viswamithra/Desktop/Movie_Theater_Seating/src/input.txt"
```

