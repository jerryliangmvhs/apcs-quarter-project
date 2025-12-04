import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;

public class Card {

    private BufferedImage card;
    private boolean visible;

    public Card(BufferedImage card){
        this.card = card;
    }
    public void drawMe(Graphics g){
        if(visible){
            g.drawImage(card,300,100,null);
        }
    }
    public boolean getVisible(){
        return visible;
    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }
}
