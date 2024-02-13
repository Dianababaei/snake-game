import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoard extends JPanel implements ActionListener {
    private Timer timer;
    private Snake snake;
    final int cellSize = 20;
    private final int boardSize = 20;

    public GameBoard() {
        setFocusable(true);
        setPreferredSize(new Dimension(boardSize * cellSize, boardSize * cellSize));
        setBackground(Color.BLACK);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    snake.changeDirection(0);
                } else if (key == KeyEvent.VK_UP) {
                    snake.changeDirection(1);
                } else if (key == KeyEvent.VK_RIGHT) {
                    snake.changeDirection(2);
                } else if (key == KeyEvent.VK_DOWN) {
                    snake.changeDirection(3);
                }
            }
        });

        snake = new Snake(10, 10, 5, 0);
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        for (Point point : snake.getBody()) {
            g.fillRect(point.x * cellSize, point.y * cellSize, cellSize, cellSize);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        if (checkWallCollision() ) {
            timer.stop();
            int result = JOptionPane.showConfirmDialog(this, "Game over! Do you want to restart?", "Snake Game",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                snake = new Snake(10, 10, 5, 0);
                timer.restart();
            } else {
                System.exit(0);
            }
        }
        repaint();
    }

    private boolean checkWallCollision() {
        Point head = snake.getBody().getFirst();
        return head.x < 0 || head.x >= boardSize || head.y < 0 || head.y >= boardSize;
    }

   /* private boolean checkSelfCollision() {
        Point head = snake.getBody().getFirst();
        for (Point point : snake.getBody().subList(1, snake.getBody().size())) {
            if (head.equals(point)) {
                return true;
            }
        }
        return false;
    } */
}