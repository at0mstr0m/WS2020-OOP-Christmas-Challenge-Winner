package ui;

import config.GameConfig;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;

import java.io.File;

public class BottomUI implements GameConfig {
    private Rectangle body;
    private String[] playButtonAssetsPaths;
    private Image playPauseButton;

    public BottomUI() {
        this.body = new Rectangle(BOTTOM_UI_X_POS, BOTTOM_UI_Y_POS, BOTTOM_UI_WIDTH, BOTTOM_UI_HEIGHT, FIRE_OPAL);
        this.playButtonAssetsPaths = collectFilesInFolder(PATH_TO_ASSETS_PLAY_PAUSE_BUTTON);
        this.playPauseButton = new Image(0,0, playButtonAssetsPaths[0]);
        this.playPauseButton.setYPos(BOTTOM_UI_Y_POS + (BOTTOM_UI_HEIGHT - playPauseButton.getHeight()) / 2);
        this.playPauseButton.setXPos(BOTTOM_UI_X_POS + (BOTTOM_UI_HEIGHT - playPauseButton.getHeight()) / 2);
    }

    private String[] collectFilesInFolder(String relativeFolderPath) {
        File[] files = new File(relativeFolderPath).listFiles();
        String[] absFilePaths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            absFilePaths[i] = files[i].getAbsolutePath();
        }
        return absFilePaths;
    }

    public void draw() {
        body.draw();
        playPauseButton.draw();
    }
}
