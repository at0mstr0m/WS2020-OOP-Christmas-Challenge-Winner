import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Line;

public class TurretZero extends Turret {


    public TurretZero(float xPos, float yPos, ChristmasChallenge mainClassListener) {
        super(xPos, yPos, 0, mainClassListener);
        this.fireCounter = 0;
        this.fireCooldown = 20;
        this.dmgPerTick = 0.75;
        this.fireRange = 300;
    }

    @Override
    public void draw() {
        if (mainClassListener.currentWaveIsAttacking()) {      //if there is a wave attacking, fire
            fire();                                             //will fire at ChristmasPresent if not cooling down
        }
        super.draw();
    }

    private void fire() {
        this.countShots();
        ChristmasPresent closestPresent = getClosestPresent(this.fireRange);
        if (closestPresent != null && this.fireCounter < this.fireCooldown + 1) {
            float rayStartX = this.turretCenter.getXPos();
            float rayStartY = this.turretCenter.getYPos();
            float rayEndX = closestPresent.getCenterPoint().getXPos();
            float rayEndY = closestPresent.getCenterPoint().getYPos();
            Line ray = new Line(rayStartX, rayStartY, rayEndX, rayEndY, Colors.getRandomColor(), 5);
            ray.draw();
            adjustTurretRotation(rayStartX, rayStartY, rayEndX, rayEndY);
            closestPresent.takeDamage(this.dmgPerTick);
        }
    }
}
