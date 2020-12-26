import de.ur.mi.oop.graphics.Rectangle;

public class RightUI implements GameConfig, InputEventListener {
    private ChristmasChallenge listener;
    private Rectangle body;
    public StartButton startButton;
    private TurretButton[] turretButtons;
    public TurretButton currentlyPlacingTurretButtonInstance;

    public RightUI(ChristmasChallenge listener) {
        this.listener = listener;
        this.body = new Rectangle(RIGHT_UI_X_POS, RIGHT_UI_Y_POS, RIGHT_UI_WIDTH, RIGHT_UI_HEIGHT, FIRE_OPAL);
        this.startButton = new StartButton(listener);
        turretButtons = new TurretButton[NUM_OF_TURRETS];
        for (int i = 0; i < turretButtons.length; i++) {
            turretButtons[i] = new TurretButton(listener,this, i);
        }
    }

    public void changeStartButtonAsset() {
        this.startButton.changeAsset();
    }

    public void draw() {
        body.draw();
        startButton.draw();
        drawTurretButtons();
    }

    private void drawTurretButtons() {
        for (int i = 0; i < turretButtons.length; i++) {
            turretButtons[i].draw();
        }
    }

    @Override
    public void handleMouseClick(int x, int y) {
        if (startButton.hitTest(x,y) && !listener.currentWaveIsAttacking()) {   //startButton
            startButton.changeAsset();
            listener.launchNextWave();
        }

        for (int i = 0; i < turretButtons.length; i++) {
            if (turretButtons[i].hitTest(x,y)){
                if (turretButtons[i] == currentlyPlacingTurretButtonInstance || currentlyPlacingTurretButtonInstance == null) {
                    turretButtons[i].lockOrUnlockAsset(x, y);
                }
            }
        }
    }
}
