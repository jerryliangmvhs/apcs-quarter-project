import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Sprite {

    private int row;
    private int column;
    private String name;

    public Sprite(int row, int column) {
        this(row, column, "UnnamedSprite");
    }

    public Sprite(int row, int column, String name) {
        this.row = row;
        this.column = column;
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getName() {
        return name;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int newRow, int newColumn) {
        this.row = newRow;
        this.column = newColumn;
    }

    public abstract void drawMe(Graphics g, BufferedImage sprite);
}