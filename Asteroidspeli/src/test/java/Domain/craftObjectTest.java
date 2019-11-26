
package Domain;

import java.awt.geom.Point2D;
import javafx.scene.layout.Pane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class craftObjectTest {
    craftObject craft;
    Pane root;
    
    public craftObjectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.root = new Pane();
        this.root.setPrefSize(1000, 1000);
    }
    
    @After
    public void tearDown() {
    }
public void startingCoordinatesRight() {

        assertEquals((double) 500, craft.getPic().getTranslateX(), 0.01);
        assertEquals((double) 500, craft.getPic().getTranslateY(), 0.01);
    }

}
