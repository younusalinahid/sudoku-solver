package sudoku;

public class Solver {

    private final Board board;

    Solver(int[][] input) throws IllegalArgumentException {
        this.board = new Board(input);
    }

    boolean solve() {
        return solve(0, 0);
    }

    private boolean solve(int row, int col) {
        if (row == board.GRID_SIZE) {
            row = 0;
            if (++col == board.GRID_SIZE) {
                return true;
            }
        }
        if (board.getCell(row, col) != 0) {
            return solve(row + 1, col);
        }

        for (int val = 1; val <= board.GRID_SIZE; val++) {
            if (isMoveOK(row, col, val)) {
                board.setCell(row, col, val);
                if (solve(row + 1, col)) {
                    return true;
                }
                board.setCell(row, col, 0);
            }
        }
        return false;
    }

    private boolean isMoveOK(int row, int col, int val) {
        return !(arrayContains(board.getRow(row), val)
                || arrayContains(board.getColumn(col), val)
                || arrayContains(board.getRegion(row, col), val));
    }

    private boolean arrayContains(int[] array, int val) {
        for (int arrayVal : array) {
            if (arrayVal == val) {
                return true;
            }
        }
        return false;
    }
}
