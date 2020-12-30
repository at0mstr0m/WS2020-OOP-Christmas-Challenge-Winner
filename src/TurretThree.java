import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Line;

public class TurretThree extends Turret {
    public TurretThree(float xPos, float yPos, ChristmasChallenge mainClassListener) {
        super(xPos, yPos, 3, mainClassListener);
        this.fireCounter = 0;
        this.fireCooldown = 20;
        this.dmgPerTick = 0.75;
        this.fireRange = 10000000;      // irrelevant because sniper can fire at every point
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
        ChristmasPresent firstPresentOfWave = getFirstPresentOfWave();
        if (firstPresentOfWave != null && this.fireCounter < this.fireCooldown + 1) {
            float rayStartX = this.turretCenter.getXPos();
            float rayStartY = this.turretCenter.getYPos();
            float rayEndX = firstPresentOfWave.getCenterPoint().getXPos();
            float rayEndY = firstPresentOfWave.getCenterPoint().getYPos();
            Line ray = new Line(rayStartX, rayStartY, rayEndX, rayEndY, Colors.PINK, 3);
            ray.draw();
            adjustTurretRotation(rayStartX, rayStartY, rayEndX, rayEndY);
            firstPresentOfWave.takeDamage(this.dmgPerTick);
        }
    }
}
