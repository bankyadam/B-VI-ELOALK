package hu.gde.hzoxye.eloalk;

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

    public Snail(int id, Color color) {
        this.id = id;
        this.color = color;
        stepsMade = 0;
        random = new Random();
    }

    public int getId() {
        return id;
    }

    public int getStepsMade() {
        return stepsMade;
    }

    public void step() {
        int steps = random.nextInt(3);
        stepsMade += steps;

        System.out.printf("   Snail #%d moved %d steps, %d so far.\n", getId(), steps, getStepsMade());
    }

}
