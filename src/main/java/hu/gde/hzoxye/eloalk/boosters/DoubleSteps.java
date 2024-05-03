package hu.gde.hzoxye.eloalk.boosters;

public class DoubleSteps implements Booster {

    @Override
    public int boostRound(int steps) {
        return steps * 2;
    }

    @Override
    public String getName() {
        return "Double Steps";
    }
}
