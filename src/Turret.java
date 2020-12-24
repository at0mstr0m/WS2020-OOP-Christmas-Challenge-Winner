import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Line;

public class Turret implements GameConfig{
    private Circle body;
    private Line ray;
    private int firerate;              //helps adjusting the firerate

    public Turret(int xPos, int yPos) {
        this.body = new Circle(xPos,yPos,20, ARYLIDE_YELLOW);
        this.firerate = 0;
    }

    public void draw() {
        firerate++;
        ChristmasPresent closestPresent = ChristmasChallenge.getCurrentWave()[getIndexOfClosestPresent()];
        this.body.draw();
        if (closestPresent != null && firerate < 61) {
            this.ray = new Line(this.body.getXPos(), this.body.getYPos(), closestPresent.body.getXPos(), closestPresent.body.getYPos(), LINEN, 5);
            this.ray.draw();
        }
        if (firerate == 120) firerate = 0;
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
