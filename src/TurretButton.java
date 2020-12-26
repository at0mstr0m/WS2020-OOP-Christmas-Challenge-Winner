import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;

public class TurretButton implements GameConfig {
    public int index;
    private final ChristmasChallenge mainProgListener;
    private final RightUI uIListener;
    private Image body;
    private final Rectangle background;
    private final String[] turretButtonAssets = {
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_TURRET_BUTTON + "turret_1.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_TURRET_BUTTON + "turret_2.png"
    };
    private boolean buildMode;

    public TurretButton(ChristmasChallenge mainProgListener, RightUI uIListener, int index) {
        this.index = index;
        this.mainProgListener = mainProgListener;
        this.uIListener = uIListener;
        this.background = new Rectangle(BUTTONS_X_POS, resolveYPos(index), BUTTON_WIDTH, BUTTON_HEIGHT, ARYLIDE_YELLOW);
        this.body = new Image(BUTTONS_X_POS, resolveYPos(index), turretButtonAssets[index]);
        this.buildMode = false;
    }

    private int resolveYPos(int index) {
        return START_BUTTON_Y_POS + BUTTON_SPACE_INCL_OFFSET * (1 + index);
    }

    public void draw() {
        background.draw();
        if (buildMode)
            body.setPosition(mainProgListener.getCurrentMouseXPos() - BUTTON_WIDTH_MIDDLE, mainProgListener.getCurrentMouseYPos() - BUTTON_HEIGHT_MIDDLE);
        body.draw();
    }

    public boolean hitTest(int x, int y) {
        return this.background.hitTest(x, y);    //aim at background which is the whole button
    }

    public void lockOrUnlockAsset(int x, int y) {
        if (!buildMode) {
            buildMode = true;
            uIListener.currentlyPlacingTurretButtonInstance = this;
        } else {
            buildMode = false;
            uIListener.currentlyPlacingTurretButtonInstance = null;
            this.body = new Image(BUTTONS_X_POS, resolveYPos(this.index), turretButtonAssets[this.index]);
        }
    }
}
