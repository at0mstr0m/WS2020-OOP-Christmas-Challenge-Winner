import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Rectangle;

public class RightUI implements GameConfig, WaveContent, InputEventListener, TurretButtonListener {
    private RightUIListener listener;
    private Rectangle body;
    public StartButton startButton;
    private TurretButton[] turretButtons;
    private Label moneyLabel;
    private Label lifesLabel;
    private Label waveLabel;
    private TurretButton currentlyPlacingTurretButtonInstance;
    public RightUI(RightUIListener listener) {
        this.listener = listener;
        this.body = new Rectangle(RIGHT_UI_X_POS, RIGHT_UI_Y_POS, RIGHT_UI_WIDTH, RIGHT_UI_HEIGHT, RIGHT_UI_COLOR);
        this.startButton = new StartButton(listener);
        turretButtons = new TurretButton[NUM_OF_TURRETS];
        for (int i = 0; i < turretButtons.length; i++) {
            turretButtons[i] = new TurretButton(listener,this, i);
        }
        this.moneyLabel = new Label(MONEY_LABEL_X_POS, MONEY_LABEL_Y_POS, listener.getMoneyAsString(), MONEY_LABEL_FONT_COLOR);
        this.moneyLabel.setFontSize(MONEY_LABEL_FONT_SIZE);
        this.lifesLabel = new Label(LIFES_LABEL_X_POS, LIFES_LABEL_Y_POS, "Lifes: " + listener.getLifesAsString(), MONEY_LABEL_FONT_COLOR);
        this.lifesLabel.setFontSize(LIFES_LABEL_FONT_SIZE);
        this.waveLabel = new Label(WAVE_LABEL_X_POS, WAVE_LABEL_Y_POS, "Wave: " + SantasStaticHelper.getWaveCounter()  + " / " + waves.length, MONEY_LABEL_FONT_COLOR);
        this.waveLabel.setFontSize(WAVE_LABEL_FONT_SIZE);
    }

    public void changeStartButtonAsset() {
        this.startButton.changeAsset();
    }

    public void draw() {
        body.draw();
        startButton.draw();
        drawTurretButtons();
        updateAndDrawLabels();
    }

    private void updateAndDrawLabels() {
        moneyLabel.setText(listener.getMoneyAsString());    //refresh current Money
        moneyLabel.draw();
        lifesLabel.setText(listener.getLifesAsString());    //refresh current Lifes
        lifesLabel.draw();
        waveLabel.setText(SantasStaticHelper.getWaveCounterAsString() + " / " + waves.length); //refresh current WaveCounter
        waveLabel.draw();
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
                    turretButtons[i].toggleBuildMode();
                }
            }
        }
    }

    @Override
    public void setCurrentlyPlacingTurretButtonInstance(TurretButton currentlyPlacingTurretButtonInstance) {
        this.currentlyPlacingTurretButtonInstance = currentlyPlacingTurretButtonInstance;
    }

    @Override
    public void handleMouseClick(MousePressedEvent event) {

    }

    @Override
    public TurretButton getCurrentlyPlacingTurretButtonInstance() {
        return currentlyPlacingTurretButtonInstance;
    }
}
