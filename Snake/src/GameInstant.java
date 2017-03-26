import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameInstant extends JFrame {
    private JPanel scorePanel;
    private int windowWidth;
    private int windowHeight;

    SnakeGame snakeGame = new SnakeGame();

    public GameInstant(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    snakeGame.storeDirectionOfSnake(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    snakeGame.storeDirectionOfSnake(Direction.UP);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snakeGame.storeDirectionOfSnake(Direction.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    snakeGame.storeDirectionOfSnake(Direction.DOWN);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        GameBoard gameBoard = new GameBoard(this.windowWidth, this.windowHeight);
        DrawingTheBoard gamePanel = new DrawingTheBoard(gameBoard.getxCells(), gameBoard.getyCells(), gameBoard.getBoard());
        this.add(gamePanel, BorderLayout.CENTER);

        scorePanel = new JPanel();
        scorePanel.add(gamePanel.scoreLabel, BorderLayout.CENTER);
        this.add(scorePanel, BorderLayout.PAGE_END);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0, snakeGame.getGameSpeed(), TimeUnit.MILLISECONDS);
    }
}

class RepaintTheBoard implements Runnable {
    private GameInstant theGame;

    public RepaintTheBoard(GameInstant theGame) {

        this.theGame = theGame;
    }

    public void run() {
        theGame.repaint();
    }
}

class DrawingTheBoard extends JComponent {
    public JLabel scoreLabel;
    private int xCells;
    private int yCells;
    private boolean inGame = false;
    private int score = 0;
    CellData[][] board;
    SnakeGame snakeGame = new SnakeGame();
    Snake theSnake = new Snake();

    public DrawingTheBoard(int xCells, int yCells, CellData[][] board) {
        this.xCells = xCells;
        this.yCells = yCells;
        this.board = board;
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 40));
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setBackground(Color.BLACK);
        g2D.fillRect(0, 0, getWidth(), getHeight());
        update();

        for (int i = 0; i < xCells; i++) {
            for (int j = 0; j < yCells; j++) {
                if (board[i][j] == CellData.APPLE || board[i][j] == CellData.SNAKE) {
                    g2D.setPaint(Color.WHITE);
                    g2D.fillRect(i * 10, j * 10, 10, 10);
                } else if (board[i][j] == CellData.WALL) {
                    g2D.setPaint(Color.RED);
                    g2D.fillRect(i * 10, j * 10, 10, 10);
                }
            }
        }
        if (theSnake.hasEatenApple()) {
            score += 10;
            scoreLabel.setText("Score: " + Integer.toString(score));
        } else if (theSnake.isDead()) {
            score = 0;
            scoreLabel.setText("Score: " + Integer.toString(score));
        }
    }

    public void update() {
        if (inGame == false) {
            snakeGame.initializeGame();
            inGame = true;
        }
        snakeGame.changeSnakeDirection();
        snakeGame.updateSnake();
        if (snakeGame.snakeIsDead()) {
            snakeGame.removeSnake();
            snakeGame.initializeGame();
        }
        snakeGame.updateApple();
        snakeGame.updateBoard();
    }
}
