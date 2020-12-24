import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import ui.BottomUI;
import ui.ChristmasPresent;
import ui.ChristmasPresentListener;

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
    private Turret turret;
    private long start;
    private long step;
    private long waveSpacingInMS;
    private int lastLaunchedIndex;

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }

    @Override
    public void initialize() {
        path = SantasLittleHelper.setupPath();
        bottomUI = new BottomUI();
        currentWave = SantasLittleHelper.fillCurrentWave(5, this);
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        waveSpacingInMS = 1000;
        lastLaunchedIndex = 0;
        start = System.currentTimeMillis();
        turret = new Turret();
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawPath();
        drawWave();
        bottomUI.draw();
        turret.draw();
    }

    private void drawPath() {
        for (int i = 0; i < path.size(); i++) {
            Line element = (Line) path.get(i);
            element.draw();
        }
    }

    private void drawWave() {
        step = System.currentTimeMillis();
        if (step - start >= waveSpacingInMS && lastLaunchedIndex < currentWave.length) {
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
                return;
            }
        }
    }

    public static ChristmasPresent[] getCurrentWave() {
        return currentWave;
    }
}