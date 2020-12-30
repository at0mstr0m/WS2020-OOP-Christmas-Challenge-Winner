import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Point;

public abstract class Turret implements GameConfig{
    protected ChristmasChallenge mainClassListener;
    protected Point turretCenter;
    private Image body;
    private int type;
    private int level;
    private int worth;
    private float xPos;
    private float yPos;
    protected int fireCounter;                //helps adjusting the firerate
    protected int fireCooldown;               //how long turret can fire without
    protected double dmgPerTick;              //damage dealt by turret in one draw() cycle
    protected double fireRange;

    public Turret(float xPos, float yPos, int type, ChristmasChallenge mainClassListener) {
        this.mainClassListener = mainClassListener;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = type;
        this.level = 0;
        this.body = new Image(this.xPos, this.yPos, SantasLittleHelper.turretAssets[this.type][this.level]);
        this.turretCenter = new Point(xPos + 32, yPos + 32);
        this.worth = turretBuildingPrices[this.type][this.level];
    }

    public void draw() {
        this.body.draw();
    }

    protected void countShots() {
        this.fireCounter++;
        if (this.fireCounter == this.fireCooldown * 2) this.fireCounter = 0;
    }

    protected void adjustTurretRotation(float rayStartX, float rayStartY, float rayEndX, float rayEndY) {
        double angle = Math.atan2(rayEndY - rayStartY, rayEndX - rayStartX);    // Berechnet den Winkel zwischen den beiden Punkten im Bogenmaß
        angle = TURRET_ROTATION_OFFSET + (angle * (360 / (2 * Math.PI)));       // convert angle unit from Rad to Degrees
        if (angle < 0) angle += 360;                                            // if angle becomes negative, ad 360° to reach the correct values
        this.body.setRotationAngle(angle);
    }

    protected int getIndexOfClosestPresent(double fireRange) {
        int length = mainClassListener.getCurrentWave().length;
        System.out.println("length = " + length);
        int indexOfClosest = 0;
        double shortestDistanceFound = 100000;
        for (int i = 0; i < length; i++) {
            ChristmasPresent currentPresent = mainClassListener.getCurrentWave()[i];
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

    protected ChristmasPresent getClosestPresent(double fireRange) {
        ChristmasPresent closestPresent = null;
        int length = mainClassListener.getCurrentWave().length;
        int indexOfClosest = 0;
        double shortestDistanceFound = fireRange;
        for (int i = 0; i < length; i++) {
            ChristmasPresent currentPresent = mainClassListener.getCurrentWave()[i];
            if (currentPresent != null) {
                double distanceToCurrentPresent = this.body.distanceTo(currentPresent.getBody());
                if (distanceToCurrentPresent < shortestDistanceFound) {
                    shortestDistanceFound = distanceToCurrentPresent;
                    indexOfClosest = i;
                }
            }
        }
        if (this.body.distanceTo(mainClassListener.getCurrentWave()[indexOfClosest].getBody()) > fireRange) return null;
        else return mainClassListener.getCurrentWave()[indexOfClosest];
    }

    protected ChristmasPresent getFirstPresentOfWave() {
        int length = mainClassListener.getCurrentWave().length;
        for (int i = 0; i < length; i++) {
            ChristmasPresent currentPresent = mainClassListener.getCurrentWave()[i];
            if (currentPresent != null) return currentPresent;
        }
        return null;
    }

    public boolean hitTest(int x, int y) {
        return this.body.hitTest(x,y);
    }

    public Point getTurretCenter() {
        return this.turretCenter;
    }

    public int getWorth() {
        return this.worth;
    }

    /**
     * only updates that can be afforded can be performed
     */
    public boolean levelUp() {
        if (mainClassListener.getMoney() >= turretBuildingPrices[this.type][this.level + 1]) {
            this.level++;
            this.dmgPerTick *= 1.5;
            mainClassListener.spendMoney(turretBuildingPrices[type][level]);
            this.body = new Image(xPos, yPos, SantasLittleHelper.turretAssets[type][level]);
            return true;
        } else return false;
    }

    public int getType() {
        return this.type;
    }

    public int getLevel() {
        return this.level;
    }
}
