import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Map {
    private Color black = new Color(0,0,0);
    private BufferedImage rocks;
    private BufferedImage rocks2;
    private BufferedImage saferocks;
    private BufferedImage saferocks2;
    
    public Map() {
        try {
            rocks = ImageIO.read(new File("rocks.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            saferocks = ImageIO.read(new File("saferocks.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rocks2 = ImageIO.read(new File("rocks2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            saferocks2 = ImageIO.read(new File("saferocks2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawStoneTiles(Graphics g) {
        int rocksX = 0;
        int rocksY = 0;
        while(rocksY<600) {
            g.drawImage(rocks, rocksX,rocksY, null);
            rocksX+=40;
            if(rocksX>1200) {
                rocksX=0;
                rocksY+=40;
            }
        }
        
    }
    public void drawStoneTiles2(Graphics g) {
        int rocksX = 0;
        int rocksY = 0;
        while(rocksY<600) {
            g.drawImage(rocks2, rocksX,rocksY, null);
            rocksX+=40;
            if(rocksX>1200) {
                rocksX=0;
                rocksY+=40;
            }
        }
        
    }
    public void drawStart(Graphics g) {
        int saferocksX = 0;
        int saferocksY = 0;
        while(saferocksY<600) {
            g.drawImage(saferocks, saferocksX, saferocksY, null);
            saferocksX+=40;
            if(saferocksX>160) {
                saferocksX = 0;
                saferocksY +=40;
            }
        }
        
    }
    public void drawStart2(Graphics g) {
        int saferocksX = 0;
        int saferocksY = 0;
        while(saferocksY<600) {
            g.drawImage(saferocks2, saferocksX, saferocksY, null);
            saferocksX+=40;
            if(saferocksX>160) {
                saferocksX = 0;
                saferocksY +=40;
            }
        }
        
    }
    public void drawEnd(Graphics g){
        int saferocksX = 1000;
        int saferocksY = 0;
        while(saferocksY<600) {
            g.drawImage(saferocks, saferocksX, saferocksY, null);
            saferocksX+=40;
            if(saferocksX>2000) {
                saferocksX = 1000;
                saferocksY +=40;
            }
        }
    }
    public void drawEnd2(Graphics g){
        int saferocksX = 1000;
        int saferocksY = 0;
        while(saferocksY<600) {
            g.drawImage(saferocks2, saferocksX, saferocksY, null);
            saferocksX+=40;
            if(saferocksX>2000) {
                saferocksX = 1000;
                saferocksY +=40;
            }
        }
    }
    public void drawVerticalGrid(Graphics g, int x, int y){
        while(x<=1200) {
            g.setColor(black);
            g.fillRect(x,y,2,600);
            x +=40;
        }
    }
    public void drawHorizontalGrid(Graphics g, int x, int y){
    
        while(y<=600){
            g.setColor(black);
            g.fillRect(x,y,1200,2);
            y +=40;
        }
    }
}