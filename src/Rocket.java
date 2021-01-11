import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Point;

public class Rocket implements GameConfig {
    private final Image body;
    private final String rocketAsset = System.getProperty("user.dir") + PATH_TO_ASSET_ROCKET + "rocket.png";
    private float xPos;
    private float yPos;
    private final float speed = 4f;
    private final RocketListener listener;
    private ChristmasPresent target;

    public Rocket(Point turretCenter, double rotationAngle, RocketListener listener) {
        this.listener = listener;
        this.xPos = turretCenter.getXPos() - ROCKET_X_OFFSET;
        this.yPos = turretCenter.getYPos() - ROCKET_Y_OFFSET;
        this.body = new Image(this.xPos, this.yPos, rocketAsset);
        this.body.setRotationAngle(rotationAngle);
        this.target = listener.getFirstPresentOfWave();
    }

    public void draw() {
        this.target = listener.getFirstPresentOfWave(); // always aims at first ChristmasPresent of the current wave
        if (target != null) {                           // without a target this Rocket will become obsolete and is removed by TurretTwo
            moveTowardsTarget();
            this.body.draw();
        }
    }

    /**
     * Moves Rocket towards its Target.
     */
    private void moveTowardsTarget() {
        if (Math.abs(target.getCenterPoint().getXPos() - ROCKET_X_OFFSET - body.getXPos()) <= speed && Math.abs(target.getCenterPoint().getYPos() + ROCKET_Y_OFFSET - body.getYPos()) <= speed) body.setPosition(target.getCenterPoint().getXPos(), target.getCenterPoint().getYPos());
        else {
            double angle = Math.atan2(target.getCenterPoint().getYPos() - ROCKET_Y_OFFSET - body.getYPos(), target.getCenterPoint().getXPos() - ROCKET_X_OFFSET - body.getXPos());
            float xMovement = (float) (Math.cos(angle) * speed);
            float yMovement = (float) (Math.sin(angle) * speed);
            this.xPos += xMovement;
            this.yPos += yMovement;
            this.body.move(xMovement, yMovement);   // move Rocket closer to target
            if (angle < 0) angle += 360;            // necessary to setRotationAngle correctly
            this.body.setRotationAngle(TURRET_ROTATION_OFFSET + (angle * (360 / (2 * Math.PI))));   // convert angle unit from rad to degrees
        }
    }

    public boolean hasReachedTarget() {
        return this.target.hitTest(this.body.getXPos(), this.body.getYPos());   // use hitTest on ChristmasPresent
    }

    public ChristmasPresent getTarget() {
        return target;
    }
}
