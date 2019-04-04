import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Square {
    private int size;
    private Random r;
    int board[][];

    ArrayList<ArrayList<Integer>> rows = new ArrayList<>();

    public Square(int size) {
        this.size = size;
        r = new Random();
        board = new int[size][size];

        for (int i = 0; i < size; i++) {
            rows.add(new ArrayList<>());
            for (int x = 1; x <= size; x++) {
                rows.get(i).add(x);
            }
        }
    }

    public void calculate() {
        for (int i = 0; i < size; i++) {
            int temp = 1;
            for (int j = 0; j < size; j++) {
                while(check(temp, i, j)){
                    temp ++;
                    if (temp > size) {
                        temp = 1;
                    }
                }
                board[i][j] = temp;
            }
        }
    }

    public void calculateRandomnly() {
        Integer value;
        Integer index;
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
        boolean exist = false;
        for (int x = 0; x < size; x++) {
            if (board[i][x] == value) {
                exist = true;
            }
        }
        // check col
        for (int x = 0; x < size; x++) {
            if (board[x][j] == value) {
                exist = true;
            }
        }
        return exist;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
