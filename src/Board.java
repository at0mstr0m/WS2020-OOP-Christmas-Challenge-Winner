import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.MouseButton;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Line;

import java.util.ArrayList;

public class Board implements GameConfig, InputEventListener {
    private final String backgroundAsset = System.getProperty("user.dir") + PATH_TO_ASSET_BACKGROUND + "board_snow.png";
    private Image background;
    public Line[] path;
    private ArrayList<Circle> buildingSites;
    private BoardListener boardListener;
    private RightUI rightUIListener;
    private ArrayList<Turret> turretsOnTheBoard;
    private TurretContextMenu contextMenu;

    public Board(BoardListener boardListener, RightUI rightUIListener) {
        this.background = new Image(0,0,backgroundAsset);
        this.turretsOnTheBoard = new ArrayList<>();
        this.boardListener = boardListener;
        this.rightUIListener = rightUIListener;
        this.path = SantasStaticHelper.getPath();
        this.buildingSites = getFittingBuildingSites();
    }

    private ArrayList<Circle> getFittingBuildingSites() {
        ArrayList<Circle> result = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_OF_FUNDAMENTS; i++) {
            boolean unusable = false;
            for (int unusableBuildingSiteIndex : unusableBuildingSiteIndexes) {  //sort out unusable spots that are lying on the path.
                if (unusableBuildingSiteIndex == i) {
                    unusable = true;
                    break;
                }
            }
            if (!unusable) result.add(new Circle(SantasStaticHelper.anchorPoints[i], FUNDAMENT_RADIUS, Colors.TRANSPARENT));
        }
        return result;
    }

    public void draw() {
        background.draw();
        drawBuildingSites();
        drawBuiltTurrets();
        drawContextMenu();
    }

    private void drawContextMenu() {
        if (contextMenu != null) {
            contextMenu.draw();
        }
    }

    private void drawBuiltTurrets() {
        if (turretsOnTheBoard.size() != 0) {
            for (Turret turret : turretsOnTheBoard) {
                turret.draw();
            }
        }
    }

    private void drawBuildingSites() {
        for (Circle buildingSite : buildingSites) {
            buildingSite.draw();
        }
    }

    @Override
    public void handleMouseClick(int x, int y) {

    }

    public void handleMouseClick(MousePressedEvent event) {
        int x = event.getXPos();
        int y = event.getYPos();
        /*
         * Must be a LEFT click.
         * checking if turret can be placed is only necessary if player is currently building a turret
         */
        if (event.getButton() == MouseButton.LEFT && rightUIListener.getCurrentlyPlacingTurretButtonInstance() != null) placeTurretOnBoard(x, y);

        /*
         * Must be a RIGHT click.
         * Player must not be placing turrets and there must already be torrets on the board
         * to make TurretContextMenu relevant.
         */
        else if (rightUIListener.getCurrentlyPlacingTurretButtonInstance() == null && event.getButton() == MouseButton.RIGHT && !turretsOnTheBoard.isEmpty()) openTurretContextMenu(x, y);
        if (event.getButton() == MouseButton.LEFT && contextMenu != null) {
            contextMenu.handleMouseClick(x,y);
        }
    }

    private void openTurretContextMenu(int x, int y) {
        for (Turret turret : turretsOnTheBoard) {
            if (turret.hitTest(x, y)) {
                // Turret turretWithContextMenu = turretsOnTheBoard.get(i);
                this.contextMenu = new TurretContextMenu(turret, this);
                break;      // only one turret can be clicked at a time
            }
        }
    }

    /**
     * Places Turret on an empty buildingSite and removes the buildingSite from its ArrayList so only one Turret
     * can be placed on one buildingSite.
     * @param x
     * @param y
     */
    private void placeTurretOnBoard(int x, int y) {
        for (int i = 0; i < buildingSites.size(); i++) {
            if (buildingSites.get(i).hitTest(x, y)) {
                int type = rightUIListener.getCurrentlyPlacingTurretButtonInstance().getType();
                int price = turretBuildingPrices[type][0];
                if (boardListener.getMoney() >= price) {            //only turrets that can be afforded can be placed
                    boardListener.spendMoney(price);                //substract price from current money
                    float xPos = buildingSites.get(i).getXPos() - FUNDAMENT_OFFSET_FROM_ANCHOR_POINT;
                    float yPos = buildingSites.get(i).getYPos() - FUNDAMENT_OFFSET_FROM_ANCHOR_POINT;
                    switch (type) {
                        case 0 -> turretsOnTheBoard.add(new TurretZero(xPos, yPos, boardListener));
                        case 1 -> turretsOnTheBoard.add(new TurretOne(xPos, yPos, boardListener));
                        case 2 -> turretsOnTheBoard.add(new TurretTwo(xPos, yPos, boardListener));
                        case 3 -> turretsOnTheBoard.add(new TurretThree(xPos, yPos, boardListener));
                    }
                    buildingSites.remove(i);
                    buildingSites.trimToSize();
                }
                break;      //only one buildingSite can be clicked at a time
            }
        }
    }

    public void closeTurretContextMenu() {
        this.contextMenu = null;
    }

    public void sellTurret(Turret turretWithContextMenu) {
        boardListener.earnMoneyFromSelling(turretWithContextMenu.getWorth());                                           //give money from selling
        buildingSites.add(new Circle(turretWithContextMenu.getTurretCenter(), FUNDAMENT_RADIUS, Colors.TRANSPARENT));   //readd buildingsite to buildingSites
        turretsOnTheBoard.remove(turretWithContextMenu);                                                                //remove sold Turret from turretsOnTheBoard
        turretsOnTheBoard.trimToSize();                                                                                 //avoid errors by trimming size
    }

    public void resetFireCounters() {
        for (Turret turret : turretsOnTheBoard) {
            turret.resetFireCounter();
        }
    }
}
