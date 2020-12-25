import de.ur.mi.oop.graphics.Rectangle;

import java.io.File;

public class BottomUI implements GameConfig, BottomUIListener {
    private ChristmasChallenge listener;
    private Rectangle body;
    public StartButton startButton;

    public BottomUI(ChristmasChallenge listener) {
        this.listener = listener;
        this.body = new Rectangle(BOTTOM_UI_X_POS, BOTTOM_UI_Y_POS, BOTTOM_UI_WIDTH, BOTTOM_UI_HEIGHT, FIRE_OPAL);
        this.startButton = new StartButton(listener);
    }

    public void changeStartButtonAsset() {
        System.out.println("Bump");
        this.startButton.changeAsset();
    }

    public void draw() {
        body.draw();
        startButton.draw();
    }

    @Override
    public void handleMouseClick(int x, int y) {
        if (startButton.hitTest(x,y) && !listener.currentWaveIsAttacking()) {
            startButton.changeAsset();
            listener.launchNextWave();
        }
    }
}
