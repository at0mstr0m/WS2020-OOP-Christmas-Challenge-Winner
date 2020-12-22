import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Line;

import java.util.ArrayList;

import static config.GameConfig.*;

public class Initializer {
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
}
