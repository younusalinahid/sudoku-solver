package sudoku;

final class Board {
    final int GRID_SIZE;
    final int REGION_ROWS;
    final int REGION_COLS;
    private final int[][] board;

    Board(int[][] init) throws IllegalArgumentException {
        this.GRID_SIZE = init.length;

        if (GRID_SIZE == 9) {
            this.REGION_ROWS = 3;
            this.REGION_COLS = 3;
        } else if (GRID_SIZE == 6) {
            this.REGION_ROWS = 2;
            this.REGION_COLS = 3;
        } else {
            throw new IllegalArgumentException("Invalid board size.");
        }

        if (init.length != GRID_SIZE || init[0].length != GRID_SIZE) {
            throw new IllegalArgumentException("Invalid board size.");
        }
        for (int[] row : init) {
            for (int cell : row) {
                if (cell < 0 || cell > GRID_SIZE) {
                    throw new IllegalArgumentException("Invalid value: cells must be between 0 and " + GRID_SIZE + ".");
                }
            }
        }
        this.board = init;
    }

    int[] getRow(int row) {
        return board[row];
    }

    int[] getColumn(int col) {
        final int[] columnView = new int[GRID_SIZE];
        for (int a = 0; a < GRID_SIZE; a++) {
            columnView[a] = board[a][col];
        }
        return columnView;
    }

    int[] getRegion(int row, int col) {
        final int[] regionView = new int[GRID_SIZE];
        final int rowBase = row - (row % REGION_ROWS);
        final int colBase = col - (col % REGION_COLS);
        int counter = 0;
        for (int r = rowBase; r < rowBase + REGION_ROWS; r++) {
            for (int c = colBase; c < colBase + REGION_COLS; c++) {
                regionView[counter++] = board[r][c];
            }
        }
        return regionView;
    }

    int getCell(int row, int col) {
        return board[row][col];
    }

    void setCell(int row, int col, int val) {
        board[row][col] = val;
    }

    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        for (int a = 0; a < GRID_SIZE; a++) {
            int[] row = board[a];
            if (a % REGION_ROWS == 0) {
                appendLine(buffer);
            }
            for (int b = 0; b < GRID_SIZE; b++) {
                if (b % REGION_COLS == 0) {
                    buffer.append(" |");
                }
                appendValue(buffer, row[b]);
            }
            buffer.append(" |\n");
        }
        appendLine(buffer);
        return buffer.toString();
    }

    private void appendValue(StringBuilder buffer, int value) {
        buffer.append(' ');
        if (value != 0) {
            buffer.append(value);
        } else {
            buffer.append('_');
        }
    }

    private void appendLine(StringBuilder buffer) {
        buffer.append(" ");
        for (int i = 0; i < GRID_SIZE + (GRID_SIZE / REGION_COLS) + 1; i++) {
            buffer.append("--");
        }
        buffer.append("\n");
    }
}
