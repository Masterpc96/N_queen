import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Square {
    private int size;
    private int[][] board;

    private ArrayList<ArrayList<Integer>> rows = new ArrayList<>();

    Square(int size) {
        this.size = size;
        board = new int[size][size];

        for (int i = 0; i < size; i++) {
            rows.add(new ArrayList<>());
            for (int x = 1; x <= size; x++) {
                rows.get(i).add(x);
            }
        }
    }

    void calculate() {
        for (int i = 0; i < size; i++) {
            int temp = 1;
            for (int j = 0; j < size; j++) {
                while (check(temp, i, j)) {
                    temp++;
                    if (temp > size) {
                        temp = 1;
                    }
                }
                board[i][j] = temp;
            }
        }
    }

    public void calculateRandomly() {
        Integer value;
        int index;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                do {
                    index = ThreadLocalRandom.current().nextInt(0, rows.get(i).size());
                    value = rows.get(i).get(index);
                    // check row
                } while (check(value, i, j));
                board[i][j] = value;
                rows.get(i).remove(value);
            }
            for (int x = 0; x < size; x++) {
                System.out.print(board[i][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean check(Integer value, int i, int j) {
        for (int x = 0; x < size; x++) {
            if (board[i][x] == value) {
                return true;
            }
        }
        // check col
        for (int x = 0; x < size; x++) {
            if (board[x][j] == value) {
                return true;
            }
        }
        return false;
    }

    void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
