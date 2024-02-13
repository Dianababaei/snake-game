import java.awt.*;

class Powerup {
    public static final int BOOST = 0;
    public static final int FREEZE = 1;
    public static final int CONFUSE = 2;
    public static final int CLEAR = 3;

    private int type;
    private GameBoard board;
    private int y;

    public Powerup(int type, GameBoard board) {
        this.type = type;
        this.board = board;
    }

    public void draw(Graphics g, int x) {
        Color color = null;
        switch (type) {
            case BOOST: color = Color.RED; break;
            case FREEZE: color = Color.CYAN; break;
            case CONFUSE: color = Color.ORANGE; break;
            case CLEAR: color = Color.MAGENTA; break;
        }
        g.setColor(color);
        g.fillOval(x * board.cellSize, y * board.cellSize, board.cellSize, board.cellSize);
    }}