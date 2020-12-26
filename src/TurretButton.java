import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;

public class TurretButton implements GameConfig{
    private ChristmasChallenge listener;
    private Image body;
    private Rectangle background;
    private String[] turretButtonAssets = {
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_TURRET_BUTTON + "turret_1.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_TURRET_BUTTON + "turret_2.png"
    };

    public TurretButton (ChristmasChallenge listener, int index) {
        this.listener = listener;

        this.body = new Image(BUTTONS_X_POS,resolveYPos(index),turretButtonAssets[index]);
    }

    private int resolveYPos(int index) {
        return START_BUTTON_Y_POS +  BUTTON_SPACE_INCL_OFFSET * (1 + index);
    }

    public void draw() {
        body.draw();
    }
}
