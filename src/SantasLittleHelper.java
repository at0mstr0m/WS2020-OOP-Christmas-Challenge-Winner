import de.ur.mi.oop.graphics.Line;

import java.io.File;
import java.util.ArrayList;

public class SantasLittleHelper implements GameConfig, WaveContent {
    public File[] bottomUIAssets;
    private static int waveCounter = 0;

    public static ArrayList<Line> setupPath() {
        ArrayList<Line> path = new ArrayList<>();
        for (int i = 0; i < waypoints.length - 1; i++) {
            path.add(newLine(i));
        }
        return path;
    }

    private static Line newLine(int index) {
        Line result = new Line(0,0,0,0, LINE_COLOR, LINE_WIDTH);
        result.setStartPoint(waypoints[index]);
        result.setEndPoint(waypoints[index + 1]);
        return result;
    }

    public static ChristmasPresent createRandomPresent(float speed, int delayCounter, ChristmasPresentListener listener) {
        return new ChristmasPresent(speed, delayCounter, listener);
    }

    public static ChristmasPresent[] fillCurrentWave(int waveLength, float speed, int delayCounter, ChristmasChallenge listener) {
        ChristmasPresent[] wave = new ChristmasPresent[waveLength];
        for (int i = 0; i < waveLength; i++) {
            wave[i] = createRandomPresent(speed, i * delayCounter, listener);
        }
        return wave;
    }

    public static ArrayList getNextWave(ChristmasPresentListener listener) {
        ArrayList<ChristmasPresent> result = new ArrayList<>();
        for (int i = 0; i < waves[waveCounter].length; i++) {
            //createRandomPresent((Float) waves[waveCounter][i][0], listener);
        }
        waveCounter++;
        return null;
    }
}
