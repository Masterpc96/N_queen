import java.util.ArrayList;

public class Board {
    private int size;

    private int forward_checking_returns = 0;
    private int back_tracking_returns = 0;

    private int forward_checking_set_queen = 0;
    private int back_tracking_set_queen = 0;

    public Board(int size) {
        this.size = size;
    }

    private boolean[][] board = new boolean[size][size];

    ArrayList<Queen> queens = new ArrayList<>();

    private boolean added = false;

    private int previousCol = 0;

    public void forwardChecking() {
        Queen temp;
        for (int row = 0; row < size; row++) {
            added = false;

            for (int col = previousCol; col < size; col++) {
                temp = new Queen(row, col);
                if (!checkCollision(temp)) {
                    queens.add(temp);
                    added = true;
                    forward_checking_set_queen += 1;
                    break;
                }
            }
            if (!added) {
                forward_checking_returns += 1;
                temp = queens.remove(queens.size() - 1);
                previousCol = temp.getCol() + 1;
                row = temp.getRow() - 1;
            } else {
                previousCol = 0;
            }
        }
    }

    public void backTracking() {
        Queen temp;
        int previousCol = 0;
        for (int row = 0; row < size; row++) {
            temp = new Queen(row, previousCol);
            queens.add(temp);
            back_tracking_set_queen += 1;

            if (checkCollision(queens.get(queens.size() - 1))) {
                temp = queens.remove(queens.size() - 1);
                row = temp.getRow() - 1;
                previousCol = temp.getCol() + 1;
                back_tracking_returns += 1;
            } else {
                previousCol = 0;
            }
        }
    }

    public void print() {
        Queen temp;

        // print header
        System.out.print("  |");
        for (int col = 0; col < size; col++) {
            System.out.print(" " + col + " |");
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
                if (temp.getCol() == col) {
                    System.out.print("| X ");
                } else {
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

    // if there isn't a collision return false
    private boolean checkCollision(Queen queen) {
        if (!checkVertical(queen) && !checkHorizontal(queen) && !checkDiagonals(queen)) {
            return false;
        }
        return true;
    }

    private boolean checkDiagonals(Queen queen) {
        for (Queen q : queens) {
            if (Math.abs(q.getRow() - queen.getRow()) == Math.abs(q.getCol() - queen.getCol()) && q != queen) {
                return true;
            }
        }
        return false;
    }

    private boolean checkHorizontal(Queen queen) {
        for (Queen q : queens) {
            if (q.getRow() == queen.getRow() && q != queen) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVertical(Queen queen) {
        for (Queen q : queens) {
            if (q.getCol() == queen.getCol() && q != queen) {
                return true;
            }
        }
        return false;
    }

    public void clearBoard(){
        queens.clear();
    }


    public String getStatistic(){
        return "forward checking set queen " + forward_checking_set_queen +
                "\nforward checking returns " + forward_checking_returns +
                "\nback tracking set queen " + back_tracking_set_queen +
                "\nback tracking returns " + back_tracking_returns;
    }
}
