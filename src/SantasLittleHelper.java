import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.graphics.Point;

public class SantasLittleHelper implements GameConfig, WaveContent {
    private static int waveCounter = 0;
    public static Point[] anchorPoints = new Point[MAX_NUM_OF_FUNDAMENTS];
    static final String[] christmasPresentAssets = {
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_1.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_2.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "candy_cane.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_hat.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_tree.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "drum.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "mistletoe.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "snowflake.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "star.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "trumpet.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "candy_cane_big.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_hat_big.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_tree_big.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "drum_big.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_1_big.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_2_big.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "mistletoe_big.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "snowflake_big.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "star_big.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "trumpet_big.png",
    };

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

    public static ChristmasPresent createRandomPresent(float speed, int delayCounter, ChristmasPresentListener listener) {
        return new ChristmasPresent(speed, delayCounter, listener);
    }


    public static ChristmasPresent[] getNextWave(ChristmasPresentListener listener) {
        ChristmasPresent[] nextWave = new ChristmasPresent[waves[waveCounter].length];
        int delayAccumulator = 0;
        for (int i = 0; i < nextWave.length; i++) {
            float speed = (float) waves[waveCounter][i][0];
            int delayCounter = (int) waves[waveCounter][i][1];
            delayAccumulator += delayCounter;
            nextWave[i] = new ChristmasPresent(speed, delayAccumulator, listener);
        }
        waveCounter++;
        return nextWave;
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
