public interface RightUIListener {
    String getMoneyAsString();
    String getLifesAsString();
    boolean currentWaveIsAttacking();
    void launchNextWave();
    void resetTurretFireCounters();
    float getCurrentMouseXPos();
    float getCurrentMouseYPos();
}
