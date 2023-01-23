package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scan.nextInt();
        char[][] cinemaSeats = new char[rows][seatsPerRow];
        startEmptySeats(cinemaSeats);
        int purchasedTickets = 0;
        int totalOfSeats = rows * seatsPerRow;
        int currentIncome = 0;
        int totalIncome = getTotalIncome(rows, seatsPerRow, totalOfSeats);

        String options = """
                
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""";
        int chosenOption;

        do {
            System.out.println(options);
            chosenOption = scan.nextInt();
            System.out.println();
            if (chosenOption == 1) {
                printCinemaSeats(cinemaSeats);
            } else if (chosenOption == 2) {
                int selectedRow;
                int selectedSeat;
                do {
                    System.out.println("Enter a row number:");
                    selectedRow = scan.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    selectedSeat = scan.nextInt();
                }
                while (isInvalidInput(cinemaSeats, selectedRow, selectedSeat));
                System.out.println();

                int price;
                if (totalOfSeats <= 60) {
                    price = 10;
                    System.out.println("Ticket price: $" + price);
                } else {
                    double halfOfRows = (double) rows / 2.0;
                    int frontHalf = (int) Math.floor(halfOfRows);
                    price = (selectedRow <= frontHalf ? 10 : 8);
                    System.out.println("Ticket price: $" + price);
                }
                cinemaSeats[selectedRow - 1][selectedSeat - 1] = 'B';
                currentIncome += price;
                purchasedTickets++;
            } else if (chosenOption == 3) {
                double percentageOfPurchasedSeats = (double) purchasedTickets / totalOfSeats * 100;
                String statistics = """
                        Number of purchased tickets: %d
                        Percentage: %.2f%%
                        Current income: $%d
                        Total income: $%d
                        """;
                System.out.printf(statistics, purchasedTickets,
                        percentageOfPurchasedSeats, currentIncome, totalIncome);
            }
        }
        while (chosenOption != 0);
    }

    private static boolean isInvalidInput(char[][] cinemaSeats, int selectedRow, int selectedSeat) {
        if (selectedRow > cinemaSeats.length
                || selectedRow < 1
                || selectedSeat > cinemaSeats[0].length
                || selectedSeat < 1) {
            System.out.println("Wrong input!");
            System.out.println();
            return true;
        }
        if (cinemaSeats[selectedRow - 1][selectedSeat - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            return true;
        }
        return false;
    }

    private static int getTotalIncome(int rows, int seatsPerRow, int totalOfSeats) {
        int total = 0;
        if (totalOfSeats <= 60) {
            total = totalOfSeats * 10;
        } else {
            double halfOfRows = (double) rows / 2.0;
            int frontHalf = (int) Math.floor(halfOfRows);
            int backHalf = (int) Math.ceil(halfOfRows);
            total = (frontHalf * 10 + backHalf * 8) * seatsPerRow;
        }
        return total;
    }

    static void printCinemaSeats(char[][] seats) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void startEmptySeats(char[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                seats[i][j] = 'S';
            }
        }
    }
}