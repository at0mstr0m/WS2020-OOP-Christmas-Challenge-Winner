import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Line;

public class Turret implements GameConfig{
    private Circle body;
    private Line ray;
    private int fireCounter;              //helps adjusting the firerate
    private int fireCooldown;             //how long turret can fire without

    public Turret(int xPos, int yPos) {
        this.body = new Circle(xPos,yPos,20, ARYLIDE_YELLOW);
        this.fireCounter = 0;
        this.fireCooldown = 60;
    }

    public void draw() {
        this.body.draw();
        if (ChristmasChallenge.getCurrentWave() != null) {
            fireCounter++;
            ChristmasPresent closestPresent = ChristmasChallenge.getCurrentWave()[getIndexOfClosestPresent()];
            if (closestPresent != null && fireCounter < fireCooldown + 1) {
                this.ray = new Line(this.body.getXPos(), this.body.getYPos(), closestPresent.body.getXPos(), closestPresent.body.getYPos(), LINEN, 5);
                this.ray.draw();
            }
            if (fireCounter == fireCooldown * 2) fireCounter = 0;
        }
    }

    private int getIndexOfClosestPresent() {
        int length = ChristmasChallenge.getCurrentWave().length;
        int indexOfClosest = 0;
        double shortestDistance = 100000;
        for (int i = 0; i < length; i++) {
            ChristmasPresent currentPresent = ChristmasChallenge.getCurrentWave()[i];
            if (currentPresent != null) {
                if (this.body.distanceTo(currentPresent.body) < shortestDistance) {
                    shortestDistance = this.body.distanceTo(currentPresent.body);
                    indexOfClosest = i;
                }
            }
        }
        return indexOfClosest;
    }
}
