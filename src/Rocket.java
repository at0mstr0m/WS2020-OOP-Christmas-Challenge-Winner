import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Point;

public class Rocket implements GameConfig{
    Image body;
    String rocketAsset = SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSET_ROCKET + "rocket.png";
    float xPos;
    float yPos;
    float speed = 10;
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
        adjustRocketRotation(this.xPos, this.yPos, target.getCenterPoint().getXPos(), target.getCenterPoint().getYPos());
        this.body.draw();
    }

    private void moveTowardsTarget() {
        if (Math.abs(target.getCenterPoint().getXPos() - body.getXPos()) <= speed && Math.abs(target.getCenterPoint().getYPos() - body.getYPos()) <= speed) body.setPosition(target.getCenterPoint().getXPos(), target.getCenterPoint().getYPos());
        else {
            double angle = Math.atan2(target.getCenterPoint().getYPos() - body.getYPos(), target.getCenterPoint().getXPos() - body.getXPos());
            float xMovement = (float) (Math.cos(angle) * speed);
            float yMovement = (float) (Math.sin(angle) * speed);
            this.xPos += xMovement;
            this.yPos += yMovement;
            this.body.move(xMovement, yMovement);                   // move Rocket
        }
    }

    public boolean hasReachedTarget() {
        return this.target.hitTest(this.body.getXPos(), this.body.getYPos());   // use hitTest on ChristmasPresent
    }

    private void adjustRocketRotation(float rayStartX, float rayStartY, float rayEndX, float rayEndY) {
        double angle = Math.atan2(rayEndY - rayStartY, rayEndX - rayStartX);    // Berechnet den Winkel zwischen den beiden Punkten im Bogenmaß
        angle = TURRET_ROTATION_OFFSET + (angle * (360 / (2 * Math.PI)));       // convert angle unit from Rad to Degrees
        this.body.setRotationAngle(angle);
    }

    public ChristmasPresent getTarget() {
        return target;
    }
}
