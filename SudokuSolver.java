package sudokusolver;
public class SudokuSolver {
    private static final int BOARD_SIZE = 9;

    public boolean solveSudoku(int[][] board)
     {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find the first empty cell in the puzzle
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // If there are no empty cells, the puzzle is solved
        if (isEmpty) {
            return true;
        }
        else
        {
        // Try numbers from 1 to 9 in the empty cell
        for (int num = 1; num <= BOARD_SIZE; num++) {
            if (isSafe(board, row, col, num)) {
                // If the number is safe, assign it and continue solving
                board[row][col] = num;

                // Recursively solve the remaining puzzle
                if (solveSudoku(board))
                {
                    return true;
                }
               else{
                // If the assigned number doesn't lead to a solution, backtrack and try the next number
                board[row][col] = 0;}
            }
        }
    }
        // No solution found for the current configuration
        return false;
}

    public boolean isSafe(int[][] board, int row, int col, int num) {
        // Check if the number exists in the current row or column
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
            
        }

        // Check if the number exists in the current 3x3 grid
        int gridRow = row - row % 3;
        int gridCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[gridRow + i][gridCol + j] == num) {
                    return false;
                }
            }
        }

        // The number is safe to be placed in the cell
        return true;
    }

    public static void main(String[] args) {
        // Replace this example with your Sudoku puzzle
        int[][] puzzle = {
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
        };

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(puzzle)) {
            System.out.println("Sudoku Puzzle Solved:");
            printBoard(puzzle);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
