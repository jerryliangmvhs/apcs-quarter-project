import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Table extends JPanel implements ActionListener, MouseListener, KeyListener {

	private JButton switchPlayerButton;
	private JButton startButton;
	private JButton beginGame;
	private JButton skipTurn;
	private JButton returnHome;
	private JButton howToPlay;

	private int stage = 0; //stage 0 is start screen, stage 1 is game, stage 2 is end game screen.
	private CardGame cardGame;
	private Color darkRed = new Color(160,0,0);
	private Color lightRed = new Color(250,90,90);


	public Table() {

			cardGame = new CardGame();
			//setup buttons
			setLayout(null);


			switchPlayerButton = new JButton("Place Card(s)"); //drawing cards/a card will automatically switch to the next player
			switchPlayerButton.setBounds(600,300,200,30); //x,y,width,height
			switchPlayerButton.addActionListener(this);
			add(switchPlayerButton);
			switchPlayerButton.setVisible(false);

			startButton = new JButton("Start"); //for confirming switching players. Not to be confused with the button for starting the game.
			startButton.setBounds(600,300,200,30);
			startButton.addActionListener(this);
			add(startButton);
			startButton.setVisible(false);

			beginGame = new JButton("New Game");
			beginGame.setBounds(250,400,300,60);
			beginGame.setFont(new Font("Arial", Font.BOLD, 30));
			beginGame.setBackground(lightRed);
			beginGame.setOpaque(true);
			beginGame.setBorderPainted(false);
			beginGame.addActionListener(this);
			add(beginGame);
			beginGame.setVisible(true);

			howToPlay = new JButton("How to Play");
			howToPlay.setBounds(250,495,300,60);
			howToPlay.setFont(new Font("Arial", Font.BOLD, 30));
			howToPlay.setBackground(lightRed);
			howToPlay.setOpaque(true);
			howToPlay.setBorderPainted(false);
			howToPlay.addActionListener(this);
			add(howToPlay);
			howToPlay.setVisible(true);

			skipTurn = new JButton("Skip"); //for players who want to skip or don't have a matching card.
			skipTurn.setBounds(25,300,200,30);
			skipTurn.addActionListener(this);
			add(skipTurn);
			skipTurn.setVisible(false);

			returnHome = new JButton("Back to Main Screen");
			returnHome.setBounds(250,495,300,60);
			returnHome.addActionListener(this);
			add(returnHome);
			returnHome.setVisible(false);
			
			addMouseListener(this);
			addKeyListener(this);
			setFocusable(true);
	}
	
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(800,600);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(stage ==0){
			g.setColor(darkRed); //replace with image later
			g.fillRect(0,0,800,600);
			cardGame.drawTitleScreen(g);
			skipTurn.setVisible(false);

		}
		if(stage ==1){
			cardGame.drawGame(g);
			beginGame.setVisible(false);
			howToPlay.setVisible(false);
			cardGame.showHowToPlay(false);
			returnHome.setVisible(false);
			if(cardGame.getPlayer1CardAmount()<=2){
				g.setColor(Color.WHITE);
				Font cardIndicator = new Font("Arial",Font.PLAIN,15);
				g.drawString("Player 1 only has " + cardGame.getPlayer1CardAmount() + " card(s) left!!!",50,580);
			}
			if(cardGame.getPlayer2CardAmount()<=2){
				g.setColor(Color.WHITE);
				Font cardIndicator = new Font("Arial",Font.PLAIN,15);
				g.drawString("Player 2 only has " + cardGame.getPlayer2CardAmount() + " card(s) left!!!",400,580);
			}
		}
		if(stage ==2){
			cardGame.drawEndScreen(g);
			this.displayWinner(g);
			startButton.setVisible(false);
		}
	}
	public void placeCardSound(){
		try {
            URL url = this.getClass().getClassLoader().getResource("placeCard.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
	}
	public void skipTurnSound(){
		try {
            URL url = this.getClass().getClassLoader().getResource("skip.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
	}
	public void rocketSound(){
		try {
            URL url = this.getClass().getClassLoader().getResource("rocket.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
	}
	public void bombSound(){
		try {
            URL url = this.getClass().getClassLoader().getResource("bomb.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
	}
	public void displayWinner(Graphics g){
		returnHome.setVisible(true);
		Font winner = new Font("Arial",Font.BOLD,40);
		g.setFont(winner);
		g.setColor(Color.WHITE);
		
		
		if(cardGame.getPlayer1CardAmount()==0){
			g.drawString("Player 1 Wins!",300,160);
		}
		else if(cardGame.getPlayer2CardAmount()==0){
			g.drawString("Player 2 Wins!",300,160);
		}
		else{
			g.drawString("Nobody Won!",300,160);
		}
		
	}




	public void actionPerformed(ActionEvent e) {
		
			if(e.getSource()==beginGame){
				cardGame = new CardGame();
				stage = 1;
				beginGame.setVisible(false);
				howToPlay.setVisible(false);
				switchPlayerButton.setVisible(false);
				skipTurn.setVisible(true);
				cardGame.organizeCards();
			}

			if(e.getSource() == switchPlayerButton){
				//display blank screen
				switchPlayerButton.setVisible(false); //doesn't allow player to draw any more cards after making a move
				cardGame.updateMinimumRequiredValue();
				cardGame.updateRequiredCombination();
				if(cardGame.getSelectedCombination().equals("rocket")){
					this.rocketSound();
					//cardGame.switchPlayer(); //adding an extra switchPlayer here so it then the switchPlayer method declared later will switch the player back. This is because the person who draws the rocket forces the other player to skip their turn.
				}
				if(cardGame.getSelectedCombination().equals("bomb")){
					this.bombSound();
				}
				
				cardGame.setBlankScreen(true);
				this.placeCardSound();
				startButton.setVisible(true);

				cardGame.placeCards();

				System.out.println("Required Combination: " + cardGame.getRequiredCombination());
				cardGame.switchPlayer();
				cardGame.clearSelectedCombination();
				skipTurn.setVisible(false);
				if(cardGame.getPlayer1CardAmount()==0 || cardGame.getPlayer2CardAmount()==0){
					stage = 2;
					repaint();
				}
				
			}
			if(e.getSource() == startButton){
				cardGame.setBlankScreen(false);
				startButton.setVisible(false);
				switchPlayerButton.setVisible(false);
				skipTurn.setVisible(true);
			}
			if(e.getSource() == skipTurn){
				this.skipTurnSound();
				skipTurn.setVisible(false);
				cardGame.setBlankScreen(true);
				cardGame.reset();
				switchPlayerButton.setVisible(false);
				startButton.setVisible(true);
				cardGame.switchPlayer();
			}
			if(e.getSource() == returnHome){
				stage = 0;
				beginGame.setVisible(true);
				howToPlay.setVisible(true);
				returnHome.setVisible(false);
				cardGame.showHowToPlay(false);
			}
			if(e.getSource() == howToPlay){
				cardGame.showHowToPlay(true);
				howToPlay.setVisible(false);
				returnHome.setVisible(true);
				beginGame.setVisible(false);
			}
		
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		if(stage ==1){
			cardGame.selectP1Cards(e.getX(), e.getY());
			cardGame.selectP2Cards(e.getX(),e.getY());
			String result = cardGame.checkSequence();
			if(result.equals("")){
				result = cardGame.checkRocket();
				if(result.equals("")){
					result = cardGame.checkBomb();
					if(result.equals("")){
						result = cardGame.checkTriplet();
						if(result.equals("")){
							result = cardGame.checkPair();
							if(result.equals("")){
								result = cardGame.checkSingle();
							}
						}
					}
				}
			}
			
			if(cardGame.getP1MinimumSelectedValue() > cardGame.getMinimumRequiredValue() || cardGame.getP2MinimumSelectedValue() > cardGame.getMinimumRequiredValue()){
				if(result.equals(cardGame.getRequiredCombination()) || cardGame.getRequiredCombination().equals("") || result.equals("rocket")){
					if(result.equals("")==false ){ //if there is a valid combination selected
						if(cardGame.getRequiredCombination().equals("") || result.equals(cardGame.getRequiredCombination()) ||result.equals("rocket")){
							if(cardGame.getP1SelectedCardAmount() >0 || cardGame.getP2SelectedCardAmount() >0){
								switchPlayerButton.setVisible(true);
							}
						}
					}
					if(result.equals("") || result.equals(cardGame.getSelectedCombination())==false){ //if there's no valid combination selected
						switchPlayerButton.setVisible(false);
					}
					if(result.equals("rocket")){
						switchPlayerButton.setVisible(true);
					}
				}
			}
			//else if means it only executes if the previous if statements are false
			else if(result.equals("") || result.equals(cardGame.getRequiredCombination())==false || cardGame.getP1MinimumSelectedValue() <= cardGame.getMinimumRequiredValue() || cardGame.getP2MinimumSelectedValue() <= cardGame.getMinimumRequiredValue()){
				if(result.equals("rocket")==false){
					switchPlayerButton.setVisible(false);
				}
			}
			System.out.println("P1 Minimum Selected Value: " + cardGame.getP1MinimumSelectedValue());
			System.out.println("Required Value: " + cardGame.getMinimumRequiredValue());
			System.out.println("Selected Combination: " + result);
			System.out.println("Required Combination: " + cardGame.getRequiredCombination());
			repaint();
		}
		
		
		/*hides the place card button if there's no valid combination selected
		else if(cardGame.getP1MinimumSelectedValue() <= cardGame.getMinimumRequiredValue() || cardGame.getP2MinimumSelectedValue() <= cardGame.getMinimumRequiredValue()){
			if(result.equals(cardGame.getRequiredCombination())==false || cardGame.getRequiredCombination().equals("")==false){
				switchPlayerButton.setVisible(false);
			}
		}
			*/
		
		
		repaint();
    }


    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

	public void keyPressed(KeyEvent e){
		//System.out.println(e.getKeyCode());
		if(e.getKeyCode()==79){
			if(stage ==0){
				cardGame = new CardGame();
				stage=1;
				beginGame.setVisible(false);
				howToPlay.setVisible(false);
				switchPlayerButton.setVisible(false);
				skipTurn.setVisible(true);
				cardGame.organizeCards();
				
			}
			else if(stage==1){
				stage=2;
				skipTurn.setVisible(false);
				switchPlayerButton.setVisible(false);
				
			}
			else if(stage ==2){
				stage =0;
				beginGame.setVisible(true);
				howToPlay.setVisible(true);
				returnHome.setVisible(false);
			}
		}
		repaint();
	}
	public void keyReleased(KeyEvent e){

	}
	public void keyTyped(KeyEvent e){

	}
	public void test(){
		while(true){
			System.out.println(cardGame.getSelectedCombination());
		}
	}


	
	
}