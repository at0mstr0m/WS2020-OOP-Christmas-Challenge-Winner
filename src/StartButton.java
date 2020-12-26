import de.ur.mi.oop.graphics.Image;

public class StartButton implements GameConfig {
    private String startButtonAssets[] = {
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_PLAY_PAUSE_BUTTON + "play_button1.png",
            SantasLittleHelper.getWorkingDirectory() + PATH_TO_ASSETS_PLAY_PAUSE_BUTTON + "play_button2.png"
    };
    private Image buttonImage;
    private String assetInUse;

    public StartButton(ChristmasChallenge listener) {
        assetInUse = startButtonAssets[1];
        this.buttonImage = new Image(BUTTONS_X_POS,START_BUTTON_Y_POS, assetInUse);
    }

    public void draw() {
        buttonImage.draw();
    }

    public boolean hitTest(int x, int y) {
        return buttonImage.hitTest(x,y);
    }

    public void changeAsset() {
        if (assetInUse.equals(startButtonAssets[0])) {
            assetInUse = startButtonAssets[1];
        }
        else if (assetInUse.equals(startButtonAssets[1])) {
            assetInUse = startButtonAssets[0];
        }
        buttonImage = new Image(BUTTONS_X_POS, START_BUTTON_Y_POS, assetInUse);
    }
}
