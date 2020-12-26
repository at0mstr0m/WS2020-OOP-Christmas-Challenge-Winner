import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.graphics.Point;

public class Turret implements GameConfig{
    private Image body;
    private Line ray;
    private int fireCounter;              //helps adjusting the firerate
    private int fireCooldown;             //how long turret can fire without
    private final String[] turretAssets = {
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_TURRET_BUTTON + "turret_1.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_TURRET_BUTTON + "turret_2.png"
    };
    private Point turretCenter;

    public Turret(float xPos, float yPos, int type) {
        this.body = new Image(xPos,yPos,turretAssets[type]);
        this.turretCenter = new Point(xPos + 32, yPos + 32);
        this.fireCounter = 0;
        this.fireCooldown = 1;
    }

    public void draw() {
        if (ChristmasChallenge.getCurrentWave() != null) {
            fireCounter++;
            ChristmasPresent closestPresent = ChristmasChallenge.getCurrentWave()[getIndexOfClosestPresent()];
            if (closestPresent != null && fireCounter < fireCooldown + 1) {
                float rayStartX = this.turretCenter.getXPos();
                float rayStartY = this.turretCenter.getYPos();
                float rayEndX = closestPresent.getCenterPoint().getXPos();
                float rayEndY = closestPresent.getCenterPoint().getYPos();
                this.ray = new Line(rayStartX, rayStartY, rayEndX, rayEndY, LINEN, 5);
                this.ray.draw();
                adjustRotation(rayStartX, rayStartY, rayEndX, rayEndY);
            }
            if (fireCounter == fireCooldown * 2) fireCounter = 0;
        }
        this.body.draw();
    }

    private void adjustRotation(float rayStartX, float rayStartY, float rayEndX, float rayEndY) {
        double angle = Math.atan2(rayEndY - rayStartY, rayEndX - rayStartX);         // Berechnet den Winkel zwischen den beiden Punkten im Bogenmaß
        angle = angle * (360 / (2 * Math.PI)) + TURRET_ROTATION_OFFSET;
        this.body.setRotationAngle(angle);
    }

    private int getIndexOfClosestPresent() {
        int length = ChristmasChallenge.getCurrentWave().length;
        int indexOfClosest = 0;
        double shortestDistanceFound = 100000;
        for (int i = 0; i < length; i++) {
            ChristmasPresent currentPresent = ChristmasChallenge.getCurrentWave()[i];
            if (currentPresent != null) {
                double distanceToCurrentPresent = this.body.distanceTo(currentPresent.getBody());
                if (distanceToCurrentPresent < shortestDistanceFound) {
                    shortestDistanceFound = distanceToCurrentPresent;
                    indexOfClosest = i;
                }
            }
        }
        return indexOfClosest;
    }
}
