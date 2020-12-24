import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Line;
import ui.ChristmasPresent;

import static config.GameConfig.ARYLIDE_YELLOW;
import static config.GameConfig.LINEN;

public class Turret {
    private Circle body;
    private Line ray;

    public Turret() {
        this.body = new Circle(200,500,20, ARYLIDE_YELLOW);
    }

    public void draw() {
        ChristmasPresent closestPresent = ChristmasChallenge.getCurrentWave()[getIndexOfClosestPresent()];
        this.body.draw();
        if (closestPresent != null) {
            this.ray = new Line(this.body.getXPos(), this.body.getYPos(), closestPresent.body.getXPos(), closestPresent.body.getYPos(), LINEN, 5);
            this.ray.draw();
        }
    }

    private int getIndexOfClosestPresent() {
        int length = ChristmasChallenge.getCurrentWave().length;
        int indexOfClosest = 0;
        double shortestDistance = 100000;
        for (int i = 0; i < length; i++) {
            ChristmasPresent currentPresent = ChristmasChallenge.getCurrentWave()[i];
            if (currentPresent != null && currentPresent.isVisible()) {
                if (this.body.distanceTo(currentPresent.body) < shortestDistance) {
                    shortestDistance = this.body.distanceTo(currentPresent.body);
                    indexOfClosest = i;
                }
            }
        }
        return indexOfClosest;
    }
}
