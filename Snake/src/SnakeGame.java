import java.util.LinkedList;

public class SnakeGame {
    private int gameSpeed = 100;
    private LinkedList<Point> body;
    private Point head;
    private static Direction snakeDirection;
    Snake theSnake = new Snake();
    GameBoard board = new GameBoard();

    public SnakeGame() {
    }

    public void initializeGame() {
        board.cleanBoard();
        theSnake.createSnake(board.getxCells() / 2, board.getyCells() / 2);
        board.spawnRandomApple();
        addAppleToGameBoard();
    }

    public boolean collidesWith(CellData cellData) {
        body = theSnake.getBody();
        head = body.get(0);
        CellData cell = board.getBoard()[head.getX()][head.getY()];
        return (cell == cellData);

    }

    public boolean snakeIsDead() {
        if (collidesWith(CellData.WALL)
                || collidesWith(CellData.SNAKE)) {
            theSnake.setIsDead(true);
            return true;
        } else {
            theSnake.setIsDead(false);
            return false;
        }
    }

    public void takeAppleFromGameBoard() {
        board.setDataCell(board.getPointOfApple().getX(), board.getPointOfApple().getY(), CellData.EMPTY);
    }

    public void addAppleToGameBoard() {
        board.setDataCell(board.getPointOfApple().getX(), board.getPointOfApple().getY(), CellData.APPLE);
    }

    public void updateApple() {

        if (collidesWith(CellData.APPLE)) {
            takeAppleFromGameBoard();
            theSnake.eat();
            board.spawnRandomApple();
        } else {
        }
    }

    public void storeDirectionOfSnake(Direction direction) {
        snakeDirection = direction;
    }

    public void changeSnakeDirection() {
        if (snakeDirection != null) {
            theSnake.changeDirection(snakeDirection);
        }
    }

    public void addSnakeToBoard() {
        body = theSnake.getBody();
        for (int i = 0; i < body.size(); i++) {
            board.setDataCell(body.get(i).getX(), body.get(i).getY(), CellData.SNAKE);
            board.setDataCell(theSnake.getTailCell().getX(), theSnake.getTailCell().getY(), CellData.EMPTY);
        }
    }

    public void updateSnake() {
        theSnake.update();
    }

    public void updateBoard() {
        addAppleToGameBoard();
        addSnakeToBoard();
    }

    public void removeSnake() {
        body = theSnake.getBody();
        theSnake.clearBody();
        for (int i = 0; i < body.size(); i++) {
            board.setDataCell(body.get(i).getX(), body.get(i).getY(), CellData.EMPTY);
        }
    }

    public int getGameSpeed() {
        return gameSpeed;
    }
}

