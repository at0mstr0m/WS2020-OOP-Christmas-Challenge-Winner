import java.util.ArrayList;
import java.util.Iterator;

public class TurretTwo extends Turret {
    ArrayList<Rocket> rockets = new ArrayList();
    public TurretTwo(float xPos, float yPos, ChristmasChallenge mainClassListener) {
        super(xPos, yPos, 2, mainClassListener);
        this.fireCounter = 0;
        this.fireCooldown = 60;
        this.dmgPerTick = 20;
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
        super.draw();
    }

    private void fire() {
        this.countShots();
        ChristmasPresent closestPresent = getClosestPresent(this.fireRange);
        if (closestPresent != null && canFire()) {
            this.rockets.add(new Rocket(this.getTurretCenter(), this.getRotationAngle(), closestPresent, this));
            adjustTurretRotation(this.turretCenter.getXPos(), this.turretCenter.getYPos(), closestPresent.getCenterPoint().getXPos(), closestPresent.getCenterPoint().getYPos());
        }
    }

    private boolean canFire() {
        return fireCounter == 0;
    }

    @Override
    protected void countShots() {
        this.fireCounter++;
        if (this.fireCounter == this.fireCooldown) this.fireCounter = 0;
    }
}
