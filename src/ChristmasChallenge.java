import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.MouseButton;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.ArrayList;

/**
 * Auf Basis dieses Grundgerüst können Sie Ihr eigenes Spiel entwickeln. In der GameConfig finden Sie
 * für den Fall der Fälle eine Sammlung weihnachtlicher Farbwerte. Bitte füllen Sie vor der Abgabe Ihres
 * Beitrags die Readme-Datei in diesem Projektverzeichnis aus und laden Sie erst dann das gesamte Projekt
 * als ZIP-Datei hoch.
 */

public class ChristmasChallenge extends GraphicsApp implements GameConfig, ChristmasPresentListener {
    private ArrayList path;
    private BottomUI bottomUI;
    private static ChristmasPresent[] currentWave;
    private Turret turret0;
    private Turret turret1;
    private long start;
    private long step;
    private long waveDelayInMilliseconds;
    private int lastLaunchedIndex;

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }

    @Override
    public void initialize() {
        setFrameRate(FRAME_RATE);
        path = SantasLittleHelper.setupPath();
        bottomUI = new BottomUI(this);
        currentWave = SantasLittleHelper.fillCurrentWave(5, this);
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        waveDelayInMilliseconds = 1000;
        lastLaunchedIndex = 0;
        start = System.currentTimeMillis();
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
        step = System.currentTimeMillis();
        if (step - start >= waveDelayInMilliseconds && lastLaunchedIndex < currentWave.length) {
            currentWave[lastLaunchedIndex].declareAsVisible();                  //declateAsVisible before Launch
            lastLaunchedIndex++;
            start = step;
        }
        for (int i = 0; i < lastLaunchedIndex; i++) {
            if (currentWave[i] != null) currentWave[i].draw();
        }
    }

    @Override
    public void onPresentReachedEndOfPath(ChristmasPresent present) {       // Entfern das Geschenk aus dem Array, um Platz für ein neues zu machen
        removePresentFromArray(present);
    }

    private void removePresentFromArray(ChristmasPresent present) {
        for (int i = 0; i < currentWave.length; i++) {
            if (currentWave[i] == present) {
                currentWave[i] = null;
                break;
            }
        }
    }

    public static ChristmasPresent[] getCurrentWave() {
        return currentWave;
    }

    @Override
    public void onMousePressed(MousePressedEvent event) {
        if (event.getButton() == MouseButton.LEFT) {        //only handle left click
            bottomUI.handleMouseClick(event.getXPos(), event.getYPos());
        }
    }

    public void launchNextWave() {

    }

}