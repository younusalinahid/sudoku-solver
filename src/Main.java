import sudoku.Sudoku;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 0;

        while (true) {
            System.out.print("Choose Sudoku size (Enter 9 for 9x9 or 6 for 6x6): ");
            size = scanner.nextInt();
            if (size == 9 || size == 6) {
                break;
            } else {
                System.out.println("Invalid size. Please enter 9 for 9x9 or 6 for 6x6.");
            }
        }
        int[][] sudoku;

        if (size == 9) {
            sudoku = new int[][]{
                    {8, 6, 0, 0, 2, 0, 0, 0, 0},
                    {0, 0, 0, 7, 0, 0, 0, 5, 9},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 6, 0, 8, 0, 0},
                    {0, 4, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 5, 3, 0, 0, 0, 0, 7},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 2, 0, 0, 0, 0, 6, 0, 0},
                    {0, 0, 7, 5, 0, 9, 0, 0, 0}
            };
        } else if (size == 6) {
            sudoku = new int[][]{
                    {6, 2, 0, 5, 0, 3},
                    {0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 3, 0},
                    {0, 6, 0, 0, 2, 0},
                    {0, 0, 0, 3, 4, 6},
                    {3, 0, 6, 0, 0, 0}
            };

        } else {
            System.out.println("Invalid size. Only 9x9 and 6x6 supported.");
            return;
        }

        try {
            System.out.println(Sudoku.asString(sudoku));
            if (Sudoku.solve(sudoku)) {
                System.out.println("Solved Sudoku:");
                System.out.println(Sudoku.asString(sudoku));
            } else {
                System.out.println("This Sudoku puzzle cannot be solved.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
