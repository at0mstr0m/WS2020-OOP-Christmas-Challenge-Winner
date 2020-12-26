import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.graphics.Rectangle;

import java.util.ArrayList;

public class Board implements GameConfig, InputEventListener {
    private final String backgroundAsset =SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_BACKGROUND + "board.png";
    private Image background;
    public Line[] path;
    private ArrayList<Rectangle> buildingSites;
    private ChristmasChallenge mainProgListener;
    private RightUI rightUIListener;
    private ArrayList<Turret> builtTurrets;

    public Board(ChristmasChallenge mainProgListener, RightUI rightUIListener) {
        this.background = new Image(0,0,backgroundAsset);
        this.builtTurrets = new ArrayList<>();
        this.mainProgListener = mainProgListener;
        this.rightUIListener = rightUIListener;
        this.path = SantasLittleHelper.getPath();
        this.buildingSites = getFittingBuildingSites();
    }

    private ArrayList<Rectangle> getFittingBuildingSites() {
        ArrayList<Rectangle> result = new ArrayList<>();
        for (int i = 0; i < MAX_NUM_OF_FUNDAMENTS; i++) {
            boolean unusable = false;
            for (int j = 0; j < unusableBuildingSiteIndexes.length; j++) {  //sort out unusable spots that are lying on the path.
                if (unusableBuildingSiteIndexes[j] == i) {
                    unusable = true;
                    break;
                }
            }
            if (!unusable) result.add(new Rectangle(resolveXPos(i),resolveYPos(i),FUNDAMENT_WIDTH,FUNDAMENT_HEIGHT, Colors.GREY));
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
    }

    private void drawBuiltTurrets() {
        if (builtTurrets.size() != 0) {
            for (int i = 0; i < builtTurrets.size(); i++) {
                builtTurrets.get(i).draw();
            }
        }
    }

    private void drawBuildingSites() {
        for (int i = 0; i < buildingSites.size(); i++) {
            buildingSites.get(i).draw();
        }
    }

    private void drawPath() {
        for (int i = 0; i < path.length; i++) {
            path[i].draw();
        }
    }

    @Override
    public void handleMouseClick(int x, int y) {
        if (rightUIListener.currentlyPlacingTurretButtonInstance != null) {     //checking only necessary if player is currently building a turret
            for (int i = 0; i < buildingSites.size(); i++) {
                if (buildingSites.get(i).hitTest(x,y)) {
                    float xPos = buildingSites.get(i).getXPos();
                    float yPos = buildingSites.get(i).getYPos();
                    int type = rightUIListener.currentlyPlacingTurretButtonInstance.getType();
                    builtTurrets.add(new Turret(xPos,yPos,type));
                    buildingSites.remove(i);
                    buildingSites.trimToSize();
                }
            }
        }
    }
}
