package sudoku;

public class Sudoku {
    public static boolean solve(int[][] sudoku) {
        return new Solver(sudoku).solve();
    }

    public static String asString(int[][] sudoku) {
        return new Board(sudoku).toString();
    }
}
