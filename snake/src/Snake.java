import java.awt.Point;
import java.util.LinkedList;

    public class Snake {
        private LinkedList<Point> body;
        private int direction;

        public Snake(int x, int y, int length, int direction) {
            body = new LinkedList<>();
            this.direction = direction;

            for (int i = 0; i < length; i++) {
                body.add(new Point(x - i, y));
            }
        }

        public void changeDirection(int newDirection) {
            this.direction = newDirection;
        }

        public void move() {
            // اضافه کردن موقعیت جدید سر مار بر اساس جهت حرکت
            // حرکت به چپ, بالا, راست, پایین به ترتیب با اعداد 0 تا 3 نمایش داده شده‌اند
            Point head = body.getFirst();
            int x = head.x, y = head.y;
            switch (direction) {
                case 0:
                    x--;
                    break;
                case 1:
                    y--;
                    break;
                case 2:
                    x++;
                    break;
                case 3:
                    y++;
                    break;
            }
            body.addFirst(new Point(x, y));
            body.removeLast();
        }

        public LinkedList<Point> getBody() {
            return body;
        }
    }

