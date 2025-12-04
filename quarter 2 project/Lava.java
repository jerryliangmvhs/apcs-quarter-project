import java.awt.*;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Lava {
    private int x;
    private int y;
    private int particleX;
    private int particleY;
    private Color orange = new Color(255,128,0);
    private Color orange2 = new Color(240,110,0);
    private int width;
    private int height;
    private int particleDirection = 1;
    private boolean visible = false;
    
    public Lava(int x, int y) {
        this.x = x;
        this.y = y;
        this.particleX = x+5;
        this.particleY = y+5;
        this.width = 40;
        this.height = 40;
    }
    public void drawLavaTiles(Graphics g) {
        if (visible == true) {
            g.setColor(orange);
            g.fillRect(x,y,width,height);
        }
    }
    public void drawLavaParticles(Graphics g) {
        if (visible == true) {
            g.setColor(orange2);
            g.fillOval(particleX+25, particleY+25,5,5);
            g.fillOval(particleX, particleY,5,5);
            g.fillOval(particleX+10,particleY+10,5,5);
            g.fillOval(particleX+15,particleY+5,5,5);
            g.fillOval(particleX,particleY+20,5,5);
        }
    }
    public void moveParticles() { //for use of animating lava: makes use of random movements of the particles
        particleDirection = (int)(Math.random()*20);
        if(particleDirection ==0) {
            particleX = x+5; //resets the position of the particle so that they don't move out of the lava tile
            particleY = y+5;
        }
        if(particleDirection ==1) {
            particleX++;
        }
        else if(particleDirection ==2) {
            particleX--;
        }
        else if (particleDirection ==3) {
            particleY++;
        }
        else if(particleDirection ==4) {
            particleY--;
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
    public void lavaSound() {
        try {
			URL url = this.getClass().getClassLoader().getResource("lava.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
        try {
			URL url = this.getClass().getClassLoader().getResource("lavaPop.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
    }
}