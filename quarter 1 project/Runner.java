
import javax.swing.JFrame;
import java.util.Scanner;
public class Runner {

  public static void main( String args[] ) {
    JFrame fr = new JFrame("Quarter 1 Project - My Scenery");
    Scanner scan = new Scanner(System.in); 

    System.out.println("Enter in an integer: 1) Day 2) Night");
    int background = scan.nextInt();

    System.out.println("Enter in an integer: 1) Sunny 2) Cloudy 3) Rainy");
    int weather = scan.nextInt();

    Scenery sc = new Scenery(background, weather);
    fr.add(sc);
    
    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fr.pack();
    fr.setVisible(true);
  }
}
