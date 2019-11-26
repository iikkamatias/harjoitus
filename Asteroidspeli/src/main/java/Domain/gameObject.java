
package Domain;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class gameObject {
    private Node pic;
    private Point2D speed;
    private boolean alive;
    private Pane root;
    
    public gameObject() {
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

    /**
     * palauttaa true, jos kaksi Node-luokan oliota törmäävät.
     * @param pic Node-luokan muuttuja
     * @return boolean 
     */
    
    public boolean crash(Node pic) {

        return this.pic.getBoundsInParent().intersects(pic.getBoundsInParent());

    }

    public double getBoundaries() {
        return 0;
    }

    public boolean outside() {
        return true;
    }

    /**
     * Muuttaa objektin nopeuden = speed*factor.
     * @param factor double
     */
    public void lowerSpeed(double factor) {
        this.speed = this.speed.multiply(factor);
    }

    /**
     * Kertoo onko objekti ruudun sisällä.
     * @return boolean 
     */
    public boolean inside() {
        return this.pic.getBoundsInParent().intersects(this.root.getBoundsInParent());
    }
}
