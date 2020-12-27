import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.graphics.Rectangle;

public class ChristmasPresent implements GameConfig {
    //private Rectangle body;
    private Image body;
    private int waypointIterator;
    private boolean destroyed;
    private float speed;
    private int delayCounter;
    private ChristmasPresentListener listener;
    private Point[] waypoints;
    private double lifepoints;
    private int type;
    private int worth;

    public ChristmasPresent(float speed, int delayCounter, int type, ChristmasPresentListener listener) {
        this.type = type;
        this.listener = listener;
        this.waypointIterator = 0;
        this.delayCounter = delayCounter;
        this.destroyed = false;
        this.speed = speed;
        this.body = new Image(0,0,SantasLittleHelper.christmasPresentAssets[type]);     //initialized with pseudo x & y
        this.waypoints = SantasLittleHelper.getPresentWaypoints(this.body.getWidth(), this.body.getHeight());
        this.body.setPosition(this.waypoints[0].getXPos(), this.waypoints[0].getYPos());
        this.lifepoints = 100 * type + 1;
        this.worth = (int) (this.lifepoints / 10);
    }

    private void moveAlongPath() {
        if (waypointIterator == waypoints.length) { //final waypoint is reached
            this.body.move(0, -this.speed);     //move on beyond Window
            if (this.body.getBottomBorder() <= -this.body.getHeight()) listener.onPresentReachedEndOfPath(this);    //if completely disappeared from canvas, remove present from array
        } else if (body.getXPos() == waypoints[waypointIterator].getXPos() && body.getYPos() == waypoints[waypointIterator].getYPos()) {       //if a waypoint is reached, head to next waypoint
            this.waypointIterator++;
        } else {
            Point newPosition = moveTowards(this.body.getXPos(), this.body.getYPos(), waypoints[waypointIterator].getXPos(), waypoints[waypointIterator].getYPos(), this.speed);
            this.body.setXPos(newPosition.getXPos());
            this.body.setYPos(newPosition.getYPos());
        }
    }

    public void draw() {
        if (this.body.getBottomBorder() <= 0) listener.onPresentReachedEndOfPath(this);     //terminate Instance if end of Path is reached
        if (delayCounter > 0) {
            delayCounter--;         //count down ticks until launch
        } else {
            moveAlongPath();
            this.body.draw();
        }
    }

    private Point moveTowards(float sourceX, float sourceY, float targetX, float targetY, float speed) {
        if (Math.abs(targetX - sourceX) <= speed && Math.abs(targetY - sourceY) <= speed) return new Point(targetX, targetY);
        float angle = (float) Math.atan2(targetY - sourceY, targetX - sourceX);         // Berechnet den Winkel zwischen den beiden Punkten im Bogenmaß
        float xMovement = sourceX + (float) (Math.cos(angle) * speed);                  // Berechnet den "Bewegungsvektor" auf Basis des berechneten Winkels und der aktuellen Geschwindigkeit
        float yMovement = sourceY + (float) (Math.sin(angle) * speed);                  // Berechnet den "Bewegungsvektor" auf Basis des berechneten Winkels und der aktuellen Geschwindigkeit
        return new Point(xMovement, yMovement);                                         // Gibt Schritt zurück
    }

    public Point getCenterPoint() {
        return new Point(body.getXPos() + (body.getWidth() / 2), body.getYPos() + (body.getHeight() / 2));
    }

    public Image getBody() {
        return body;
    }

    public void takeDamage(double dmgPerTick) {
        this.lifepoints -= dmgPerTick;                                      // subtract damage dealt from this.lifepoints
        System.out.println(this.lifepoints);
        if (this.lifepoints <= 0) this.listener.onPresentDestroyed(this);   // if lifepoints are down, the present is destroyed and must be removed
    }

    public int getWorth() {
        return worth;
    }
}
