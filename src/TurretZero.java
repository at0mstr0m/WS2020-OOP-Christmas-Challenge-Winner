import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Line;

public class TurretZero extends Turret {


    public TurretZero(float xPos, float yPos, ChristmasDefense mainClassListener) {
        super(xPos, yPos, 0, mainClassListener);
        this.fireCounter = 0;
        this.fireCooldown = TURRET_ZERO_COOLDOWN;
        this.dmgPerTick = TURRET_ZERO_DMG;
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
        ChristmasPresent presentAimedAt = getFirstPresentOfWave();
        if (presentAimedAt == null) presentAimedAt = getClosestPresent();
        if (presentAimedAt != null && this.fireCounter < this.fireCooldown + 1 && this.body.distanceTo(presentAimedAt.getBody()) <= this.fireRange) {
            float rayStartX = this.turretCenter.getXPos();
            float rayStartY = this.turretCenter.getYPos();
            float rayEndX = presentAimedAt.getCenterPoint().getXPos();
            float rayEndY = presentAimedAt.getCenterPoint().getYPos();
            Line ray = new Line(rayStartX, rayStartY, rayEndX, rayEndY, Colors.getRandomColor(), 5);
            ray.draw();
            adjustTurretRotation(rayStartX, rayStartY, rayEndX, rayEndY);
            presentAimedAt.takeDamage(this.dmgPerTick);
        }
    }
}
