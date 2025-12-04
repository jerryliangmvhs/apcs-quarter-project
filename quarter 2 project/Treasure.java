import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;

public class Treasure {
    private int x;
    private int y;
    private int width = 40;
    private int height = 40;
    private BufferedImage openChest;
    private BufferedImage closedChest;
    private boolean visible = true;

    public Treasure(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            openChest = ImageIO.read(new File("openChest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            closedChest = ImageIO.read(new File("closedChest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void drawMe(Graphics g){
        if(visible ==true) {
            g.drawImage(closedChest,x,y,width,height,null);
        }
        if(visible ==false) {
            g.drawImage(openChest,x,y,width,height,null);
        }

    }
    public boolean getVisible() {
        return visible;

    }
    public void setVisible(boolean v){
        visible = v;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
