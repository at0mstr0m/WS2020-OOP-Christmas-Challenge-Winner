import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.graphics.Point;

import java.util.ArrayList;

public class SantasLittleHelper implements GameConfig, WaveContent {
    private static int waveCounter = 0;
    public static Point[] anchorPoints = new Point[MAX_NUM_OF_FUNDAMENTS];
    static final String[] christmasPresentAssets = {
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_1.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_2.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "candy_cane.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_hat.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_tree.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "drum.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "mistletoe.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "snowflake.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "star.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "trumpet.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "candy_cane_big.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_hat_big.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_tree_big.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "drum_big.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_1_big.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_2_big.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "mistletoe_big.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "snowflake_big.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "star_big.png",
            getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "trumpet_big.png",
    };

    static String[][] turretAssets = {{
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_0_0.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_0_1.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_0_2.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_0_3.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_0_4.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_0_5.png",
        },{
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_1_0.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_1_1.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_1_2.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_1_3.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_1_4.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_1_5.png",
        },{
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_2_0.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_2_1.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_2_2.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_2_3.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_2_4.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_2_5.png",
        }, {
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_3_0.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_3_1.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_3_2.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_3_3.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_3_4.png",
            getWorkingDirectory() + PATH_TO_ASSETS_TURRETS + "turret_3_5.png",
    }};

    public static void fillAnchorPoints() {
        for (int i = 0; i < anchorPoints.length; i++) {
            //if (!Arrays.stream(unusableBuildingSiteIndexes).anyMatch(x -> x == i))
            anchorPoints[i] = new Point(resolveXPos(i),resolveYPos(i));
        }
    }

    private static int resolveXPos(int index) {
        int x = index % FUNDAMENTS_IN_ROW;
        x = FUNDAMENT_DISTANCE_FROM_BORDER + FUNDAMENT_WIDTH_MIDDLE + FUNDAMENT_CENTER_DISTANCE * x;
        return x;
    }

    private static int resolveYPos(int index) {
        int y = index / FUNDAMENTS_IN_ROW;
        y = FUNDAMENT_DISTANCE_FROM_BORDER + FUNDAMENT_HEIGHT_MIDDLE + FUNDAMENT_CENTER_DISTANCE * y;
        return y;
    }

    public static String getWorkingDirectory() {
        return System.getProperty("user.dir");
    }

    public static Line[] getPath() {
        Line[] path = new Line[5];      //5 straight paths
        path[0] = new Line(anchorPoints[57].getXPos(), BOARD_HEIGHT, anchorPoints[9].getXPos(), anchorPoints[9].getYPos(), LINE_COLOR, LINE_WIDTH);
        path[1] = new Line(anchorPoints[9], anchorPoints[12], LINE_COLOR, LINE_WIDTH);
        path[2] = new Line(anchorPoints[12], anchorPoints[52], LINE_COLOR, LINE_WIDTH);
        path[3] = new Line(anchorPoints[52], anchorPoints[54], LINE_COLOR, LINE_WIDTH);
        path[4] = new Line(anchorPoints[54].getXPos(), anchorPoints[54].getYPos(), anchorPoints[6].getXPos(),0, LINE_COLOR, LINE_WIDTH);
        return path;
    }

    public static ArrayList<ChristmasPresent> getNextWave(ChristmasPresentListener listener) {
        ArrayList<ChristmasPresent> nextwave = new ArrayList<>();
        int delayAccumulator = 0;
        for (int i = 0; i < waves[waveCounter].length; i++) {
            float speed = (float) waves[waveCounter][i][0];
            int delayCounter = (int) waves[waveCounter][i][1];
            int type = (int) waves[waveCounter][i][2];
            delayAccumulator += delayCounter;
            nextwave.add(new ChristmasPresent(speed, delayAccumulator, type, listener));
        }
        waveCounter++;
        return nextwave;
    }

    public static Point[] getPresentWaypoints(float presentWidth, float presentHeight) {
        presentWidth /= 2;
        presentHeight /= 2;
        Line[] path = getPath();
        Point[] result = new Point[6];    //needs one more Point than there are Lines
        result[0] = new Point(path[0].getStartpointX() - presentWidth, path[0].getStartpointY());
        for (int i = 1; i < result.length - 1; i++) {
            result[i] = new Point(path[i].getStartpointX() - presentWidth, path[i].getStartpointY() - presentHeight);
        }
        result[result.length - 1] = new Point(path[path.length - 1].getEndpointX() - presentWidth, path[path.length - 1].getEndpointY());
        return result;
    }
}
