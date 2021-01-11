import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Point;

public abstract class Turret implements GameConfig {
    private final Image foot;
    private final String footAsset;
    private final int type;
    private final int worth;
    private final float xPos;
    private final float yPos;
    protected BoardListener boardListener;
    protected Point turretCenter;
    protected int fireCounter;                // helps adjusting the firerate
    protected int fireCooldown;               // helps fire burst iteration
    protected double dmgPerTick;              // damage dealt by turret in one draw() cycle
    protected double fireRange;
    private Image body;
    private int level;

    public Turret(float xPos, float yPos, int type, BoardListener boardListener) {
        this.boardListener = boardListener;
        this.xPos = xPos;
        this.yPos = yPos;
        this.type = type;
        this.level = 0;
        this.body = new Image(this.xPos, this.yPos, SantasStaticHelper.turretAssets[this.type][this.level]);
        this.footAsset = System.getProperty("user.dir") + PATH_TO_ASSETS_TURRETS + "turret_foot.png";
        this.foot = new Image(this.xPos, this.yPos, footAsset);
        this.turretCenter = new Point(xPos + 32, yPos + 32);
        this.worth = turretBuildingPrices[this.type][this.level];
    }

    public void draw() {
        body.draw();
    }

    public void drawFoot() {
        foot.draw();
    }

    protected void countShots() {
        this.fireCounter++;
        if (this.fireCounter == this.fireCooldown * 2) this.fireCounter = 0;
    }

    /**
     * rotates Turret towards its target
     *
     * @param rayStartX
     * @param rayStartY
     * @param rayEndX
     * @param rayEndY
     */
    protected void adjustTurretRotation(float rayStartX, float rayStartY, float rayEndX, float rayEndY) {
        double angle = Math.atan2(rayEndY - rayStartY, rayEndX - rayStartX);
        angle = TURRET_ROTATION_OFFSET + (angle * (360 / (2 * Math.PI)));       // convert angle unit from Rad to Degrees
        if (angle < 0) angle += 360;
        this.body.setRotationAngle(angle);
    }

    /**
     * returns instance of first ChristmasPresent of the current wave
     *
     * @return
     */
    public ChristmasPresent getFirstPresentOfWave() {
        for (int i = 0; i < boardListener.getCurrentWave().size(); i++) {
            ChristmasPresent currentPresent = boardListener.getCurrentWave().get(i);
            if (currentPresent != null && this.body.distanceTo(currentPresent.getBody()) <= this.fireRange) {
                if (currentPresent.getBody().getYPos() >= WINDOW_HEIGHT) return null;   // currentPresent must be visible to be aimed at
                return currentPresent;
            }
        }
        return null;
    }

    /**
     * necessary to open TurretContextMenu
     *
     * @param x
     * @param y
     * @return
     */
    public boolean hitTest(int x, int y) {
        return this.body.hitTest(x, y);
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
        if (boardListener.getMoney() >= turretBuildingPrices[this.type][this.level + 1]) {
            this.level++;
            this.dmgPerTick *= 1.5;
            this.fireRange += 10;
            boardListener.spendMoney(turretBuildingPrices[type][level]);
            this.body = new Image(xPos, yPos, SantasStaticHelper.turretAssets[type][level]);
            return true;
        } else return false;
    }

    public int getType() {
        return this.type;
    }

    public int getLevel() {
        return this.level;
    }

    protected double getRotationAngle() {
        return this.body.getRotationAngle();
    }

    public void resetFireCounter() {
        this.fireCounter = 0;
    }
}