import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Rectangle;

public class RightUI implements GameConfig, InputEventListener {
    private ChristmasChallenge listener;
    private Rectangle body;
    public StartButton startButton;
    private TurretButton[] turretButtons;
    private Label moneyLabel;
    public TurretButton currentlyPlacingTurretButtonInstance;

    public RightUI(ChristmasChallenge listener) {
        this.listener = listener;
        this.body = new Rectangle(RIGHT_UI_X_POS, RIGHT_UI_Y_POS, RIGHT_UI_WIDTH, RIGHT_UI_HEIGHT, FIRE_OPAL);
        this.startButton = new StartButton(listener);
        turretButtons = new TurretButton[NUM_OF_TURRETS];
        for (int i = 0; i < turretButtons.length; i++) {
            turretButtons[i] = new TurretButton(listener,this, i);
        }
        this.moneyLabel = new Label(MONEY_LABEL_X_POS, MONEY_LABEL_Y_POS, listener.getMoneyAsString());
        this.moneyLabel.setFontSize(MONEY_LABEL_FONT_SIZE);
        this.moneyLabel.setColor(MONEY_LABEL_FONT_COLOR);
    }

    public void changeStartButtonAsset() {
        this.startButton.changeAsset();
    }

    public void draw() {
        body.draw();
        startButton.draw();
        drawTurretButtons();
        moneyLabel.setText(listener.getMoneyAsString());    //refresh current Money
        moneyLabel.draw();
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
                    turretButtons[i].toggleBuildMode(x, y);
                }
            }
        }
    }

    @Override
    public void handleMouseClick(MousePressedEvent event) {

    }
}
