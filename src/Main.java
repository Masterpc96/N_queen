public class Main {
    public static void main(String args[]) {
        Board board = new Board(5);
        board.forwardChecking();
        board.clearBoard();
        board.backTracking();
        System.out.println(board.getStatistic());
    }
}
