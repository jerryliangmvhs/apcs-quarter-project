import java.awt.*;
public class win {
    Color purple = new Color(100,0,255);
    Color black = new Color(0,0,0);
    Color white = new Color(255,255,255);
    private int x;
    private int y;
    //this class is made for the player animation at the end of the game
    public win(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public void drawMe(Graphics g) {
        
        g.setColor(purple);
        g.fillRect(x,y,80,80);
        g.setColor(white);
        g.fillRect(x+10,y+10,20,20);
        g.fillRect(x+50,y+10,20,20);
        g.setColor(black);
        //pupils
        g.fillRect(x+14,y+20,10,10);
        g.fillRect(x+54,y+20,10,10);
        //mouth
        g.fillRect(x+10,y+50,60,6);
        g.fillRect(x+10,y+40,6,10);
        g.fillRect(x+65,y+40,5,10);
    }
    public void move() {
        x+=5;
        if(x>1200) {
            x=0;
        }

    }
}
