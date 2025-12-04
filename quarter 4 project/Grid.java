import java.awt.*;

public class Grid {
    private int x;
    private int y;
    private boolean visible; //determines if the slot of the grid is used in the game or not

    public Grid(int x, int y, boolean visible){
        this.x = x;
        this.y = y;
        this.visible = visible;
        
    }
    public void drawGrid(Graphics g){
        if(visible){
            g.setColor(Color.BLACK);
            g.drawRect(x,y,80,80);
            g.setColor(Color.WHITE);
            g.fillRect(x,y,80,80);
        }

    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean getVisible(){
        return visible;
    }
    /* 
    public void movePlayer1(int num){
        if(player1.getRow()==6){
            int currentCol = player1.getColumn();
            int newColumn = currentCol - num;
            player1.setColumn(newColumn);
        }
    }
        */
}
