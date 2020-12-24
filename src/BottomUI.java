import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;

import java.io.File;

public class BottomUI implements GameConfig, BottomUIListener {
    private Rectangle body;
    private String[] playButtonAssetsPaths;
    private Image playPauseButton;
    private int playPauseButtonIndex;
    private float playPauseButtonYPos;
    private float playPauseButtonXPos;
    private ChristmasChallenge listener;

    public BottomUI(ChristmasChallenge listener) {
        this.listener = listener;
        int playPauseButtonIndex = 1;
        this.body = new Rectangle(BOTTOM_UI_X_POS, BOTTOM_UI_Y_POS, BOTTOM_UI_WIDTH, BOTTOM_UI_HEIGHT, FIRE_OPAL);
        this.playButtonAssetsPaths = collectFilesInFolder(PATH_TO_ASSETS_PLAY_PAUSE_BUTTON);
        this.playPauseButton = new Image(0,0, playButtonAssetsPaths[playPauseButtonIndex]);
        playPauseButtonYPos = (BOTTOM_UI_Y_POS + (BOTTOM_UI_HEIGHT - playPauseButton.getHeight()) / 2);
        playPauseButtonXPos = (BOTTOM_UI_X_POS + (BOTTOM_UI_HEIGHT - playPauseButton.getHeight()) / 2);
        this.playPauseButton.setXPos(playPauseButtonXPos);
        this.playPauseButton.setYPos(playPauseButtonYPos);
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

    @Override
    public void handleMouseClick(int x, int y) {
        if (playPauseButton.hitTest(x,y)) {
            changePlayPauseButtonImage();
        }
    }

    private void changePlayPauseButtonImage() {
        if (playPauseButtonIndex == 1) {        //play button currently showed
            playPauseButtonIndex = 0;
        } else if (playPauseButtonIndex == 0) { //pause button showed
            playPauseButtonIndex = 1;
        }
        playPauseButton = new Image(playPauseButtonXPos,playPauseButtonYPos, playButtonAssetsPaths[playPauseButtonIndex]);
    }
}
