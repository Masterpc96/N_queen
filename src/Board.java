import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int size;

    private int forward_checking_returns = 0;
    private int back_tracking_returns = 0;

    private int forward_checking_set_queen = 0;
    private int back_tracking_set_queen = 0;

    private ArrayList<ArrayList<Integer>> usedCol = new ArrayList<>();

    Board(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            usedCol.add(new ArrayList<>());
        }
    }

    private ArrayList<Queen> queens = new ArrayList<>();

    private int previousCol = 0;

    void forwardCheckingRandPosition() {
        Queen temp = new Queen();
        int col = 0;
        boolean back = false;
        Random r = new Random();
        ArrayList<Integer> temporary;
        for (int row = 0; row < size; row++) {
            temporary = usedCol.get(row);
            back = false;
            do {
                if (temporary.size() == size) {
                    temporary.clear();
                    row -= 2;
                    queens.remove(queens.size() - 1);
                    forward_checking_returns += 1;
                    back = true;
                    break;
                } else {
                    do {
                        col = r.nextInt(size);
                    } while (temporary.contains(col));
                    temp = new Queen(row, col);
                    temporary.add(col);
                }
            } while (checkCollision(temp));
            if (!back) {
                forward_checking_set_queen += 1;
                queens.add(temp);
            }

        }
    }


    public void forwardChecking() {
        Queen temp;
        for (int row = 0; row < size; row++) {
            boolean added = false;

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

    public void backTrackingRandPosition() {
        Queen temp;
        int col;
        Random r = new Random();
        ArrayList<Integer> temporary;
        for (int row = 0; row < size; row++) {

            temporary = usedCol.get(row);
            System.out.println(temporary);

            if (temporary.size() == size) {
                temp = queens.remove(queens.size() - 1);
                row = temp.getRow() - 1;
                back_tracking_returns += 1;
                temporary.clear();
            } else {
                do {
                    col = r.nextInt(size);

                } while (temporary.contains(col));

                temporary.add(col);
                temp = new Queen(row, col);

                queens.add(temp);
                back_tracking_set_queen += 1;

                if (checkCollision(queens.get(queens.size() - 1))) {
                    temp = queens.remove(queens.size() - 1);
                    row = temp.getRow() - 1;
                    back_tracking_returns += 1;
                }
            }
        }

    }

//    public void backTracking() {
//        Queen temp;
//        int previousCol = 0;
//        for (int row = 0; row < size; row++) {
//            temp = new Queen(row, previousCol);
//            queens.add(temp);
//            back_tracking_set_queen += 1;
//
//            if (checkCollision(queens.get(queens.size() - 1))) {
//                temp = queens.remove(queens.size() - 1);
//                row = temp.getRow() - 1;
//                previousCol = temp.getCol() + 1;
//                back_tracking_returns += 1;
//            } else {
//                previousCol = 0;
//            }
//        }
//    }

    public void print() {
        Queen temp;

        // print header
        System.out.print("  |");
        for (int col = 0; col < size; col++) {
            System.out.print(" " + col + " |");
        }
        System.out.println();
        for (int col = 0; col < size - 1; col++) {
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
                    System.out.print("| Q ");
                } else {
                    System.out.print("|   ");
                }
            }


            System.out.println("|");
            for (int col = 0; col < size - 1; col++) {
                System.out.print("-----");
            }
            System.out.println();
        }
    }

    // if there isn't a collision return false
    private boolean checkCollision(Queen queen) {
        return checkVertical(queen) || checkHorizontal(queen) || checkDiagonals(queen);
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

    public void clearBoard() {
        queens.clear();
        for (ArrayList<Integer> integers : usedCol) {
            integers.clear();
        }

    }

    String getStatistic() {
        return "forward checking set queen " + forward_checking_set_queen +
                "\nforward checking returns " + forward_checking_returns +
                "\nback tracking set queen " + back_tracking_set_queen +
                "\nback tracking returns " + back_tracking_returns;
    }
}
