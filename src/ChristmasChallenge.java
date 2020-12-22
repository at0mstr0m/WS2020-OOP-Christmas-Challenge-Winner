import config.GameConfig;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.graphics.Line;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;
import ui.BottomUI;

import java.util.ArrayList;

/**
 * Auf Basis dieses Grundgerüst können Sie Ihr eigenes Spiel entwickeln. In der GameConfig finden Sie
 * für den Fall der Fälle eine Sammlung weihnachtlicher Farbwerte. Bitte füllen Sie vor der Abgabe Ihres
 * Beitrags die Readme-Datei in diesem Projektverzeichnis aus und laden Sie erst dann das gesamte Projekt
 * als ZIP-Datei hoch.
 */

public class ChristmasChallenge extends GraphicsApp implements GameConfig {
    ArrayList path;
    BottomUI bottomUI;

    public static void main(String[] args) {
        // Instanziiert eine Instanz dieser Klasse und startet die GraphicsApp
        GraphicsAppLauncher.launch();
    }

    @Override
    public void initialize() {
        path = Initializer.setupPath();
        bottomUI = new BottomUI();
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        for (int i = 0; i < path.size(); i++) {
            Line element = (Line) path.get(i);
            element.draw();
        }
        bottomUI.draw();
    }
}