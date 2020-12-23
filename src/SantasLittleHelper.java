import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.graphics.Point;
import ui.ChristmasPresent;
import ui.ChristmasPresentListener;

import java.util.ArrayList;

import static config.GameConfig.*;

public final class SantasLittleHelper {
    public static ArrayList setupPath() {
        ArrayList path = new ArrayList();
        for (int i = 0; i < waypoints.length - 1; i++) {
            path.add(newLine(i));
        }
        return path;
    }

    private static Line newLine(int index) {
        Line result = new Line(0,0,0,0,LINE_COLOR,LINE_WIDTH);
        result.setStartPoint(waypoints[index]);
        result.setEndPoint(waypoints[index + 1]);
        return result;
    }

    public static ChristmasPresent createRandomPresent(ChristmasPresentListener listener) {
        return new ChristmasPresent(listener);
    }

    public static ChristmasPresent[] fillCurrentWave(int waveLength, ChristmasChallenge listener) {
        ChristmasPresent[] wave = new ChristmasPresent[waveLength];
        for (int i = 0; i < waveLength; i++) {
            wave[i] = createRandomPresent(listener);
        }
        return wave;
    }
}
