import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Rectangle;

public class TurretButton implements GameConfig {
    private int type;
    private final ChristmasDefense mainProgListener;
    private final RightUI uIListener;
    private Image body;
    private final Rectangle background;
    private boolean buildMode;
    private int price;
    private Label pricetag;

    public TurretButton(ChristmasDefense mainProgListener, RightUI uIListener, int type) {
        this.type = type;
        this.mainProgListener = mainProgListener;
        this.uIListener = uIListener;
        this.background = new Rectangle(BUTTONS_X_POS, resolveYPos(type), BUTTON_WIDTH, BUTTON_HEIGHT, ARYLIDE_YELLOW);
        this.body = new Image(BUTTONS_X_POS, resolveYPos(type), SantasLittleHelper.turretAssets[type][0]);
        this.buildMode = false;
        this.price = turretBuildingPrices[type][0];
        this.pricetag = new Label(0, body.getBottomBorder(), "$ " + this.price, DARK_SEA_GREEN);
        this.pricetag.setFontSize(15);
        this.pricetag.setXPos(body.getLeftBorder() + BUTTON_WIDTH / 2 - (pricetag.getWidthEstimate() / 2));
    }

    private int resolveYPos(int index) {
        return START_BUTTON_Y_POS + BUTTON_SPACE_INCL_OFFSET * (1 + index);
    }

    public void draw() {
        background.draw();
        if (buildMode)
            body.setPosition(mainProgListener.getCurrentMouseXPos() - BUTTON_WIDTH_MIDDLE, mainProgListener.getCurrentMouseYPos() - BUTTON_HEIGHT_MIDDLE);
        pricetag.draw();
        body.draw();
    }

    public boolean hitTest(int x, int y) {
        return this.background.hitTest(x, y);    //aim at background which is the whole button
    }

    public void toggleBuildMode() {
        if (!buildMode) {
            buildMode = true;
            uIListener.currentlyPlacingTurretButtonInstance = this;
        } else {
            buildMode = false;
            uIListener.currentlyPlacingTurretButtonInstance = null;
            this.body = new Image(BUTTONS_X_POS, resolveYPos(this.type), SantasLittleHelper.turretAssets[this.type][0]);
        }
    }

    public int getType() {
        return this.type;
    }
}
