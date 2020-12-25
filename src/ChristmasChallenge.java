import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.MouseButton;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.ArrayList;

/**
 * TODO:    Implement lifepoints of ChristmasPresents
 * TODO:    Implement damage dealt by Turrets
 * TODO:    Implement different types of ChristmasPresents
 * TODO:    Implement different types of Turrets
 * TODO:    Add Assets
 */

public class ChristmasChallenge extends GraphicsApp implements GameConfig, ChristmasPresentListener {
    private ArrayList path;
    private BottomUI bottomUI;
    private static ChristmasPresent[] currentWave;
    private Turret turret0;
    private Turret turret1;

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }

    @Override
    public void initialize() {
        setFrameRate(FRAME_RATE);
        path = SantasLittleHelper.setupPath();
        bottomUI = new BottomUI(this);
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        turret0 = new Turret(200, 500);
        turret1 = new Turret(550, 200);
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawPath();
        drawWave();
        bottomUI.draw();
        turret0.draw();
        turret1.draw();
    }

    private void drawPath() {
        for (int i = 0; i < path.size(); i++) {
            Line element = (Line) path.get(i);
            element.draw();
        }
    }

    private void drawWave() {
        if (waveIsOver()) {
            currentWave = null;
        }
        if (currentWave != null) {
            for (int i = 0; i < currentWave.length; i++) {
                if (currentWave[i] != null) currentWave[i].draw();
            }
        }
    }

    @Override
    public void onPresentReachedEndOfPath(ChristmasPresent present) {       //remove ChristmasPresent from Array
        removePresentFromArray(present);
    }

    private void removePresentFromArray(ChristmasPresent present) {
        for (int i = 0; i < currentWave.length; i++) {
            if (currentWave[i] == present) {
                currentWave[i] = null;
                break;
            }
        }
        if (waveIsOver()) bottomUI.changeStartButtonAsset();    //if wave is now over, change Asset of StartButton

    }

    private boolean waveIsOver() {
        if (currentWave == null) return true;
        for (int i = 0; i < currentWave.length; i++) {
            if (currentWave[i] != null) return false;
        }
        return true;
    }

    public static ChristmasPresent[] getCurrentWave() {
        return currentWave;
    }

    public boolean currentWaveIsAttacking() {
        return currentWave != null;
    }

    @Override
    public void onMousePressed(MousePressedEvent event) {
        if (event.getButton() == MouseButton.LEFT) {        //only handle left click
            bottomUI.handleMouseClick(event.getXPos(), event.getYPos());
        }
    }

    public void launchNextWave() {
        if (currentWave == null){
            currentWave = SantasLittleHelper.getNextWave(this);
        }
    }
}
