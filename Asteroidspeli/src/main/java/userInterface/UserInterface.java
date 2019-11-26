
package userInterface;

import Domain.craftObject;
import Domain.missileObject;
import Domain.asteroidObject;
import Domain.gameObjects;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterface extends Application{
    
private Pane root = new Pane();
    private gameObjects objects = new gameObjects(this.root);

    private Parent game() {

        this.root.setPrefSize(1000, 1000);

        Text text = new Text(10, 20, "Points: 0");

        this.root.getChildren().add(text);

        AtomicInteger points = new AtomicInteger();

        newCraftObject();

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                update(text, points);

                if (gameOver()) {
                    super.stop();

                    endPanel();
                }

            }
        };

        timer.start();

        return root;
    }

    private Pane endPanel() {

        Button start = new Button("start");

        HBox menu = new HBox(start);

        menu.setPadding(new Insets(20, 20, 20, 20));

        menu.setSpacing(10);

        return menu;
    }

    private void update(Text text, AtomicInteger points) {

        this.objects.chrashes();
        this.root = this.objects.Update();

        if (Math.random() < 0.005) {
            newAsteroidObject();
        }
    }

    @Override
    public void start(Stage window) throws Exception {

        Scene game = new Scene(game());
        Scene endPanel = new Scene(endPanel());

        window.setScene(game);

        craftObject craft = this.objects.getCraft();

        game.setOnKeyPressed(event -> {

            switch (event.getCode()) {
                case A:
                    craft.left();
                    break;
                case D:
                    craft.right();
                    break;
                case W:
                    craft.up();
                    break;
                case S:
                    craft.down();
                    break;
            }

            this.objects.setCraft(craft);

        });

        game.setOnMouseMoved(event -> {
            craft.turn(event.getX(), event.getY());

        });

        game.setOnMouseClicked(event -> {
            newMissileObject();
        });

        window.setOnCloseRequest(event -> {

        });

        window.show();

    }

    public craftObject newCraftObject() {
        craftObject craft = new craftObject(this.root);

        this.objects.addCraft(craft);

        this.root.getChildren().add(craft.getPic());

        return craft;
    }

    public asteroidObject newAsteroidObject() {
        asteroidObject asteroid = new asteroidObject(this.root);

        this.objects.addAsteroid(asteroid);

        this.root.getChildren().add(asteroid.getPic());

        return asteroid;
    }

    public missileObject newMissileObject() {
        missileObject missile = new missileObject(this.root);
        missile.setLocation(this.objects.getCraft().getPic().getTranslateX(), this.objects.getCraft().getPic().getTranslateY());
        missile.setSpeed(this.objects.getCraft().getSpeed().normalize().multiply(1.03));

        this.objects.addMissile(missile);

        this.root.getChildren().add(missile.getPic());

        return missile;
    }

    public boolean gameOver() {

        return this.objects.getCraft().dead();
    }

}
