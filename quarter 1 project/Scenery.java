import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class Scenery extends JPanel {
  private int background;
  private int weather;

  public Scenery(int background, int weather) {
    this.background = background;
    this.weather = weather;
  }
  @Override
  
  public Dimension getPreferredSize() {
    return new Dimension(600,800);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    

    if(background == 1) {
      Color lightBlue = new Color(108,206,209);
      g.setColor(lightBlue);
      g.fillRect(0,0,600,800);
      if(weather == 1) {
        Color colorYellow = new Color(255,255,0);
        g.setColor(colorYellow);
        g.fillOval(30,30,75,75);
      }
    }
    
    if(background == 2) {
      Color darkBlue = new Color(0,10,104);
      g.setColor(darkBlue);
      g.fillRect(0,0,600,800);
      if(weather == 1) {
        Color colorWhite = new Color(255,255,255);
        g.setColor(colorWhite);
        g.fillOval(30,30,75,75);
      }
    }
    if(weather ==2) {
      Color white = new Color(255,255,255);
      g.setColor(white);
      g.fillOval(300,30,150,75);
      g.fillOval(275,50,150,70);
      g.fillOval(100,40,150,60);
      g.fillOval(100,30,90,60);
      g.fillOval(0,40,70,50);
    }
    if(weather ==3) {
      Color gray = new Color(128,128,128);
      g.setColor(gray);
      g.fillOval(300,30,150,75);
      g.fillOval(275,50,150,70);
      g.fillOval(100,40,150,60);
      g.fillOval(100,30,90,60);
      g.fillOval(0,40,70,50);
      Graphics2D g2 = (Graphics2D) g;
      g2.setStroke(new BasicStroke(3));
      Color blue = new Color(0,0,255);
      g.setColor(blue);
      g2.drawLine(25,100,30,500);
      g2.drawLine(80,100,90,500);
      g2.drawLine(130,100,140,500);
      g2.drawLine(310,150,320,500);
      g2.drawLine(400,150,410,500);
    }
    drawTowerGray(g, 100, 200);
    drawTowerBlack(g, 200, 250);
    drawTowerLidGray(g, 125, 150);
    drawTowerLidBlack(g,200,200);
    drawTowerBlack(g,0,250);
    drawTowerLidBlack(g,0,200);
    drawTowerGray(g,300,200);
    drawTowerLidGray(g,325,150);
    drawTowerBlack(g,400,250);
    drawTowerLidBlack(g,400,200);
    drawTowerGray(g,500,200);
    drawTowerLidGray(g,525,150);
    drawWater(g,0,400);
    drawBoat(g,400,450,500,450,475,475,425,475);
    drawBoat(g,200,375,300,375,275,400,225,400);
    drawIsland(g,50,450);
    drawIsland(g,50,425);
    drawIsland(g,450,525);
    drawIsland(g,450,500);
    drawBridge(g);
    drawBoat(g,300,600,400,600,375,625,325,625);
    Font font = new Font("Arial", Font.PLAIN, 20);
    g.setFont(font);
    g.setColor(Color.black);
    g.drawString("Rainbow Bridge, Tokyo, Japan", 25, 650);
    
    
  }
  //Foreground object 1
  public void drawTowerGray(Graphics g, int x, int y) {
    Color gray = new Color(90,90,90);
    g.setColor(gray);
    g.fillRect(x,y,100,250);
  }
  //Foreground object 2
  public void drawTowerBlack(Graphics g, int x, int y) {
    Color black = new Color(0,0,0);
    g.setColor(black);
    g.fillRect(x,y,100,200);
  }
  public void drawTowerLidGray(Graphics g, int x, int y) {
    Color gray = new Color(90,90,90);
    g.setColor(gray);
    g.fillRect(x,y,50,75);
  }
  public void drawTowerLidBlack(Graphics g, int x, int y) {
    Color black = new Color(0,0,0);
    g.setColor(black);
    g.fillOval(x,y,100,100);
  }
  public void drawWater(Graphics g, int x, int y) {
    Color blue = new Color(0,0,255);
    g.setColor(blue);
    g.fillRect(x,y,600,300);
  }
  //Foreground object 3
  public void drawBridge(Graphics g) {
    //The bridge is supposed to be a gradient, but I wasn't able to figure out how to do that, so I made a simple primary color scheme.
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(6));
    Color white = new Color(255, 255, 255);
    g.setColor(white);
    g2.drawLine(0,550,600,400);
    g2.drawLine(0,550,100,350);
    g2.drawLine(150,350,300,475);
    g2.drawLine(300,475,500,250);
    g2.drawLine(550,250,600,400);
    Color dark_blue = new Color(0,0,160);
    g.setColor(dark_blue);
    g.fillRect(100,500,50,100);
    g.fillRect(500,400,50,100);
    Color yellow = new Color(255,255,0);
    g.setColor(yellow);
    g.fillRect(100,400,50,100);
    g.fillRect(500,300,50,100);
    Color red = new Color(255,0,0);
    g.setColor(red);
    g.fillRect(100,350,50,50);
    g.fillRect(500,250,50,50);
  
  }
  //Foreground object 4
  public void drawIsland(Graphics g, int x, int y) {
    Color green = new Color(0,160,60);
    g.setColor(green);
    g.fillOval(x,y,200,75);
  }
  //Foreground object 5
  public void drawBoat(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3) {
    int[] xArray = new int[4];
    int[] yArray = new int[4];

    xArray[0] = x0;
    yArray[0] = y0;

    xArray[1] = x1;
    yArray[1] = y1;

    xArray[2] = x2;
    yArray[2] = y2;

    xArray[3] = x3;
    yArray[3] = y3;

    Color Brown = new Color(139, 69, 19);
    g.setColor(Brown);

    g.fillPolygon(xArray,yArray,4);
  }
  

  
  
}
