import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Rectangle;

public class TurretButton implements GameConfig {
    private int type;
    private final RightUIListener rightUIListener;
    private final TurretButtonListener uIListener;
    private Image body;
    private final Rectangle background;
    private boolean buildMode;
    private int price;
    private Label pricetag;

    public TurretButton(RightUIListener rightUIListener, TurretButtonListener turretButtonListener, int type) {
        this.type = type;
        this.rightUIListener = rightUIListener;
        this.uIListener = turretButtonListener;
        this.background = new Rectangle(BUTTONS_X_POS, resolveYPos(type), BUTTON_WIDTH, BUTTON_HEIGHT, ARYLIDE_YELLOW);
        this.body = new Image(BUTTONS_X_POS, resolveYPos(type), SantasStaticHelper.turretAssets[type][0]);
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
            body.setPosition(rightUIListener.getCurrentMouseXPos() - BUTTON_WIDTH_MIDDLE, rightUIListener.getCurrentMouseYPos() - BUTTON_HEIGHT_MIDDLE);
        pricetag.draw();
        body.draw();
    }

    public boolean hitTest(int x, int y) {
        return this.background.hitTest(x, y);    //aim at background which is the whole button
    }

    public void toggleBuildMode() {
        if (!buildMode) {
            buildMode = true;
            uIListener.setCurrentlyPlacingTurretButtonInstance(this);
        } else {
            buildMode = false;
            uIListener.setCurrentlyPlacingTurretButtonInstance(null);
            this.body = new Image(BUTTONS_X_POS, resolveYPos(this.type), SantasStaticHelper.turretAssets[this.type][0]);
        }
    }

    public int getType() {
        return this.type;
    }
}
