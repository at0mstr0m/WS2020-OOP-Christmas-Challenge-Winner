import de.ur.mi.oop.colors.Color;

public interface GameConfig {
    /**
     * Christmassy colors from https://www.schemecolor.com/christmas-carol.php
     * Turret sprites from https://opengameart.org/content/tower-defense-300-tilessprites
     * ChristmasPresents sprites from https://akari21.itch.io/christmas
     */
    Color FIRE_OPAL = new Color(235, 92, 95);
    Color AMERICAN_PINK = new Color(250, 149, 148);
    Color LINEN = new Color(251, 242, 234);
    Color FLACESCENT = new Color(242, 229, 153);
    Color ARYLIDE_YELLOW = new Color(236, 217, 105);
    Color DARK_SEA_GREEN = new Color(153, 211, 136);

    int BOARD_WIDTH = 800;
    int BOARD_HEIGHT = 800;

    int RIGHT_UI_WIDTH = 100;
    int RIGHT_UI_HEIGHT = BOARD_HEIGHT;
    int RIGHT_UI_X_POS = BOARD_WIDTH;
    int RIGHT_UI_Y_POS = 0;
    int START_BUTTON_Y_POS = 18;

    int BUTTONS_X_POS = BOARD_WIDTH + START_BUTTON_Y_POS;
    int BUTTON_WIDTH = 64;
    int BUTTON_WIDTH_MIDDLE = BUTTON_WIDTH / 2;
    int BUTTON_HEIGHT = BUTTON_WIDTH ;
    int BUTTON_HEIGHT_MIDDLE = BUTTON_HEIGHT / 2;

    int BUTTON_SPACE_INCL_OFFSET = 100;
    String PATH_TO_ASSETS_PLAY_PAUSE_BUTTON = "\\assets\\ui\\startButton\\";
    String PATH_TO_ASSETS_TURRET_BUTTON = "\\assets\\ui\\turrets\\";
    String PATH_TO_ASSET_BACKGROUND = "\\assets\\ui\\";
    String PATH_TO_ASSET_CHRISTMAS_PRESENTS = "\\assets\\christmasPresents\\";

    int FRAME_RATE = 60;
    int WINDOW_WIDTH = BOARD_WIDTH + RIGHT_UI_WIDTH;
    int WINDOW_HEIGHT = BOARD_HEIGHT;
    Color BACKGROUND_COLOR = DARK_SEA_GREEN;

    int NUM_OF_TURRETS = 2;

    int LINE_WIDTH = 40;
    Color LINE_COLOR = FLACESCENT;

    int FUNDAMENT_WIDTH = 64;
    int FUNDAMENT_HEIGHT = 64;
    int FUNDAMENT_RADIUS = 35;
    int FUNDAMENT_OFFSET_FROM_ANCHOR_POINT = 32;
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
