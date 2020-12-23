package ui;

import config.GameConfig;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.graphics.Rectangle;

public class ChristmasPresent implements GameConfig {
    public Rectangle body;
    private int pathPart;

    public ChristmasPresent() {
        this.pathPart = 1;
        body = new Rectangle(0, 0, 20, 20, AMERICAN_PINK);
        body.setPosition(waypoints[0].getXPos(), waypoints[0].getYPos());
    }

    private void moveAlongPath() {
        if (body.getXPos() == waypoints[pathPart].getXPos() && body.getYPos() == waypoints[pathPart].getYPos()) {
            pathPart++;
        }
        Point newPosition = moveTowards(body.getXPos(), body.getYPos(), waypoints[pathPart].getXPos(), waypoints[pathPart].getYPos(), 1);
        this.body.setXPos(newPosition.getXPos());
        this.body.setYPos(newPosition.getYPos());
    }

    public void draw() {
        moveAlongPath();
        body.draw();
    }

    public Point moveTowards(float startX, float startY, float endX, float endY, float speed) {
        if (startX < endX) {
            if (startY < endY) return new Point(startX + speed, startY + speed);
            else if (startY > endY) return new Point(startX + speed, startY - speed);
            else if (startY == endY) return new Point(startX + speed, startY);
        } else if (startX > endX) {
            if (startY < endY) return new Point(startX - speed, startY + speed);
            else if (startY > endY) return new Point(startX - speed, startY - speed);
            else if (startY == endY) return new Point(startX - speed, startY);
        } else if (startX == endX) {
            if (startY < endY) return new Point(startX, startY + speed);
            else if (startY > endY) return new Point(startX, startY - speed);
        }
        return new Point(startX, startY);
    }
}
