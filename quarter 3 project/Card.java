import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {

   private int value;
   private String name;
   private String suit;
   private BufferedImage hearts; 
   private BufferedImage diamonds;
   private BufferedImage clubs;
   private BufferedImage spades;
   private BufferedImage small_joker;
   private BufferedImage big_joker;
   private boolean selected = false;

   public Card(int value, String name, String suit){
		this.value = value;
		this.name = name;
		this.suit = suit;
		if( suit.equals("hearts") ){
			try{
				hearts = ImageIO.read(new File("hearts.png"));
			} catch (IOException e) {}
		}
      if(suit.equals("diamonds")){
         try{
				diamonds = ImageIO.read(new File("diamonds.png"));
			} catch (IOException e) {}
      }
      if(suit.equals("clubs")){
         try{
				clubs = ImageIO.read(new File("clubs.png"));
			} catch (IOException e) {}
      }
      if(suit.equals("spades")){
         try{
				spades = ImageIO.read(new File("spades.png"));
			} catch (IOException e) {}
      }
      if(suit.equals("Little Joker")){
         try{
            small_joker = ImageIO.read(new File("grayscale_joker.png"));
         } catch (IOException e) {}
      }
      if(suit.equals("Big Joker")){
         try{
            big_joker = ImageIO.read(new File("joker.png"));
         } catch (IOException e) {}
      }
	 }

	 public int getValue(){
      return this.value;
   }


	 public void drawMe(Graphics g, int x, int y){
      g.setColor(Color.WHITE);
      if(selected == false){
         g.fillRect(x,y,90,150);
         g.setColor(Color.black);      
         g.drawRect(x,y,90,150);
         g.drawImage(hearts, x+2, y+2, 30,35,null);
         g.drawImage(spades, x+2, y+2, 30,35,null);
         g.drawImage(clubs, x+2, y+2, 30,35,null);
         g.drawImage(diamonds, x+2, y+2, 30,35,null);
         g.drawImage(small_joker,x+2,y+2,30,35,null);
         g.drawImage(big_joker,x+2,y+2,30,35,null);
         if( this.suit.equals("hearts") || this.suit.equals("diamonds")){
            g.setColor(Color.RED);
            Font font = new Font("Arial", Font.PLAIN, 20);
            g.setFont(font);
         }
         if(this.suit.equals("clubs") || this.suit.equals("spades")){
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.PLAIN, 20);
            g.setFont(font);
         }
         if(this.suit.equals("Big Joker")){
            g.setColor(Color.RED);
            Font font = new Font("Arial", Font.PLAIN, 10);
            g.setFont(font);
         }
         if(this.suit.equals("Little Joker")){
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.PLAIN, 10);
            g.setFont(font);
         }
         g.drawString(this.name+"", x+5, y + 55);
      }
      if(selected == true){
         g.fillRect(x,y-10,90,150);
         g.setColor(Color.black);      
         g.drawRect(x,y-10,90,150);
         g.drawImage(hearts, x+2, y-8, 30,35,null);
         g.drawImage(spades, x+2, y-8, 30,35,null);
         g.drawImage(clubs, x+2, y-8, 30,35,null);
         g.drawImage(diamonds, x+2, y-8, 30,35,null);
         g.drawImage(small_joker,x+2,y-8,30,35,null);
         g.drawImage(big_joker,x+2,y-8,30,35,null);
         if( this.suit.equals("hearts") || this.suit.equals("diamonds")){
            g.setColor(Color.RED);
            Font font = new Font("Arial", Font.PLAIN, 20);
            g.setFont(font);
         }
         if(this.suit.equals("clubs") || this.suit.equals("spades")){
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.PLAIN, 20);
            g.setFont(font);
         }
         if(this.suit.equals("Big Joker")){
            g.setColor(Color.RED);
            Font font = new Font("Arial", Font.PLAIN, 10);
            g.setFont(font);
         }
         if(this.suit.equals("Little Joker")){
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.PLAIN, 10);
            g.setFont(font);
         }
         g.drawString(this.name+"", x+5, y + 45);
      }      
   }


   public String toString(){
      return name + " " + suit + ":" + value;
   }
   public void highLight(){
      if(selected ==true){
         selected = false;
      }
      else{
         selected = true;
      }
      
   }
   public boolean getSelected(){
      return selected;
   }
   
}