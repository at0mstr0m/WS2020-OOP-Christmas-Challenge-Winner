import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.graphics.Rectangle;

public class ChristmasPresent implements GameConfig {
    public Rectangle body;
    private int waypointIterator;
    private boolean destroyed;
    private float speed;
    private int delayCounter;
    private ChristmasPresentListener listener;

    public ChristmasPresent(float speed, int delayCounter, ChristmasPresentListener listener) {
        this.listener = listener;
        this.waypointIterator = 0;
        this.delayCounter = delayCounter;
        this.destroyed = false;
        this.speed = speed;
        body = new Rectangle(0, 0, 20, 20, AMERICAN_PINK);
        body.setPosition(waypoints[0].getXPos(), waypoints[0].getYPos());
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
}
