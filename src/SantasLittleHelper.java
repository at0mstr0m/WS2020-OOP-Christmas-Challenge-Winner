import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.graphics.Point;

import java.util.ArrayList;

import static config.GameConfig.*;

public class SantasLittleHelper {

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

    public Point moveTowards(Point start, Point end, float speed) {
        if (start.getXPos() < end.getXPos()) {
            if (start.getYPos() < end.getYPos()) {
                start.setLocation(start.getXPos() + speed, start.getYPos() + speed);
                return start;
            } else if (start.getYPos() > end.getYPos()) {
                start.setLocation(start.getXPos() + speed, start.getYPos() - speed);
                return start;
            } else if (start.getYPos() == end.getYPos()) {
                start.setLocation(start.getXPos() + speed, start.getYPos());
                return start;
            }
        } else if (start.getXPos() > end.getXPos()) {
            if (start.getYPos() < end.getYPos()) {
                start.setLocation(start.getXPos() - speed, start.getYPos() + speed);
                return start;
            } else if (start.getYPos() > end.getYPos()) {
                start.setLocation(start.getXPos() - speed, start.getYPos() - speed);
                return start;
            } else if (start.getYPos() == end.getYPos()) {
                start.setLocation(start.getXPos() - speed, start.getYPos());
                return start;
            }
        } else if (start.getXPos() == end.getXPos()) {
            if (start.getYPos() < end.getYPos()) {
                start.setLocation(start.getXPos(), start.getYPos() + speed);
                return start;
            } else if (start.getYPos() > end.getYPos()) {
                start.setLocation(start.getXPos(), start.getYPos() - speed);
                return start;
            } else if (start.getYPos() == end.getYPos()) {
                return start;
            }
        }
        return start;
    }
}
