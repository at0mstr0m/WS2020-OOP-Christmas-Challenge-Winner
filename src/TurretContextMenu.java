import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Rectangle;

public class TurretContextMenu implements GameConfig {
    private Turret turretWithContextMenu;
    private Board board;
    private Rectangle body;
    private float xPos;
    private float yPos;
    private Rectangle upgradeButton;
    private Rectangle sellButton;
    private Rectangle closeButton;

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
        this.upgradeButton = new Rectangle(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, TURRET_CONTEXT_MENU_BUTTON_WIDTH, TURRET_CONTEXT_MENU_BUTTON_HEIGHT, Colors.BLACK);
        this.sellButton = new Rectangle(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER * 2 + TURRET_CONTEXT_MENU_BUTTON_WIDTH, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, TURRET_CONTEXT_MENU_BUTTON_WIDTH, TURRET_CONTEXT_MENU_BUTTON_HEIGHT, Colors.PINK);
        this.closeButton = new Rectangle(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER * 3 + TURRET_CONTEXT_MENU_BUTTON_WIDTH * 2, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, TURRET_CONTEXT_MENU_BUTTON_WIDTH, TURRET_CONTEXT_MENU_BUTTON_HEIGHT, Colors.GREEN);
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
