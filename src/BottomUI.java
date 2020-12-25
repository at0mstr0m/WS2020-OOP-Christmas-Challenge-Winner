import de.ur.mi.oop.graphics.Rectangle;

import java.io.File;

public class BottomUI implements GameConfig, BottomUIListener {
    private ChristmasChallenge listener;
    private Rectangle body;
    private StartButton startButton;

    public BottomUI(ChristmasChallenge listener) {
        this.listener = listener;
        int playPauseButtonIndex = 1;
        this.body = new Rectangle(BOTTOM_UI_X_POS, BOTTOM_UI_Y_POS, BOTTOM_UI_WIDTH, BOTTOM_UI_HEIGHT, FIRE_OPAL);
        this.startButton = new StartButton(listener);
    }

    private String[] collectFilesInFolder(String relativeFolderPath) {
        File[] files = new File(relativeFolderPath).listFiles();
        String[] absFilePaths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            absFilePaths[i] = files[i].getAbsolutePath();
            System.out.println(absFilePaths[i]);
        }
        return absFilePaths;
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
