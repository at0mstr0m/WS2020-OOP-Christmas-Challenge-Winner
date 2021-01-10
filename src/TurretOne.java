import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Line;

public class TurretOne extends Turret {
    public TurretOne(float xPos, float yPos, BoardListener boardListener) {
        super(xPos, yPos, 1, boardListener);
        this.fireCounter = 0;
        this.fireCooldown = TURRET_ONE_COOLDOWN;
        this.dmgPerTick = TURRET_ONE_DMG;
        this.fireRange = 300;
    }

    @Override
    public void draw() {
        if (boardListener.currentWaveIsAttacking()) {      //if there is a wave attacking, fire
            fire();                                             //will fire at ChristmasPresent if not cooling down
        }
        super.draw();
    }

    private void fire() {
        this.countShots();
        ChristmasPresent presentAimedAt = getFirstPresentOfWave();
        if (presentAimedAt != null && this.fireCounter < this.fireCooldown + 1) {
            float rayStartX = this.turretCenter.getXPos();
            float rayStartY = this.turretCenter.getYPos();
            float rayEndX = presentAimedAt.getCenterPoint().getXPos();
            float rayEndY = presentAimedAt.getCenterPoint().getYPos();
            Line ray = new Line(rayStartX, rayStartY, rayEndX, rayEndY, FIRE_OPAL, 16);
            Line seperatorRay = new Line(rayStartX, rayStartY, rayEndX, rayEndY, Colors.WHITE, 6);
            ray.draw();
            seperatorRay.draw();
            adjustTurretRotation(rayStartX, rayStartY, rayEndX, rayEndY);
            presentAimedAt.takeDamage(this.dmgPerTick);
        }
    }
}
