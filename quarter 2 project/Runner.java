import javax.swing.JFrame;

public class Runner {
	public static void main(String[] args) {


		JFrame frame = new JFrame("The Floor is Lava");


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//Create panel and add it to the frame
		Screen sc = new Screen();
		
		sc.levels();
		frame.add(sc);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		sc.animate();
    }
}
