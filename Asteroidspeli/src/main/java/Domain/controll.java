
package Domain;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_MOVED;

public class controll {
    private KeyEvent button;
    private MouseEvent click;
    private MouseEvent movement;
    
    public controll(KeyEvent button) {
        this.button = button;
    }
    
    public controll(MouseEvent a) {
        if(a.getEventType() == MOUSE_CLICKED) {
            this.click = a;
        }
        
        if(a.getEventType() == MOUSE_MOVED) {
            this.movement = a;
        }     
        
    }   
}
