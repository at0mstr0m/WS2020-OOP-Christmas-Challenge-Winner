import de.ur.mi.oop.graphics.Line;

import java.util.ArrayList;

public class SantasLittleHelper implements GameConfig, WaveContent {
    private static int waveCounter = 0;

    public static String getWorkingDirectory() {
        return System.getProperty("user.dir");
    }

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
}
