import de.ur.mi.oop.graphics.Image;

public class StartButton implements GameConfig {
    private String playButtonAssets[] = {SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_PLAY_PAUSE_BUTTON + "play_button1.png", SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_PLAY_PAUSE_BUTTON + "play_button2.png"};
    private Image buttonImage;
    private int playPauseButtonIndex;
    private String[] playButtonAssetsPaths;
    private float playPauseButtonYPos;
    private float playPauseButtonXPos;
    private String assetInUse;

    public StartButton(ChristmasChallenge listener) {
        assetInUse = playButtonAssets[1];
        this.buttonImage = new Image(0,0, assetInUse);
        playPauseButtonYPos = (BOTTOM_UI_Y_POS + (BOTTOM_UI_HEIGHT - buttonImage.getHeight()) / 2);
        playPauseButtonXPos = (BOTTOM_UI_X_POS + (BOTTOM_UI_HEIGHT - buttonImage.getHeight()) / 2);
        this.buttonImage.setXPos(playPauseButtonXPos);
        this.buttonImage.setYPos(playPauseButtonYPos);
    }

    public void draw() {
        buttonImage.draw();
    }

    public boolean hitTest(int x, int y) {
        return buttonImage.hitTest(x,y);
    }

    public void changeAsset() {
        System.out.println("BumpBump");
        if (assetInUse.equals(playButtonAssets[0])) {
            assetInUse = playButtonAssets[1];
        }
        else if (assetInUse.equals(playButtonAssets[1])) {
            assetInUse = playButtonAssets[0];
        }
        buttonImage = new Image(playPauseButtonXPos, playPauseButtonYPos, assetInUse);
    }
}
