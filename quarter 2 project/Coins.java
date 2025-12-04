import java.awt.*;

public class Coins {
    private int x;
    private int y;
    private int width = 20;
    private int height = 20;
    private boolean visible = true;

    private Color yellow = new Color(255,255,0);
    private Color yellow2 = new Color(255,200,0);

    public Coins(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawCoins(Graphics g) {
        if(visible == true ) {
            g.setColor(yellow);
            g.fillOval(x,y,width,height);
            g.setColor(yellow2);
            g.fillOval(x+2,y+2,16,16);
        }
        
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


    public boolean getVisible() {
        return visible;

    }
    public void setVisible(boolean v){
        visible = v;
    }
}