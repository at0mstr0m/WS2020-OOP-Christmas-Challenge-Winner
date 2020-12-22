package config;

import de.ur.mi.oop.colors.Color;

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

    int WINDOW_WIDTH = 640;
    int WINDOW_HEIGHT = 480;
    Color BACKGROUND_COLOR = LINEN;

}
