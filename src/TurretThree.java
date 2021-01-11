import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Line;

public class TurretThree extends Turret {
    private ChristmasPresent firstPresentOfWave;

    public TurretThree(float xPos, float yPos, BoardListener boardListener) {
        super(xPos, yPos, 3, boardListener);
        this.fireCounter = 0;
        this.fireCooldown = TURRET_THREE_COOLDOWN;
        this.dmgPerTick = TURRET_THREE_DMG;
        this.fireRange = 10000000;      // irrelevant because sniper can fire at every point
    }

    @Override
    public void draw() {
        // if there is a wave attacking, fire
        if (boardListener.currentWaveIsAttacking()) fire();     // will fire at ChristmasPresent if not cooling down
        super.draw();
    }

    private void fire() {
        this.countShots();
        if (fireCounter == 0) this.firstPresentOfWave = getFirstPresentOfWave();    // only aim at one present per fire burst
        if (firstPresentOfWave != null && canFire() && boardListener.getCurrentWave().contains(firstPresentOfWave)) {
            float rayStartX = this.turretCenter.getXPos();
            float rayStartY = this.turretCenter.getYPos();
            float rayEndX = firstPresentOfWave.getCenterPoint().getXPos();
            float rayEndY = firstPresentOfWave.getCenterPoint().getYPos();
            Line ray = new Line(rayStartX, rayStartY, rayEndX, rayEndY, Colors.BLACK, 3);
            ray.draw();
            adjustTurretRotation(rayStartX, rayStartY, rayEndX, rayEndY);
            firstPresentOfWave.takeDamage(this.dmgPerTick);
        }
    }


    private boolean canFire() {
        /*
         * Only shoot one burst of shots that are visible to the player.
         * Not very elegant but effective.
         */
        return fireCounter == 0 || fireCounter == 1 || fireCounter == 2 || fireCounter == 3 || fireCounter == 4 || fireCounter == 5;
    }

    @Override
    protected void countShots() {
        this.fireCounter++;
        if (this.fireCounter == this.fireCooldown) this.fireCounter = 0;
    }

    @Override
    public void resetFireCounter() {
        super.resetFireCounter();
        this.firstPresentOfWave = null;
    }
}
