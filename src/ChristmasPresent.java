import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Point;

public class ChristmasPresent implements GameConfig {
    private final String[] christmasPresentAssets = {
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_1.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_2.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "candy_cane.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_hat.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_tree.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "drum.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "mistletoe.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "snowflake.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "star.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "trumpet.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "candy_cane_big.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_hat_big.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "christmas_tree_big.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "drum_big.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "mistletoe_big.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "snowflake_big.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "star_big.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "trumpet_big.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_1_big.png",
            System.getProperty("user.dir") + PATH_TO_ASSET_CHRISTMAS_PRESENTS + "gift_2_big.png",
    };
    private final Image body;
    private int waypointIterator;
    private final boolean destroyed;
    private final float speed;
    private int delayCounter;
    private final ChristmasPresentListener listener;
    private final Point[] waypoints;
    private double lifepoints;
    private final int worth;

    public ChristmasPresent(float speed, int delayCounter, int type, ChristmasPresentListener listener) {
        this.listener = listener;
        this.waypointIterator = 0;
        this.delayCounter = delayCounter;
        this.destroyed = false;
        this.speed = speed;
        this.body = new Image(0, 0, christmasPresentAssets[type]);     //initialized with pseudo x & y
        this.waypoints = SantasStaticHelper.getPresentWaypoints(this.body.getWidth(), this.body.getHeight());
        this.body.setPosition(this.waypoints[0].getXPos(), this.waypoints[0].getYPos());
        this.lifepoints = resolveLifepoints(type);
        this.worth = 6;
    }

    private double resolveLifepoints(int type) {
        return 80 * Math.pow(type + 1, 1 / 1.8);
        //return (80 * (Math.sqrt(type) + 1));
    }

    /**
     * Moves ChristmasPresent along the path
     */
    private void moveAlongPath() {
        if (waypointIterator == waypoints.length) { // final waypoint is reached
            this.body.move(0, -this.speed);     // move on beyond Window
            if (this.body.getBottomBorder() <= -this.body.getHeight()) listener.onPresentReachedEndOfPath(this);    // if completely disappeared from canvas, remove present from array
        } else if (body.getXPos() == waypoints[waypointIterator].getXPos() && body.getYPos() == waypoints[waypointIterator].getYPos()) {       // if a waypoint is reached, head to next waypoint
            this.waypointIterator++;
        } else {
            Point newPosition = moveTowards(this.body.getXPos(), this.body.getYPos(), waypoints[waypointIterator].getXPos(), waypoints[waypointIterator].getYPos(), this.speed);
            this.body.setXPos(newPosition.getXPos());
            this.body.setYPos(newPosition.getYPos());
        }
    }

    public void draw() {
        if (this.body.getBottomBorder() <= 0) listener.onPresentReachedEndOfPath(this);     // remove Instance if end of Path is reached
        if (delayCounter > 0) delayCounter--;         // count down ticks until launch
        else {
            moveAlongPath();
            this.body.draw();
        }
    }

    /**
     * Moves ChristmasPresent to waypoint on path
     */
    private Point moveTowards(float sourceX, float sourceY, float targetX, float targetY, float speed) {
        if (Math.abs(targetX - sourceX) <= speed && Math.abs(targetY - sourceY) <= speed) return new Point(targetX, targetY);
        float angle = (float) Math.atan2(targetY - sourceY, targetX - sourceX);
        float xMovement = sourceX + (float) (Math.cos(angle) * speed);
        float yMovement = sourceY + (float) (Math.sin(angle) * speed);
        return new Point(xMovement, yMovement);
    }

    public Point getCenterPoint() {
        return new Point(body.getXPos() + (body.getWidth() / 2), body.getYPos() + (body.getHeight() / 2));
    }

    public Image getBody() {
        return body;
    }

    /**
     * Handles Damage caused on ChristmasPresent
     *
     * @param dmgPerTick
     */
    public void takeDamage(double dmgPerTick) {
        this.lifepoints -= dmgPerTick;                                      // subtract damage dealt from this.lifepoints
        if (this.lifepoints <= 0) this.listener.onPresentDestroyed(this);   // if lifepoints are down, the present is destroyed and must be removed
    }

    public int getWorth() {
        return worth;
    }

    public boolean hitTest(float x, float y) {
        return this.body.hitTest(x, y);
    }
}
