import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.MouseButton;
import de.ur.mi.oop.events.MouseMovedEvent;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.graphics.Point;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.ArrayList;

/**
 * TODO:    improve lifepoints of ChristmasPresents
 * TODO:    improve damage dealt by Turrets
 * TODO:    improve money added from destroyed ChristmasPresents
 * TODO:    add GameOver
 * TODO:    Implement costs for Turrets
 * TODO:    Implement different types of Turrets
 * TODO:    Reset Cooldown after wave
 * TODO:    Implement attack range of Turrets
 */

public class ChristmasDefense extends GraphicsApp implements GameConfig, ChristmasPresentListener, BoardListener, RightUIListener {
    private RightUI rightUI;
    private static ArrayList<ChristmasPresent> currentWave;
    private Board board;
    private Point currentMousePosition;
    private int money;
    private int lifes;
    private boolean gameOver;
    private boolean hasWon;
    private Label finalLabel;

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }

    @Override
    public void initialize() {
        gameOver = false;
        hasWon = false;
        money = START_MONEY;
        lifes = START_LIFES;
        setFrameRate(FRAME_RATE);
        SantasStaticHelper.fillAnchorPoints();
        rightUI = new RightUI(this);
        board = new Board(this, rightUI);
        setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public void draw() {
        if (!gameOver) {
            board.draw();
            drawWave();
            rightUI.draw();
        } else {
            if (!hasWon) finalLabel = new Label(0, WINDOW_HEIGHT_MIDDLE, "Game over after " + SantasStaticHelper.getWaveCounter() + " waves!", Colors.BLACK);
            else finalLabel = new Label(0, WINDOW_HEIGHT_MIDDLE, "Congratulations, you have successfully prevented all christmas celebrations!", Colors.BLACK);
            finalLabel.setFontSize(20);
            finalLabel.setXPos(WINDOW_WIDTH_MIDDLE - (finalLabel.getWidthEstimate() / 2));
            finalLabel.draw();
        }
    }

    private void drawWave() {
        if (waveIsOver()) currentWave = null;
        if (currentWave != null) {
            /*
             * drawn from back to the front so the ChristmasPresent
             * at the front position is not overdrawn
             */
            for (int i = currentWave.size() - 1; i >= 0; i--) {
                currentWave.get(i).draw();
            }
        }
    }

    @Override
    public void onPresentReachedEndOfPath(ChristmasPresent present) {       //remove ChristmasPresent from Array
        removePresentFromArray(present);
        lifes--;                            //one life lost
        if (lifes == 0) gameOver = true;
    }

    @Override
    public void onPresentDestroyed(ChristmasPresent present) {
        removePresentFromArray(present);
        money += present.getWorth();        //add worth of destroyed present to money
    }

    private void removePresentFromArray(ChristmasPresent present) {
        currentWave.remove(present);
        currentWave.trimToSize();
        if (waveIsOver()) {
            rightUI.changeStartButtonAsset();    //if wave is now over, change Asset of StartButton
            money += 50;
            if (SantasStaticHelper.getWaveCounter() == SantasStaticHelper.waves.length) {
                gameOver = true;
                hasWon = true;
            }
        }
    }

    private boolean waveIsOver() {
        if (currentWave == null) return true;
        if (currentWave.size() == 0) {
            currentWave = null;
            return true;
        } else return false;
    }

    @Override
    public ArrayList<ChristmasPresent> getCurrentWave() {
        return currentWave;
    }

    @Override
    public boolean currentWaveIsAttacking() {
        return currentWave != null;
    }

    @Override
    public void onMousePressed(MousePressedEvent event) {
        if (event.getButton() == MouseButton.LEFT) {                    //only handle left click for rightUI
            rightUI.handleMouseClick(event.getXPos(), event.getYPos()); //pass on click event information to rightUI
        }
        board.handleMouseClick(event);   //pass on click event information to Board
    }

    public void onMouseMoved(MouseMovedEvent event) {
        currentMousePosition = new Point(event.getXPos(), event.getYPos());
    }

    @Override
    public void launchNextWave() {
        if (currentWave == null){
            currentWave = SantasStaticHelper.getNextWave(this);
        }
    }

    @Override
    public float getCurrentMouseXPos() {
        return currentMousePosition.getXPos();
    }

    @Override
    public float getCurrentMouseYPos() {
        return currentMousePosition.getYPos();
    }

    @Override
    public String getMoneyAsString() {
        return "$ " + money;
    }

    @Override
    public String getLifesAsString() {
        return "Lifes: " + lifes;
    }

    @Override
    public void spendMoney(int price) {
        if (price > 0) money -= price;
    }

    /**
     * When selling a Turret only half of its value is returned.
     */
    @Override
    public void earnMoneyFromSelling(int value) {
        if (value > 0) money += value / 2;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void resetTurretFireCounters() {
        this.board.resetFireCounters();
    }
}
