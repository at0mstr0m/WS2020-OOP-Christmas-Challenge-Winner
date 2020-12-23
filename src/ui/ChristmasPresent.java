package ui;

import config.GameConfig;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.graphics.Rectangle;

public class ChristmasPresent implements GameConfig {
    public Rectangle body;
    private int pathPart;
    private boolean destroyed;
    private boolean visible;
    private ChristmasPresentListener listener;

    public ChristmasPresent(ChristmasPresentListener listener) {
        this.listener = listener;
        this.pathPart = 0;
        this.destroyed = false;
        this.visible = false;
        body = new Rectangle(0, 0, 20, 20, AMERICAN_PINK);
        body.setPosition(waypoints[0].getXPos(), waypoints[0].getYPos());
    }

    private void moveAlongPath() {
        if (body.getXPos() == waypoints[pathPart].getXPos() && body.getYPos() == waypoints[pathPart].getYPos()) {
            this.pathPart++;
        }
        Point newPosition = moveTowards(this.body.getXPos(), this.body.getYPos(), waypoints[pathPart].getXPos(), waypoints[pathPart].getYPos(), 5);
        this.body.setXPos(newPosition.getXPos());
        this.body.setYPos(newPosition.getYPos());
    }

    public void draw() {
        if (this.body.getBottomBorder() <= 0) listener.onPresentReachedEnd(this);
        moveAlongPath();
        body.draw();
    }

    private Point moveTowards(float sourceX, float sourceY, float targetX, float targetY, float speed) {
        if (Math.abs(targetX - sourceX) <= speed && Math.abs(targetY - sourceY) <= speed) return new Point(targetX, targetY);
        float angle = (float) Math.atan2(targetY - sourceY, targetX - sourceX);         // Berechnet den Winkel zwischen den beiden Punkten im Bogenmaß
        float xMovement = sourceX + (float) (Math.cos(angle) * speed);                  // Berechnet den "Bewegungsvektor" auf Basis des berechneten Winkels und der aktuellen Geschwindigkeit
        float yMovement = sourceY + (float) (Math.sin(angle) * speed);                  // Berechnet den "Bewegungsvektor" auf Basis des berechneten Winkels und der aktuellen Geschwindigkeit
        return new Point(xMovement, yMovement);                                         // Gibt Zielpunkt zurück
    }
}
