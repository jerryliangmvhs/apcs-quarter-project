import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Sprite {
    public Player(int row, int column, String name) {
        super(row, column, name);
    }
    @Override
    public void drawMe(Graphics g, BufferedImage sprite) {
        g.drawImage(sprite, (getColumn()*80)+75,(getRow()*80)+30,null);
    }
}
