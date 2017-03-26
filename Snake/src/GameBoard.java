import java.util.Random;

public class GameBoard {
    private static int windowWidth;
    private static int windowHeight;
    private static int boardWidth;
    private static int boardHeight;
    private static int xCells;
    private static int yCells;
    private static CellData board[][];
    private int randomXPos;
    private int randomYPos;
    private Point applePoint;
    Random r = new Random();

    public GameBoard() {
    }

    public GameBoard(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        boardWidth = this.windowWidth;
        boardHeight = this.windowHeight - 100;
        xCells = boardWidth / 10;
        yCells = boardHeight / 10;
        board = new CellData[xCells][yCells];
    }

    public void cleanBoard() {
        for (int i = 0; i < xCells; i++) {
            board[i][0] = CellData.WALL;
        }
        for (int i = 0; i < xCells; i++) {
            board[i][yCells - 1] = CellData.WALL;
        }
        for (int j = 0; j < yCells; j++) {
            board[0][j] = CellData.WALL;
        }
        for (int j = 0; j < yCells; j++) {
            board[xCells - 1][j] = CellData.WALL;
        }
        for (int i = 1; i < xCells - 1; i++) {
            for (int j = 1; j < yCells - 1; j++) {
                board[i][j] = CellData.EMPTY;
            }
        }
    }

    public void spawnRandomApple() {
        randomXPos = r.nextInt(xCells - 2) + 1;
        randomYPos = r.nextInt(yCells - 2) + 1;
        applePoint = new Point(randomXPos, randomYPos);
    }

    public Point getPointOfApple() {
        return applePoint;
    }

    public void setDataCell(int x, int y, CellData cellData) {
        board[x][y] = cellData;
    }

    public CellData[][] getBoard() {
        return board;
    }

    public int getxCells() {
        return xCells;
    }

    public int getyCells() {
        return yCells;
    }


}



