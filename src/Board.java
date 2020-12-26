import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.graphics.Rectangle;

import java.util.ArrayList;

public class Board implements GameConfig, InputEventListener, BoardListener{
    public Line[] path;
    private ArrayList<Rectangle> buildingSites;
    private ChristmasChallenge listener;

    public Board(ChristmasChallenge listener) {
        this.listener = listener;
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
        drawPath();
        drawBuildingSites();
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

    }
}
