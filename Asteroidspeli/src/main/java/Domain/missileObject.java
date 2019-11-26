
package Domain;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class missileObject {
    private Node pic;
    private Point2D speed;
    private boolean alive = true;
    private Pane root;

    public missileObject(Pane root) {
        this.root = root;
        this.pic = new Circle(0, 0, 5, Color.BLACK);

    }

    public void setLocation(double x, double y) {
        this.pic.setTranslateX(x);
        this.pic.setTranslateY(y);
    }
    
    public boolean outside() {

        setAlive(false);

        if (this.pic.getTranslateX() > this.root.getWidth()) {
            return true;
        }

        if (this.pic.getTranslateX() < 0) {
            return true;
        }

        if (this.pic.getTranslateY() > this.root.getHeight()) {
            return true;

        }

        if (this.pic.getTranslateY() < 0) {
            return true;

        }

        setAlive(true);

        return false;
    }

    public double getBoundaries() {
        return 0;
    }

    public void update() {
        this.pic.setTranslateX(this.pic.getTranslateX() + this.speed.getX());
        this.pic.setTranslateY(this.pic.getTranslateY() + this.speed.getY());
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

