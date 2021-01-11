import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.graphics.Point;

import java.util.ArrayList;

/**
 * This class helps sharing common data between different classes.
 */
public final class SantasStaticHelper implements GameConfig, WaveContent {
    public static Point[] anchorPoints = new Point[MAX_NUM_OF_FUNDAMENTS];  // stores anchorpoints
    static String[][] turretAssets = {{
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_0_0.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_0_1.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_0_2.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_0_3.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_0_4.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_0_5.png",
    }, {
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_1_0.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_1_1.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_1_2.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_1_3.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_1_4.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_1_5.png",
    }, {
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_2_0.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_2_1.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_2_2.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_2_3.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_2_4.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_2_5.png",
    }, {
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_3_0.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_3_1.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_3_2.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_3_3.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_3_4.png",
            System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_3_5.png",
    }};
    private static int waveCounter = 0;                                     // counts the waves

    public static void fillAnchorPoints() {
        for (int i = 0; i < anchorPoints.length; i++) {
            anchorPoints[i] = new Point(resolveXPos(i), resolveYPos(i));
        }
    }

    /**
     * Resolves X Position of a Point in anchorPoints
     *
     * @param index
     * @return
     */
    private static int resolveXPos(int index) {
        int x = index % FUNDAMENTS_IN_ROW;
        x = FUNDAMENT_DISTANCE_FROM_BORDER + FUNDAMENT_WIDTH_MIDDLE + FUNDAMENT_CENTER_DISTANCE * x;
        return x;
    }

    /**
     * Resolves Y Position of a Point in anchorPoints
     *
     * @param index
     * @return
     */
    private static int resolveYPos(int index) {
        int y = index / FUNDAMENTS_IN_ROW;
        y = FUNDAMENT_DISTANCE_FROM_BORDER + FUNDAMENT_HEIGHT_MIDDLE + FUNDAMENT_CENTER_DISTANCE * y;
        return y;
    }

    public static Line[] getPath() {
        Line[] path = new Line[5];      //5 straight paths
        path[0] = new Line(anchorPoints[57].getXPos(), BOARD_HEIGHT, anchorPoints[9].getXPos(), anchorPoints[9].getYPos(), LINE_COLOR, LINE_WIDTH);
        path[1] = new Line(anchorPoints[9], anchorPoints[12], LINE_COLOR, LINE_WIDTH);
        path[2] = new Line(anchorPoints[12], anchorPoints[52], LINE_COLOR, LINE_WIDTH);
        path[3] = new Line(anchorPoints[52], anchorPoints[54], LINE_COLOR, LINE_WIDTH);
        path[4] = new Line(anchorPoints[54].getXPos(), anchorPoints[54].getYPos(), anchorPoints[6].getXPos(), 0, LINE_COLOR, LINE_WIDTH);
        return path;
    }

    public static ArrayList<ChristmasPresent> getNextWave(ChristmasPresentListener listener) {
        ArrayList<ChristmasPresent> nextWave = new ArrayList<>();
        int delayAccumulator = 0;
        for (int i = 0; i < waves[waveCounter].length; i++) {
            float speed = (float) waves[waveCounter][i][0];
            int delayCounter = (int) waves[waveCounter][i][1];
            int type = (int) waves[waveCounter][i][2];
            delayAccumulator += delayCounter;
            nextWave.add(new ChristmasPresent(speed, delayAccumulator, type, listener));
        }
        waveCounter++;
        return nextWave;
    }

    /**
     * Resolves waypoints for ChristmasPresents
     * Not very elegant but it works.
     *
     * @param presentWidth
     * @param presentHeight
     * @return
     */
    public static Point[] getPresentWaypoints(float presentWidth, float presentHeight) {
        presentWidth /= 2;
        presentHeight /= 2;
        Line[] path = getPath();
        Point[] result = new Point[6];    // needs one more Point than there are Lines between waypoints
        result[0] = new Point(path[0].getStartpointX() - presentWidth, path[0].getStartpointY());
        for (int i = 1; i < result.length - 1; i++) {
            result[i] = new Point(path[i].getStartpointX() - presentWidth, path[i].getStartpointY() - presentHeight);
        }
        result[result.length - 1] = new Point(path[path.length - 1].getEndpointX() - presentWidth, path[path.length - 1].getEndpointY());
        return result;
    }

    public static int getWaveCounter() {
        return waveCounter;
    }

    public static String getWaveCounterAsString() {
        return "Wave: " + waveCounter;
    }
}
