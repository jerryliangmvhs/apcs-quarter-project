import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Intro {
    private int width = 0;
    private BufferedImage introBackground;
    private Color white = new Color(255,255,255);

    public Intro(){
        try {
            introBackground = ImageIO.read(new File("loadingScreen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawMe(Graphics g){
        if(width<=600){
            g.drawImage(introBackground,0,0,null);
            g.setColor(white);
            g.fillRect(100,480,width,28);
        }
    }
    public void updateProgressBar(){
        if(width <=600){
            width +=1;
        }
    }
    public boolean visible(){
        if(width <=600){
            return true;
        }
        return false;
    }
}
