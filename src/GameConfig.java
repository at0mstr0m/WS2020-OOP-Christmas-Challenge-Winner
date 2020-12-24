import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Point;

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
    String PATH_TO_ASSETS_PLAY_PAUSE_BUTTON = "assets/ui/playPauseButton";

    int FRAME_RATE = 60;
    int WINDOW_WIDTH = 800;
    int WINDOW_HEIGHT = BOARD_HEIGHT + BOTTOM_UI_HEIGHT;
    Color BACKGROUND_COLOR = DARK_SEA_GREEN;

    int LINE_WIDTH = 40;
    Color LINE_COLOR = FLACESCENT;
    Point waypoints[] = {
            new Point(BOARD_WIDTH / 6, BOARD_HEIGHT),
            new Point(BOARD_WIDTH / 6, BOARD_HEIGHT / 6),
            new Point((BOARD_WIDTH / 6) * 3, BOARD_HEIGHT / 6),
            new Point((BOARD_WIDTH / 6) * 3, (BOARD_WIDTH / 6) * 5),
            new Point((BOARD_WIDTH / 6) * 5, (BOARD_WIDTH / 6) * 5),
            new Point((BOARD_WIDTH / 6) * 5, 0),
    };
}
