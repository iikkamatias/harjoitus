
package Domain;

import java.util.Timer;
import java.util.TimerTask;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class craftObject extends gameObject{
    private Node pic;
    private Point2D speed = new Point2D(0.01, 0.01);
    private boolean alive = true;
    private Pane root;

    public craftObject(Pane root) {
        this.root = root;
        this.pic = new Rectangle(20, 20, Color.RED);
        this.pic.setTranslateY(root.getHeight() / 2);
        this.pic.setTranslateX(root.getWidth() / 2);
    }

    @Override
    public void update() {
        lowerSpeed(0.9998);
        this.pic.setTranslateX(this.pic.getTranslateX() + this.speed.getX());
        this.pic.setTranslateY(this.pic.getTranslateY() + this.speed.getY());
    }

    public void left() {

        this.speed = this.speed.add(-0.07, 0);
    }

    public void right() {
        this.speed = this.speed.add(0.07, 0);
    }

    public void up() {
        this.speed = this.speed.add(0, -0.07);

    }

    public void down() {
        this.speed = this.speed.add(0, 0.07);

    }
  
    
    public void turn(double x, double y) {

        double legA = Math.abs(y - this.pic.getLayoutY());
        double legB = Math.abs(x - this.pic.getLayoutX());

        if (x < this.pic.getLayoutX()) {

            if (y < this.pic.getLayoutY()) {
                this.pic.setRotate(Math.tan(legB / legA) + 270);
            } else {
                this.pic.setRotate(Math.tan(legA / legB));
            }

        } else {

            if (y < this.pic.getLayoutY()) {
                this.pic.setRotate(Math.tan(legA / legB) + 180);
            } else {
                this.pic.setRotate(Math.tan(legA / legB) + 90);
            }

        }

    }
    @Override
    public boolean outside() {

        if (this.pic.getTranslateX() > this.root.getWidth()) {
            this.pic.setTranslateX(0);
        }

        if (this.pic.getTranslateX() < 0) {
            this.pic.setTranslateX(this.root.getWidth());
        }

        if (this.pic.getTranslateY() > this.root.getHeight()) {
            this.pic.setTranslateY(0);
        }

        if (this.pic.getTranslateY() < 0) {
            this.pic.setTranslateY(this.root.getHeight());
        }

        return true;

    }

    @Override
    public double getBoundaries() {
        return 0;
    }

    public void setSpeed(Point2D speed) {
        this.speed = speed;
    }

    public Point2D getSpeed() {
        return speed;
    }

    public Node getPic() {
        return pic;
    }

    public boolean alive() {
        return alive;
    }

    public boolean dead() {
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean crash(Node pic) {

        return this.pic.getBoundsInParent().intersects(pic.getBoundsInParent());

    }

    public void lowerSpeed(double factor) {
        this.speed = this.speed.multiply(0.9995);
    }

    public boolean inside() {
        return this.pic.getBoundsInParent().intersects(this.root.getBoundsInParent());
    }

}
