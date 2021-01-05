import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Line;

public class TurretOne extends Turret {
    public TurretOne(float xPos, float yPos, ChristmasChallenge mainClassListener) {
        super(xPos, yPos, 1, mainClassListener);
        this.fireCounter = 0;
        this.fireCooldown = TURRET_ONE_COOLDOWN;
        this.dmgPerTick = TURRET_ONE_DMG;
        this.fireRange = 500;
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
        ChristmasPresent closestPresent = getFirstPresentOfWave();
        if (closestPresent != null && this.fireCounter < this.fireCooldown + 1 && this.body.distanceTo(closestPresent.getBody()) <= this.fireRange) {
            float rayStartX = this.turretCenter.getXPos();
            float rayStartY = this.turretCenter.getYPos();
            float rayEndX = closestPresent.getCenterPoint().getXPos();
            float rayEndY = closestPresent.getCenterPoint().getYPos();
            Line ray = new Line(rayStartX, rayStartY, rayEndX, rayEndY, FIRE_OPAL, 16);
            Line seperatorRay = new Line(rayStartX, rayStartY, rayEndX, rayEndY, Colors.WHITE, 6);
            ray.draw();
            seperatorRay.draw();
            adjustTurretRotation(rayStartX, rayStartY, rayEndX, rayEndY);
            closestPresent.takeDamage(this.dmgPerTick);
        }
    }
}
