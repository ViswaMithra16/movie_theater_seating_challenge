# Movie Theater Seating Challenge

## Description:

Implement an algorithm for assigning seats within a movie theater to fulfill reservation requests. 
Given a theatre with seating capacity of 20 seats in each of the 10 rows (200 seats), the algorithm needs to assign seats to the customers to maximize theatre utilization and guarantees customer satisfaction.

## Summary:
This algorithm arranges the seats for the customer considering the customer satisfaction and maximum theater utilization by allocating majority seats of reservation together.

### Approach

1. The seating arrangement can be done using Greedy Approach, as we first try to reserve the entire reservation in single row and then allocates the best seats in different rows.

## Input:

Input file contains input for each Reservation request with Id and number of seats.The order of the lines in the file reflects the order in which the reservation requests were received.Each line in the file will be comprised of a reservation identifier, followed by a space, and then the number of seats requested. The reservation identifier will have the format: R####. 

### Sample Input File:
R001 2 
R002 4
R003 4 
R004 3 

## Output:

The program should output a file containing the seating assignments for each request. Each row in the file should include the reservation number followed by a space, and then a comma-delimited list of the assigned seats. 

### Sample Output Example:

R001 I1,I2
R002 F16,F17,F18,F19 
R003 A1,A2,A3,A4 
R004 J4,J5,J6

