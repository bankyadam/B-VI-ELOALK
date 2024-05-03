package hu.gde.hzoxye.eloalk;

public class Snail {
    public enum Color {
        RED,
        GREEN,
        BLUE
    }

    private int id;
    private Color color;

    public Snail(int id, Color color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }
}
