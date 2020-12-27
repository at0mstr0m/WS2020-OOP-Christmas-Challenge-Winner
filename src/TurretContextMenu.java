import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;

public class TurretContextMenu implements GameConfig {
    private final String upgradeButtonAsset = SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_TURRET_CONTEXT_MENU + "upgrade_button.png";
    private final String sellButtonAsset = SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_TURRET_CONTEXT_MENU + "sell_button.png";
    private final String closeButtonAsset = SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_TURRET_CONTEXT_MENU + "close_button.png";
    private Turret turretWithContextMenu;
    private Board board;
    private Rectangle body;
    private float xPos;
    private float yPos;
    private Image upgradeButton;
    private Image sellButton;
    private Image closeButton;

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
        this.body.setBorderColor(AMERICAN_PINK);
        this.body.setBorderWeight(3);
        this.upgradeButton = new Image(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, upgradeButtonAsset);
        this.sellButton = new Image(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER * 2 + TURRET_CONTEXT_MENU_BUTTON_WIDTH, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, sellButtonAsset);
        this.closeButton = new Image(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER * 3 + TURRET_CONTEXT_MENU_BUTTON_WIDTH * 2, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, closeButtonAsset);
    }

    public void draw() {
        body.draw();
        upgradeButton.draw();
        sellButton.draw();
        closeButton.draw();
    }

    public void handleMouseClick(int x, int y) {
        if (upgradeButton.hitTest(x,y)) {}
        else if (sellButton.hitTest(x,y)) {}
        else if (closeButton.hitTest(x,y)) board.closeTurretContextMenu();  //close this TurretContextMenu
    }
}
