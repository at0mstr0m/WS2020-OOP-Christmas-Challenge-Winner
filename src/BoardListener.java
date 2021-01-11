import java.util.ArrayList;

public interface BoardListener {
    int getMoney();

    void spendMoney(int price);

    void earnMoneyFromSelling(int worth);

    ArrayList<ChristmasPresent> getCurrentWave();

    boolean currentWaveIsAttacking();
}
