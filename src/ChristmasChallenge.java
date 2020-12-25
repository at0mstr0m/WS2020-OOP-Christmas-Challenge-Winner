import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.MouseButton;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.ArrayList;

/**
 * TODO:    Implement multiple places for turrets
 * TODO:    Make turrets hit the middle of a Christmas Present
 * TODO:    Implement lifepoints of ChristmasPresents
 * TODO:    Implement damage dealt by Turrets
 * TODO:    Make Presents destructible
 * TODO:    Add different types of ChristmasPresents
 * TODO:    Add different types of Turrets
 * TODO:    Add Assets
 */

public class ChristmasChallenge extends GraphicsApp implements GameConfig, ChristmasPresentListener {
    private BottomUI bottomUI;
    private static ChristmasPresent[] currentWave;
    private Board board;
    private Turret turret0;
    private Turret turret1;

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }

    @Override
    public void initialize() {
        setFrameRate(FRAME_RATE);
        SantasLittleHelper.fillAnchorPoints();
        board = new Board(this);
        bottomUI = new BottomUI(this);
        turret0 = new Turret(200, 500);
        turret1 = new Turret(550, 200);
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        board.draw();
        drawWave();
        bottomUI.draw();
        turret0.draw();
        turret1.draw();
    }

    private void drawWave() {
        if (waveIsOver()) currentWave = null;
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
