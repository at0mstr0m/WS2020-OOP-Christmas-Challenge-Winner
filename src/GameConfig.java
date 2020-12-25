import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.graphics.Rectangle;

public interface GameConfig {
    /**
     * Christmassy colors from https://www.schemecolor.com/christmas-carol.php
     */
    Color FIRE_OPAL = new Color(235, 92, 95);
    Color AMERICAN_PINK = new Color(250, 149, 148);
    Color LINEN = new Color(251, 242, 234);
    Color FLACESCENT = new Color(242, 229, 153);
    Color ARYLIDE_YELLOW = new Color(236, 217, 105);
    Color DARK_SEA_GREEN = new Color(153, 211, 136);

    int BOARD_WIDTH = 800;
    int BOARD_HEIGHT = 800;

    int BOTTOM_UI_WIDTH = BOARD_WIDTH;
    int BOTTOM_UI_HEIGHT = 100;
    int BOTTOM_UI_X_POS = 0;
    int BOTTOM_UI_Y_POS = BOARD_HEIGHT;
    String PATH_TO_ASSETS_PLAY_PAUSE_BUTTON = "\\assets\\ui\\playPauseButton\\";

    int FRAME_RATE = 60;
    int WINDOW_WIDTH = 800;
    int WINDOW_HEIGHT = BOARD_HEIGHT + BOTTOM_UI_HEIGHT;
    Color BACKGROUND_COLOR = DARK_SEA_GREEN;

    int LINE_WIDTH = 40;
    Color LINE_COLOR = FLACESCENT;

    int FUNDAMENT_WIDTH = 64;
    int FUNDAMENT_HEIGHT = 64;
    int FUNDAMENT_HEIGHT_MIDDLE = FUNDAMENT_HEIGHT / 2;
    int FUNDAMENT_WIDTH_MIDDLE = FUNDAMENT_WIDTH / 2;
    int FUNDAMENTS_IN_ROW = 8;
    int FUNDAMENTS_IN_COLUMN = FUNDAMENTS_IN_ROW;
    int FUNDAMENT_DISTANCE_FROM_BORDER = 18;
    int FUNDAMENT_DISTANCE_BETWEEN_FUNDAMENTS = FUNDAMENT_DISTANCE_FROM_BORDER * 2;
    int FUNDAMENT_CENTER_DISTANCE = FUNDAMENT_WIDTH + FUNDAMENT_DISTANCE_BETWEEN_FUNDAMENTS;
    int MAX_NUM_OF_FUNDAMENTS = FUNDAMENTS_IN_ROW * FUNDAMENTS_IN_COLUMN;

    int[] unusableBuildingSiteIndexes = {6,9,10,11,12,14,17,20,22,25,28,30,33,36,38,41,44,46,49,52,53,54,57};
}
