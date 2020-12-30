import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Rectangle;

public class TurretContextMenu implements GameConfig {
    private final String upgradeButtonAsset = SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_TURRET_CONTEXT_MENU + "upgrade_button.png";
    private final String sellButtonAsset = SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_TURRET_CONTEXT_MENU + "sell_button.png";
    private final String closeButtonAsset = SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_TURRET_CONTEXT_MENU + "close_button.png";
    private final String sellLabelText = "Sell for Half";
    private Turret turretWithContextMenu;
    private Board board;
    private Rectangle body;
    private float xPos;
    private float yPos;
    private Image upgradeButton;
    private Image sellButton;
    private Image closeButton;
    private int upgradePrice;
    private Label pricetag;
    private Label sellLabel;

    public TurretContextMenu(Turret turretWithContextMenu, Board board) {
        this.turretWithContextMenu = turretWithContextMenu;
        this.board = board;
        this.xPos = turretWithContextMenu.getTurretCenter().getXPos();
        this.yPos = turretWithContextMenu.getTurretCenter().getYPos();
        this.body = new Rectangle(xPos, yPos,TURRET_CONTEXT_MENU_WIDTH,TURRET_CONTEXT_MENU_HEIGHT, FIRE_OPAL);
        if (this.body.getBottomBorder() > BOARD_HEIGHT) {
            this.yPos -= TURRET_CONTEXT_MENU_HEIGHT;
            this.body = new Rectangle(xPos, yPos,TURRET_CONTEXT_MENU_WIDTH,TURRET_CONTEXT_MENU_HEIGHT, FIRE_OPAL);
        }
        if (this.body.getRightBorder() > BOARD_WIDTH) {
            this.xPos -= TURRET_CONTEXT_MENU_WIDTH;
            this.body = new Rectangle(xPos, yPos,TURRET_CONTEXT_MENU_WIDTH,TURRET_CONTEXT_MENU_HEIGHT, FIRE_OPAL);
        }
        this.body.setBorderColor(TURRET_CONTEXT_MENU_BORDER_COLOR);
        this.body.setBorderWeight(TURRET_CONTEXT_MENU_BORDER_WEIGHT);
        this.sellButton = new Image(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER * 2 + TURRET_CONTEXT_MENU_BUTTON_WIDTH, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, sellButtonAsset);
        this.sellLabel = new Label(0, this.sellButton.getBottomBorder() - 7, sellLabelText, DARK_SEA_GREEN);
        this.sellLabel.setFontSize(15);
        this.sellLabel.setXPos(this.sellButton.getLeftBorder() + BUTTON_WIDTH / 2 - (sellLabel.getWidthEstimate() / 2));
        this.closeButton = new Image(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER * 3 + TURRET_CONTEXT_MENU_BUTTON_WIDTH * 2, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, closeButtonAsset);
        if (this.turretWithContextMenu.getLevel() + 1 != turretBuildingPrices[this.turretWithContextMenu.getType()].length) {   // if no further update is available, do not show upgrade button and label
            this.upgradeButton = new Image(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, upgradeButtonAsset);
            this.upgradePrice = turretBuildingPrices[this.turretWithContextMenu.getType()][this.turretWithContextMenu.getLevel() + 1];
            this.pricetag = new Label(0, this.upgradeButton.getBottomBorder() - 7, "$ " + this.upgradePrice, DARK_SEA_GREEN);
            this.pricetag.setFontSize(15);
            this.pricetag.setXPos(this.upgradeButton.getLeftBorder() + BUTTON_WIDTH / 2 - (pricetag.getWidthEstimate() / 2));
        }
    }

    public void draw() {
        body.draw();
        if (upgradeButton != null) upgradeButton.draw();
        if (pricetag != null) pricetag.draw();
        sellButton.draw();
        sellLabel.draw();
        closeButton.draw();
    }

    public void handleMouseClick(int x, int y) {
        if (upgradeButton != null && upgradeButton.hitTest(x,y)) {  // click on upgradeButton
            if (turretWithContextMenu.levelUp()) board.closeTurretContextMenu();
        }
        else if (sellButton.hitTest(x,y)) {                         // click on sellButton
            board.sellTurret(turretWithContextMenu);
            board.closeTurretContextMenu();
        }
        else if (closeButton.hitTest(x,y)) board.closeTurretContextMenu();  //close this TurretContextMenu
    }
}
