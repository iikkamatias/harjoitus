
package Domain;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class asteroidObject extends gameObject{
    private Node pic;
    private Point2D speed;
    private boolean alive = true;
    private Pane root;
    private Random random;
    private int size;
    private Polygon polygon;

    public asteroidObject(Pane root) {
        this.random = new Random();
        this.root = root;
        this.speed = new Point2D(randomSpeed(), randomSpeed());
        this.pic = createAsteroid();
        randomLocation();
    }

    @Override
    public boolean outside() {

        if (this.pic.getTranslateX() > this.root.getWidth() + 30) {
            this.pic.setTranslateX(0);
        }

        if (this.pic.getTranslateX() < -30) {
            this.pic.setTranslateX(this.root.getWidth());
        }

        if (this.pic.getTranslateY() > this.root.getHeight() + 30) {
            this.pic.setTranslateY(0);
        }

        if (this.pic.getTranslateY() < -30) {
            this.pic.setTranslateY(this.root.getHeight());
        }

        return true;

    }

    private void randomLocation() {

        double random = this.random.nextDouble();

        if (random < 0.25) {
            this.pic.setTranslateX(-30);

            if (random < 0.175) {
                this.pic.setTranslateY(this.root.getHeight() * this.random.nextDouble());
            }

        } else if (random < 0.5) {
            this.pic.setTranslateX(this.root.getWidth() + 30);

            if (random < 0.375) {
                this.pic.setTranslateY(this.root.getHeight() * this.random.nextDouble());
            }

        } else if (random < 0.75) {
            this.pic.setTranslateY(-30);

            if (random < 0.675) {
                this.pic.setTranslateX(this.root.getWidth() * this.random.nextDouble());
            }

        } else {
            this.pic.setTranslateY(this.root.getHeight() + 30);

            if (random < 0.875) {
                this.pic.setTranslateX(this.root.getWidth() * this.random.nextDouble());
            } else {
                this.pic.setTranslateX(-30);
            }
        }

    }

    private double randomSpeed() {

        double random = this.random.nextDouble();

        if (random < 0.5) {
            return -random / 10;
        }

        return random / 10;
    }

    @Override
    public double getBoundaries() {
        return 0;
    }

    public Node createAsteroid() {

        Random random = new Random();

        double area = random.nextInt(10);

        if (size <= 1) {

            area = +10;

        } else if (size <= 2) {
            area = +20;
        } else {
            area = +30;
        }

        Polygon polygon = new Polygon();

        double c1 = Math.cos(Math.PI * 2 / 5);
        double c2 = Math.cos(Math.PI / 5);
        double s1 = Math.sin(Math.PI * 2 / 5);
        double s2 = Math.sin(Math.PI * 4 / 5);

        polygon.getPoints().addAll(
                area, 0.0,
                area * c1, -1 * area * s1,
                -1 * area * c2, -1 * area * s2,
                -1 * area * c2, area * s2,
                area * c1, area * s1);

        for (int i = 0; i < polygon.getPoints().size(); i++) {
            int muutos = random.nextInt(5) - 2;
            polygon.getPoints().set(i, polygon.getPoints().get(i) + muutos);
        }

        return polygon;

    }

    public int getSize() {
        return this.size;
    }

    public double setSize() {
        return this.size;
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

    public void lowerspeed(double factor) {
        this.speed = this.speed.multiply(0.9995);
    }

    public boolean inside() {
        return this.pic.getBoundsInParent().intersects(this.root.getBoundsInParent());
    }

}
