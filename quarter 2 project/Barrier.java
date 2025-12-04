import java.awt.*;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Barrier {
    private int x;
    private int y;
    private int width = 40;
    private int height = 600;
    private Color brown = new Color(120,50,0);
    private Color darkBrown = new Color(80,40,0);
    public Barrier(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void drawMe(Graphics g) {
        g.setColor(brown);
        g.fillRect(x,y,width,height);
        g.fillRect(x+280,y+500,width,height);

        g.setColor(darkBrown);
        g.fillRect(x+10,y,10,height);
        g.fillRect(x+30,y,10,height);
        g.fillRect(x+290,y+500,10,height);
        g.fillRect(x+310,y+500,10,height);


    }
    public void move() {
        y+=20;
        if(y>1000) {
            y = -600; //once the barrier moves off the screen, have it start back at the top but off the screen as it slowly goes down
            this.barrierSound();
        }
        }
        public int getWidth(){
            return width;
        }
        public int getHeight() {
            return height;
        }
        public int getBarrierX1() {
            return x;
        }
        public int getBarrierY1() {
            return y;
        }
        public int getBarrierX2() {
            return x+280;
        }
        public int getBarrierY2() {
            return y+500;
        }
    public void barrierSound() {
        try {
			URL url = this.getClass().getClassLoader().getResource("barrier.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
    }
}
