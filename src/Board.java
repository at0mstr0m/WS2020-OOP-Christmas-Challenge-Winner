import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.MouseButton;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Line;

import java.util.ArrayList;

public class Board implements GameConfig, InputEventListener {
    private final String backgroundAsset =SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_BACKGROUND + "board_snow.png";
    private Image background;
    public Line[] path;
    private ArrayList<Circle> buildingSites;
    private ChristmasChallenge mainProgListener;
    private RightUI rightUIListener;
    private ArrayList<Turret> turretsOnTheBoard;
    private TurretContextMenu contextMenu;

    public Board(ChristmasChallenge mainProgListener, RightUI rightUIListener) {
        this.background = new Image(0,0,backgroundAsset);
        this.turretsOnTheBoard = new ArrayList<>();
        this.mainProgListener = mainProgListener;
        this.rightUIListener = rightUIListener;
        this.path = SantasLittleHelper.getPath();
        this.buildingSites = getFittingBuildingSites();
    }

    private ArrayList<Circle> getFittingBuildingSites() {
        ArrayList<Circle> result = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_OF_FUNDAMENTS; i++) {
            boolean unusable = false;
            for (int j = 0; j < unusableBuildingSiteIndexes.length; j++) {  //sort out unusable spots that are lying on the path.
                if (unusableBuildingSiteIndexes[j] == i) {
                    unusable = true;
                    break;
                }
            }
            if (!unusable) result.add(new Circle(SantasLittleHelper.anchorPoints[i], FUNDAMENT_RADIUS, Colors.TRANSPARENT));
        }
        return result;
    }

    private int resolveXPos(int index) {
        int x = index % FUNDAMENTS_IN_ROW;
        x = FUNDAMENT_DISTANCE_FROM_BORDER + (FUNDAMENT_WIDTH  + FUNDAMENT_DISTANCE_BETWEEN_FUNDAMENTS) * x;
        return x;
    }

    private int resolveYPos(int index) {
        int y = index / FUNDAMENTS_IN_ROW;
        y = FUNDAMENT_DISTANCE_FROM_BORDER + (FUNDAMENT_HEIGHT  + FUNDAMENT_DISTANCE_BETWEEN_FUNDAMENTS) * y;
        return y;
    }

    public void draw() {
        background.draw();
        //drawPath();
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
            for (int i = 0; i < turretsOnTheBoard.size(); i++) {
                turretsOnTheBoard.get(i).draw();
            }
        }
    }

    private void drawBuildingSites() {
        for (int i = 0; i < buildingSites.size(); i++) {
            buildingSites.get(i).draw();
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
        if (event.getButton() == MouseButton.LEFT && rightUIListener.currentlyPlacingTurretButtonInstance != null) placeTurretOnBoard(x, y);

        /*
         * Must be a RIGHT click.
         * Player must not be placing turrets and there must already be torrets on the board
         * to make TurretContextMenu relevant.
         */
        else if (event.getButton() == MouseButton.RIGHT && !turretsOnTheBoard.isEmpty()) openTurretContextMenu(x, y);
    }

    private void openTurretContextMenu(int x, int y) {
        for (int i = 0; i < turretsOnTheBoard.size(); i++) {
            if (turretsOnTheBoard.get(i).hitTest(x, y)) {
                // Turret turretWithContextMenu = turretsOnTheBoard.get(i);
                this.contextMenu = new TurretContextMenu(turretsOnTheBoard.get(i), this);
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
                float xPos = buildingSites.get(i).getXPos() - FUNDAMENT_OFFSET_FROM_ANCHOR_POINT;
                float yPos = buildingSites.get(i).getYPos() - FUNDAMENT_OFFSET_FROM_ANCHOR_POINT;
                int type = rightUIListener.currentlyPlacingTurretButtonInstance.getType();
                turretsOnTheBoard.add(new Turret(xPos,yPos,type));
                buildingSites.remove(i);
                buildingSites.trimToSize();
                break;      //only one buildingSite can be clicked at a time
            }
        }
    }
}
