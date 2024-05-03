package hu.gde.hzoxye.eloalk;

public class Snail {
    public enum Color {
        RED,
        GREEN,
        BLUE
    }

    private Integer id;
    private Color color;

    public Snail(Integer id, Color color) {
        this.id = id;
        this.color = color;
    }
}
