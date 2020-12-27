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
        this.body = new Rectangle(xPos, yPos,TURRET_CONTEXT_MENU_WIDTH,TURRET_CONTEXT_MENU_HEIGHT, Colors.RED);
        upgradeButton = new Rectangle(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, TURRET_CONTEXT_MENU_BUTTON_WIDTH, TURRET_CONTEXT_MENU_BUTTON_HEIGHT, Colors.BLACK);
        sellButton = new Rectangle(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER * 2 + TURRET_CONTEXT_MENU_BUTTON_WIDTH, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, TURRET_CONTEXT_MENU_BUTTON_WIDTH, TURRET_CONTEXT_MENU_BUTTON_HEIGHT, Colors.PINK);
        closeButton = new Rectangle(this.xPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER * 3 + TURRET_CONTEXT_MENU_BUTTON_WIDTH * 2, this.yPos + TURRET_CONTEXT_MENU_DISTANCE_FROM_BORDER, TURRET_CONTEXT_MENU_BUTTON_WIDTH, TURRET_CONTEXT_MENU_BUTTON_HEIGHT, Colors.GREEN);
    }

    public void draw() {
        body.draw();
        upgradeButton.draw();
        sellButton.draw();
        closeButton.draw();
    }
}
