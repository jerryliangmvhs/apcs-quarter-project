import java.awt.*;

public class Player{
    private Color purple = new Color(100,0,255);
    private Color black = new Color(0,0,0);
    private Color white = new Color(255,255,255);
    private int x;
    private int y;
    private int height = 38;
    private int width = 38; //even though a grid square is technically a 40x40, we want it to fit within the lines so we make it slightly smaller.
    private boolean visible = true;
    private int prevX;
    private int prevY;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.prevX = x;
        this.prevY = y;
    }
    public void drawMe(Graphics g) {
        if(visible == true) {
            g.setColor(purple);
            g.fillRect(x,y,width,height);
            g.setColor(white);
            g.fillRect(x+5,y+5,10,10);
            g.fillRect(x+25,y+5,10,10);
            g.setColor(black);
            g.fillRect(x+7,y+10,5,5);
            g.fillRect(x+27,y+10,5,5);
            g.fillRect(x+5,y+25,30,3);
            
        }
    }
    public int getPlayerX() {
        return x;
    }
    public int getPlayerHeight() {
        return height;
    }
    public int getPlayerY() {
        return y;
    }
    public int getPlayerWidth() {
        return width;
    }
    public boolean getVisible() {
        return visible;

    }
    public void setVisible(boolean v){
        visible = v;
    }
    public void moveUp() {
        y = y-40;
        prevY = y;
    }
    public void moveDown() {
        y = y+40;
        prevY = y;
    }
    public void moveLeft() {
        x = x-40;
        prevX = x;
    }
    public void moveRight() {
        x = x+40;
        prevX = x;
    }
    public void setTopBounds() {
        if(y < 0) {
            y = 1;
        }
    }
    public void setBottomBounds() {
        if(y > 570) {
            y = 561;
        }
    }
    public void setLeftBounds() {
        if(x < 0) {
            x = 0;
        }
    }
    public void setRightBounds() {
        if(x > 1200) {
            x = 1200;
        }
    }
    public void moveBack() {
        x = prevX;
        y = prevY;
    }

    public boolean checkLava(Lava lava) {
        int pX = x;
        int pY = y;
        int pWidth = width;
        int pHeight = height;

        
        int lX = lava.getX();
        int lY = lava.getY();


        int lHeight = lava.getHeight();
        int lWidth = lava.getWidth();

        //code for collision detection
        if( pX+pWidth > lX && pX < lX + lWidth && pY+pHeight > lY && pY < lY+lHeight ){
            //removed the equals sign from the coding demo so that way the object disappears upon overlap rather than touching.
            System.out.println("Player tried to swim in lava.");
            return true;
        }
        return false;
    }
    public boolean checkCoins(Coins coins) {
        int pX = x;
        int pY = y;
        int pWidth = width;
        int pHeight = height;

        
        int cX = coins.getX();
        int cY = coins.getY();


        int cHeight = coins.getHeight();
        int cWidth = coins.getWidth();

        //code for collision detection
        if( pX+pWidth > cX && pX < cX + cWidth && pY+pHeight > cY && pY < cY+cHeight ){
            //removed the equals sign from the coding demo so that way the object disappears upon overlap rather than touching.
            //System.out.println("Coin Collected");
            return true;
        }
        return false;
    }
    public boolean checkBarrier(Barrier barrier) {
        int pX = getPlayerX();
        int pY = getPlayerY();
        int pWidth = getPlayerWidth();
        int pHeight = getPlayerHeight();

        
        int bX = barrier.getBarrierX1();
        int bY = barrier.getBarrierY1();
        int bX2 = barrier.getBarrierX2();
        int bY2 = barrier.getBarrierY2();


        int bHeight = barrier.getHeight();
        int bWidth = barrier.getWidth();

        //code for collision detection
        if(( pX+pWidth > bX && pX < bX + bWidth && pY+pHeight > bY && pY < bY+bHeight ) || (pX+pWidth > bX2 && pX < bX2 + bWidth && pY+pHeight > bY2 && pY < bY2+bHeight)){
            //removed the equals sign from the coding demo so that way the object disappears upon overlap rather than touching.
            return true;
        }
        return false;

    }
    public boolean checkTreasure(Treasure treasure){
        int pX = x;
        int pY = y;
        int pWidth = width;
        int pHeight = height;

        int tX = treasure.getX();
        int tY = treasure.getY();
        int tWidth = treasure.getWidth();
        int tHeight = treasure.getHeight();

        if(pX+pWidth >= tX && pX <= tX + tWidth  &&  pY+pHeight >= tY && pY <= tY + tHeight ){
	        //System.out.println("Collision");
            return true;
        }	
        return false;
    }
    
}
