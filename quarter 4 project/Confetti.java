import java.awt.*;

public class Confetti {
    private int x;
    private int y;
    private Color color;
    public Confetti(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public void drawMe(Graphics g){
        g.setColor(color);
        g.fillOval(x,y,30,30);
    }
    public void animateConfetti(){
        y++;
        if(y>=800){
            y=0;
        }
    }
}
