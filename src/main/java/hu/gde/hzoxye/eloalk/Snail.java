package hu.gde.hzoxye.eloalk;

import hu.gde.hzoxye.eloalk.boosters.Booster;

import java.util.ArrayList;
import java.util.Random;

public class Snail {

    public enum Color {
        RED,
        GREEN,
        BLUE
    }

    private int id;
    private Color color;
    private int stepsMade;
    private Random random;
    private ArrayList<Booster> boosters;

    public Snail(int id, Color color) {
        this.id = id;
        this.color = color;
        stepsMade = 0;
        random = new Random();
        boosters = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getStepsMade() {
        return stepsMade;
    }

    public void addBooster(Booster booster) {
        System.out.printf("   Snail #%d got a %s booster\n", getId(), booster.getName());
        boosters.add(booster);
    }

    public void step() {
        int steps = random.nextInt(3);

        for (Booster b : boosters) {
            steps = b.boostRound(steps);
        }

        stepsMade += steps;

        System.out.printf("   Snail #%d moved %d steps, %d so far.\n", getId(), steps, getStepsMade());

        boosters.clear();
    }

}
