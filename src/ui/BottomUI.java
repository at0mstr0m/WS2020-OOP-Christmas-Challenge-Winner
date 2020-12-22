package ui;

import config.GameConfig;
import de.ur.mi.oop.graphics.Rectangle;

public class BottomUI implements GameConfig {
    private Rectangle body;

    public BottomUI() {
        body = new Rectangle(BOTTOM_UI_X_POS, BOTTOM_UI_Y_POS, BOTTOM_UI_WIDTH, BOTTOM_UI_HEIGHT, FIRE_OPAL);
    }

     public void draw() {
        body.draw();
     }
}
