import javax.swing.JFrame;
public class Runner {
	public static void main(String args[]) {
		Table game = new Table();
		JFrame frame = new JFrame("Fight the Landlord");
		
		frame.add(game);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		//game.test();
	}
}