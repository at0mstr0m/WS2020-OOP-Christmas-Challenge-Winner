import de.ur.mi.oop.graphics.Rectangle;

public class RightUI implements GameConfig, InputEventListener {
    private ChristmasChallenge listener;
    private Rectangle body;
    public StartButton startButton;
    private TurretButton turretButtonOne;
    private TurretButton turretButtonTwo;

    public RightUI(ChristmasChallenge listener) {
        this.listener = listener;
        this.body = new Rectangle(RIGHT_UI_X_POS, RIGHT_UI_Y_POS, RIGHT_UI_WIDTH, RIGHT_UI_HEIGHT, FIRE_OPAL);
        this.startButton = new StartButton(listener);
        this.turretButtonOne = new TurretButton(listener,0);
        this.turretButtonTwo = new TurretButton(listener,1);
    }

    public void changeStartButtonAsset() {
        this.startButton.changeAsset();
    }

    public void draw() {
        body.draw();
        startButton.draw();
        turretButtonOne.draw();
        turretButtonTwo.draw();
    }

    @Override
    public void handleMouseClick(int x, int y) {
        if (startButton.hitTest(x,y) && !listener.currentWaveIsAttacking()) {
            startButton.changeAsset();
            listener.launchNextWave();
        }
    }
}
