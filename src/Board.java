import java.util.ArrayList;

public class Board {
    private int size;

    public Board(int size) {
        this.size = size;
    }

    private boolean[][] board = new boolean[size][size];

    ArrayList<Queen> queens = new ArrayList<>();

    private boolean added = false;

    private int previousCol = 0;

    public void backTracking() {
        Queen temp;
        for (int row = 0; row < size; row++) {
            added = false;

            for (int col = previousCol; col < size; col++) {
                temp = new Queen(row, col);
                if (!checkColission(temp)) {
                    queens.add(temp);
                    added = true;
                    break;
                }
            }
            if (!added) {
                temp = queens.remove(queens.size() - 1);
                previousCol = temp.getCol() + 1;
                row = temp.getRow() - 1;
            }else{
                previousCol = 0;
            }
        }
    }

    public void print() {
        Queen temp;

        // print header
        System.out.print("  |");
        for (int col = 0; col < size; col++) {
                System.out.print( " " + col + " |");
        }
        System.out.println();
        for (int col = 0; col < size; col++) {
            System.out.print("-----");
        }

        // print new line after header
        System.out.println();

        for (int row = 0; row < size; row++) {
            temp = queens.get(row);
            // print row num
            System.out.print(row + " ");

            for (int col = 0; col < size; col++) {
                // print queen position, if queen is on the position print X otherwise print white space
                if (temp.getCol() == col){
                    System.out.print("| X ");
                }else{
                    System.out.print("|   ");
                }
            }


            System.out.println("|");
            for (int col = 0; col < size; col++) {
                System.out.print("-----");
            }
            System.out.println();
        }
    }

    private boolean checkColission(Queen queen) {
        if (checkDiagonals(queen) || checkHorizontal(queen) || checkVerical(queen)) {
            return true;
        }
        return false;
    }

    private boolean checkDiagonals(Queen queen) {
        for (Queen q : queens) {
            if (Math.abs(q.getRow() - queen.getRow()) == Math.abs(q.getCol() - queen.getCol())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontal(Queen queen) {
        for (Queen q : queens) {
            if (q.getRow() == queen.getRow()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerical(Queen queen) {
        for (Queen q : queens) {
            if (q.getCol() == queen.getCol()) {
                return true;
            }
        }
        return false;
    }
}
