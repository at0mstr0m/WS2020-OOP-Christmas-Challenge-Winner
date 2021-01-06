import java.util.ArrayList;

public class TurretTwo extends Turret {
    ArrayList<Rocket> rockets = new ArrayList();
    public TurretTwo(float xPos, float yPos, ChristmasDefense mainClassListener) {
        super(xPos, yPos, 2, mainClassListener);
        this.fireCounter = 0;
        this.fireCooldown = TURRET_TWO_COOLDOWN;
        this.dmgPerTick = TURRET_TWO_DMG;
        this.fireRange = 1000000;
        System.out.println("size: " + rockets.size());
    }

    @Override
    public void draw() {
        if (mainClassListener.currentWaveIsAttacking()) {      //if there is a wave attacking, fire
            fire();                                             //will fire at ChristmasPresent if not cooling down
        }

        /*
         * Currently haven't found a more elegant solution to avoid a ConcurrentModificationException
         * due to removal of Rockets during Iteration than iterating twice...
         */
        if (mainClassListener.getCurrentWave() != null) {       // vanish rockets if currentWave is over
            for (int i = 0; i < this.rockets.size(); i++) {     // check if a rocket has reached its target
                if (rockets.get(i).hasReachedTarget()) {
                    rockets.get(i).getTarget().takeDamage(this.dmgPerTick); // cause damage to hit ChristmasPresent
                    rockets.remove(i);
                }
            }
            this.rockets.trimToSize();

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
            this.rockets.add(new Rocket(this.getTurretCenter(), this.getRotationAngle(), firstPresentOfWave, this));
            adjustTurretRotation(this.turretCenter.getXPos(), this.turretCenter.getYPos(), firstPresentOfWave.getCenterPoint().getXPos(), firstPresentOfWave.getCenterPoint().getYPos());
        }
    }

    private boolean canFire() {
        return fireCounter == 0;    //only shoot once after fire cooldown
    }

    @Override
    protected void countShots() {
        this.fireCounter++;
        if (this.fireCounter == this.fireCooldown) this.fireCounter = 0;
    }
}
