public class Main {
    public static void main(String args[]) {
        Board board = new Board(10);
        board.forwardCheckingRandPosition();
        board.print();
        System.out.println(board.getStatistic());
    }
}
