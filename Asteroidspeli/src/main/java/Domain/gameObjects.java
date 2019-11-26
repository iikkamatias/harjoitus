
package Domain;

import Domain.gameObject;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;

public class gameObjects extends gameObject{
    private List<asteroidObject> asteroids;
    private List<missileObject> missiles;
    private List<gameObject> objects;
    private craftObject craft;

    private Pane root;

    public gameObjects(Pane root) {
        this.root = root;
        this.asteroids = new ArrayList();
        this.missiles = new ArrayList();
        this.objects = new ArrayList();
    }

    public void addAsteroid(asteroidObject asteroid) {
        this.asteroids.add(asteroid);
    }

    public void addMissile(missileObject missile) {
        this.missiles.add(missile);
    }

    public void addCraft(craftObject craft) {
        this.craft = craft;
    }
    
    /**
     * Käydään läpi kaikki asteroidit ja selvitetään törmääkö ne toisiin PeliObjekteihin (myös asteroideihin) ja törmätessään PeliObjektit merkitään kuolleiksi.         
     */

    public void chrashes() {

        for (gameObject object : this.objects) {

            for (asteroidObject asteroid : this.asteroids) {

                if (object != asteroid) {

                    if (asteroid.crash(object.getPic()) && (asteroid.inside()== true && object.inside()== true)) {

                        asteroid.setAlive(false);
                        object.setAlive(false);
                        this.root.getChildren().removeAll(asteroid.getPic(), object.getPic());

                    }
                }
            }

        }

    }

    public Pane Update() {
        
        List<gameObject> toDelete = new ArrayList();
 
        for (asteroidObject asteroid : this.asteroids) {
            if (asteroid.dead()) {
                this.asteroids.remove(asteroid);
                this.root.getChildren().remove(asteroid);
            } else {
                asteroid.update();
            }
        }

        for (missileObject missile : this.missiles) {
            if (missile.dead()) {
                this.asteroids.remove(missile);
                this.root.getChildren().remove(missile);
            } else {
                missile.update();
            }
        }
               
        this.craft.update();
        
        this.root.getChildren().removeAll(toDelete);

        return this.root;

    }

    public craftObject getCraft() {
        return this.craft;
    }

    public void setCraft(craftObject craft) {
        this.craft = craft;
    }

    public Pane getPane() {
        return this.root;
    }

}
