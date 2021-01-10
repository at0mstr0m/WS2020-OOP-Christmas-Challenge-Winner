import java.util.ArrayList;

public class TurretTwo extends Turret implements RocketListener {
    ArrayList<Rocket> rockets;

    public TurretTwo(float xPos, float yPos, BoardListener boardListener) {
        super(xPos, yPos, 2, boardListener);
        this.rockets = new ArrayList();
        this.fireCounter = 0;
        this.fireCooldown = TURRET_TWO_COOLDOWN;
        this.dmgPerTick = TURRET_TWO_DMG;
        this.fireRange = 1000000;
    }

    @Override
    public void draw() {
        if (boardListener.currentWaveIsAttacking()) {   // if there is a wave attacking, fire
            fire();                                     // will fire at ChristmasPresent if not cooling down
        }
        if (boardListener.currentWaveIsAttacking()) {
            for (int i = 0; i < this.rockets.size(); i++) {
                if (rockets.get(i).getTarget() == null) {       // make Rockets disappear if no ChristmasPresent visible on the path
                    rockets.remove(i);
                }
                else if (rockets.get(i).hasReachedTarget()) {               // check if a rocket has reached its target
                    if (boardListener.getCurrentWave().contains(rockets.get(i).getTarget())) {  // avoid that an already destroyed ChristmasPresent is damaged again which would give the player more money than legal
                        rockets.get(i).getTarget().takeDamage(this.dmgPerTick);                 // cause damage to target ChristmasPresent
                    }
                    if (!boardListener.currentWaveIsAttacking()) break;     // avoids IndexOutOfBoundsException when the last ChristmasPresent is destroyed
                    rockets.remove(i);
                }
                this.rockets.trimToSize();
                if (this.rockets.size() == 0) break;
            }

            for (Rocket rocket : rockets) {                     // draw rockets
                rocket.draw();
            }
        }
        super.draw();
    }

    private void fire() {
        this.countShots();
        ChristmasPresent firstPresentOfWave = getFirstPresentOfWave();
        if (firstPresentOfWave != null && canFire()) {
            this.rockets.add(new Rocket(this.getTurretCenter(), this.getRotationAngle(), this));
            adjustTurretRotation(this.turretCenter.getXPos(), this.turretCenter.getYPos(), firstPresentOfWave.getCenterPoint().getXPos(), firstPresentOfWave.getCenterPoint().getYPos());
        }
    }

    private boolean canFire() {
        return fireCounter == 0;    // only shoot once after fire cooldown
    }

    @Override
    protected void countShots() {
        this.fireCounter++;
        if (this.fireCounter == this.fireCooldown) this.fireCounter = 0;
    }

    @Override
    public void resetFireCounter() {
        super.resetFireCounter();
        this.rockets = new ArrayList();
    }
}
