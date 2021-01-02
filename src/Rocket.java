import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Point;

public class Rocket implements GameConfig{
    Image body;
    String rocketAsset = SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_ROCKET + "rocket.png";
    float xPos;
    float yPos;
    float speed = 3;
    TurretTwo listener;
    ChristmasPresent target;

    public Rocket(Point turretCenter, double rotationAngle, ChristmasPresent target, TurretTwo listener) {
        this.listener = listener;
        this.xPos = turretCenter.getXPos() - 16;
        this.yPos = turretCenter.getYPos() - 16;
        this.body = new Image(this.xPos, this.yPos, rocketAsset);
        this.body.setRotationAngle(rotationAngle);
        this.target = target;
    }

    public void draw() {
        moveTowardsTarget();
        this.body.draw();
    }

    private void moveTowardsTarget() {
        if (Math.abs(target.getCenterPoint().getXPos() - 16 - body.getXPos()) <= speed && Math.abs(target.getCenterPoint().getYPos() - 16 - body.getYPos()) <= speed) body.setPosition(target.getCenterPoint().getXPos(), target.getCenterPoint().getYPos());
        else {
            double angle = Math.atan2(target.getCenterPoint().getYPos() - 16 - body.getYPos(), target.getCenterPoint().getXPos() - 16 - body.getXPos());
            float xMovement = (float) (Math.cos(angle) * speed);
            float yMovement = (float) (Math.sin(angle) * speed);
            this.xPos += xMovement;
            this.yPos += yMovement;
            this.body.move(xMovement, yMovement);   // move Rocket towards Target
            if (angle < 0) angle += 360;
            this.body.setRotationAngle(TURRET_ROTATION_OFFSET + (angle * (360 / (2 * Math.PI))));   // convert angle unit from Rad to Degrees
        }
    }

    public boolean hasReachedTarget() {
        return this.target.hitTest(this.body.getXPos(), this.body.getYPos());   // use hitTest on ChristmasPresent
    }

    public ChristmasPresent getTarget() {
        return target;
    }
}
