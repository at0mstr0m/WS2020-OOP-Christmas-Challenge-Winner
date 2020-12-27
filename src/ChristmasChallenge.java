import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.MouseButton;
import de.ur.mi.oop.events.MouseMovedEvent;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * TODO:    Implement lifepoints of ChristmasPresents
 * TODO:    Implement damage dealt by Turrets
 * TODO:    Add Money and Money Label
 * TODO:    Implement getting money from destroyed ChristmasPresents
 * TODO:    Make Turrets upgradable
 * TODO:    Make Turrets sellable
 * TODO:    Reset Cooldown after wave
 * TODO:    Implement attack range of Turrets
 * TODO:    Add different types of Turrets
 * TODO:    Add Assets
 */

public class ChristmasChallenge extends GraphicsApp implements GameConfig, ChristmasPresentListener {
    private RightUI rightUI;
    private static ChristmasPresent[] currentWave;
    private Board board;
    private Point currentMousePosition;
    private int money;

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }

    @Override
    public void initialize() {
        money = START_MONEY;
        setFrameRate(FRAME_RATE);
        SantasLittleHelper.fillAnchorPoints();
        rightUI = new RightUI(this);
        board = new Board(this, rightUI);
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        board.draw();
        drawWave();
        rightUI.draw();
    }

    private void drawWave() {
        if (waveIsOver()) currentWave = null;
        if (currentWave != null) {
            for (int i = currentWave.length - 1; i >= 0; i--) {     //drawn from back to the front so the ChristmasPresent at the front position is not overdrawn
                if (currentWave[i] != null) currentWave[i].draw();
            }
        }
    }

    @Override
    public void onPresentReachedEndOfPath(ChristmasPresent present) {       //remove ChristmasPresent from Array
        removePresentFromArray(present);
    }

    @Override
    public void onPresentDestroyed(ChristmasPresent present) {
        removePresentFromArray(present);
    }

    private void removePresentFromArray(ChristmasPresent present) {
        for (int i = 0; i < currentWave.length; i++) {
            if (currentWave[i] == present) {
                currentWave[i] = null;
                break;
            }
        }
        if (waveIsOver()) rightUI.changeStartButtonAsset();    //if wave is now over, change Asset of StartButton
    }

    private boolean waveIsOver() {
        if (currentWave == null) return true;
        for (int i = 0; i < currentWave.length; i++) {
            if (currentWave[i] != null) return false;
        }
        return true;
    }

    public static ChristmasPresent[] getCurrentWave() {
        return currentWave;
    }

    public static boolean currentWaveIsAttacking() {
        return currentWave != null;
    }

    @Override
    public void onMousePressed(MousePressedEvent event) {
        if (event.getButton() == MouseButton.LEFT) {                    //only handle left click for rightUI
            rightUI.handleMouseClick(event.getXPos(), event.getYPos()); //pass on click event information to rightUI
        }
        board.handleMouseClick(event);   //pass on click event information to Board
        System.out.println("x = " + event.getXPos() + " y = " + event.getYPos());
    }

    public void onMouseMoved(MouseMovedEvent event) {
        currentMousePosition = new Point(event.getXPos(), event.getYPos());
    }


    public void launchNextWave() {
        if (currentWave == null){
            currentWave = SantasLittleHelper.getNextWave(this);
        }
    }

    public float getCurrentMouseXPos() {
        return currentMousePosition.getXPos();
    }

    public float getCurrentMouseYPos() {
        return currentMousePosition.getYPos();
    }

    public String getMoneyAsString() {
        return "$ " + money;
    }
}
