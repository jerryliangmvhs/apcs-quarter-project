import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Screen extends JPanel implements KeyListener, MouseListener, ActionListener {

	
	private Sprite sprite;
	private int moveAmount;
	private BufferedImage gameBackground;
	private BufferedImage endBackground;
	private BufferedImage instructionsBackground;
	private Confetti[] confetti;
	private boolean showHowToPlay = false;
	private BoardGame boardGame;
	private Intro introScreen;

	Font small;
	Font medium = new Font("Arial", Font.PLAIN,30);
	Font large;
	Font extraLarge;
	Font button;

	private int stage; //different phases of the game, stage 0 is start menu etc.
	private JButton play;
	private JButton howToPlay;
	private JButton returnHome;
	private JButton rollDice;
	private JButton Continue;

	private Color[] color;



	public Screen(){
		this.setLayout(null);

		color = new Color[6];
		color[0] = Color.RED;
		color[1] = Color.ORANGE;
		color[2] = Color.CYAN;
		color[3] = Color.GREEN;
		color[4] = Color.BLUE;
		color[5] = Color.MAGENTA;	

		confetti = new Confetti[30];
		boardGame = new BoardGame();
		introScreen = new Intro();


		for(int i=0; i<confetti.length; i++){
			int x = (int)(Math.random()*750);
			int y = (int)(Math.random()*50);
			int colorIndex = (int)(Math.random()*6);
			confetti[i] = new Confetti(x, y,color[colorIndex]);
		}
		
		try{
			large = Font.createFont(Font.TRUETYPE_FONT,new File("pixelFont.ttf")).deriveFont(45f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(large);

			extraLarge = Font.createFont(Font.TRUETYPE_FONT,new File("pixelFont.ttf")).deriveFont(60f);
			GraphicsEnvironment gf = GraphicsEnvironment.getLocalGraphicsEnvironment();
			gf.registerFont(extraLarge);

			button = Font.createFont(Font.TRUETYPE_FONT,new File("pixelFont2.ttf")).deriveFont(25f);
			GraphicsEnvironment gg = GraphicsEnvironment.getLocalGraphicsEnvironment();
			gg.registerFont(button);

			small = Font.createFont(Font.TRUETYPE_FONT,new File("pixelFont2.ttf")).deriveFont(20f);
			GraphicsEnvironment gh = GraphicsEnvironment.getLocalGraphicsEnvironment();
			gh.registerFont(small);
			
            gameBackground = ImageIO.read(new File("gameBackground.png"));
			endBackground = ImageIO.read(new File("endBackground.png"));
			instructionsBackground = ImageIO.read(new File("instructionsBackground.png"));

    
			} catch (IOException | FontFormatException e){
			e.printStackTrace();
		}

		play = new JButton("Play");
		play.setBounds(450,300,300,80);
		play.addActionListener(this);
		play.setFont(button);
		this.add(play);

		howToPlay = new JButton("How to Play");
		howToPlay.setBounds(450,400,300,80);
		howToPlay.addActionListener(this);
		howToPlay.setFont(button);
		this.add(howToPlay);

		returnHome = new JButton("Return Home");
		returnHome.setBounds(250,550,300,80);
		returnHome.addActionListener(this);
		returnHome.setVisible(false);
		returnHome.setFont(button);
		this.add(returnHome);

		rollDice = new JButton("Roll Dice");
		rollDice.setBounds(250,600,300,50);
		rollDice.addActionListener(this);
		rollDice.setVisible(false);
		rollDice.setFont(button);
		rollDice.setFocusable(false);
		this.add(rollDice);

		Continue = new JButton("Continue");
		Continue.setBounds(250,600,300,50);
		Continue.addActionListener(this);
		Continue.setVisible(false);
		Continue.setFont(button);
		Continue.setFocusable(false);
		this.add(Continue);



		this.setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(800,700);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(stage == 0){
			//start menu
			if(showHowToPlay){
				g.setFont(large);
				g.setColor(Color.WHITE);
				g.drawImage(instructionsBackground,0,0,800,700,null);
				g.drawString("How to Play",275,100);
				g.setFont(small);
				g.drawString("In this game, you will go through different stages of life",100,250);
				g.drawString("Roll the dice to move on the board",100,300);
				g.drawString("Each time you move, you draw a card",100,350);
				g.drawString("During the game, you'll earn money and life tiles", 100,400);
				g.drawString("Near the end of the board, you earn milestones like buying a house and getting married",100,450);
				g.drawString("Whoever has the most money and life tiles combined wins!",100,500);

			}
			if(showHowToPlay==false && stage == 0){
				g.drawImage(gameBackground,0,0,800,700,null);
				g.setColor(Color.WHITE);
				g.fillRect(400,0,800,700);
				g.setColor(Color.BLACK);
				g.setFont(extraLarge);
				g.drawString("The Game",450,100);
				g.drawString("Of Life",500,175);
			}
		}
		if(stage == 1){
			g.drawImage(gameBackground,0,0,800,700,null);
			g.setFont(small);
			g.drawString("P1 Money: " +boardGame.getP1Money()+ " life money",10,630);
			g.drawString("P1 LifeTiles: " + boardGame.getP1LifeTiles()+ " life tiles",10,650);
			g.drawString("P2 Money: " +boardGame.getP2Money()+ " life money",600,630);
			g.drawString("P2 LifeTiles: " + boardGame.getP2LifeTiles()+ " life tiles",600,650);


			showHowToPlay = false;
			play.setVisible(false);
			howToPlay.setVisible(false);
			returnHome.setVisible(false);
			boardGame.drawGame(g);
			if(boardGame.isDisplayingCards()==true){
				rollDice.setVisible(false);
			}
			else if(boardGame.isDisplayingCards()==false){
				rollDice.setVisible(true);
			}

			
			
		}
		if(stage == 2){
			//determines and displays the winner
			g.drawImage(endBackground,0,0,800,700,null);
			g.setFont(extraLarge);
			returnHome.setVisible(true);
			for(int i=0; i<confetti.length; i++){
				confetti[i].drawMe(g);
			}
			g.setColor(Color.BLACK);
			if(boardGame.getP1Score()>boardGame.getP2Score()){
				g.drawString("Player 1 Wins!",175,100);
				g.setFont(small);
				g.drawString("Player 1 won with " + boardGame.getP1Score() + " points!",325,125);
			}
			else if(boardGame.getP1Score()<boardGame.getP2Score()){
				g.drawString("Player 2 Wins!",175,100);
				g.setFont(small);
				g.drawString("Player 2 won with " + boardGame.getP2Score() + " points!",325,125);
			}
			else if(boardGame.getP1Score()==boardGame.getP2Score()){
				g.drawString("Draw!",325,100);
				g.setFont(small);
				g.drawString("Wow! Both of you scored the same!",290,125);
			}
		}
		introScreen.drawMe(g);
		if(introScreen.visible()==true){
			play.setVisible(false);
			howToPlay.setVisible(false);
		}
		if(introScreen.visible()==false && stage ==0 && showHowToPlay ==false){
			play.setVisible(true);
			howToPlay.setVisible(true);
		}
	}
	public void clickSound(){
		try {
            URL url = this.getClass().getClassLoader().getResource("click.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }

	}
	public void endSound(){
		try {
            URL url = this.getClass().getClassLoader().getResource("tada.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
	}
	public void update(){
		//for animations etc.
		while(true){
			introScreen.updateProgressBar();
			if(stage ==2){
				for(int i=0; i<confetti.length; i++){
					confetti[i].animateConfetti();
				}
			}

			try {
				Thread.sleep(5); 
				} catch (InterruptedException e) {
				System.out.println(e);
			}

			repaint();
		}
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==112){
			boardGame.reset();
			boardGame.hideCards();
			if(stage <=2){
				stage++;
				if(stage ==2){
					rollDice.setVisible(false);
					Continue.setVisible(false);
					this.endSound();
				}
			}
			if(stage ==3){
				stage = 0;
				play.setVisible(true);
				howToPlay.setVisible(true);
				rollDice.setVisible(false);
				returnHome.setVisible(false);
				Continue.setVisible(false);
			}
			repaint();
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == play){
			this.clickSound();
			showHowToPlay = false;
			play.setVisible(false);
			howToPlay.setVisible(false);
			rollDice.setVisible(true);
			stage = 1;
			boardGame.reset();
			boardGame.hideCards();
			repaint();
		}
		if(e.getSource() == howToPlay){
			showHowToPlay = true;
			this.clickSound();
			howToPlay.setVisible(false);
			play.setVisible(false);
			returnHome.setVisible(true);
			repaint();
		}
		if(e.getSource() == returnHome){
			stage = 0;
			showHowToPlay = false;
			this.clickSound();
			returnHome.setVisible(false);
			play.setVisible(true);
			howToPlay.setVisible(true);
			repaint();
		}
		if(e.getSource() == Continue){
			boardGame.hideCards();
			this.clickSound();
			Continue.setVisible(false);
			rollDice.setVisible(true);
		}
		if(e.getSource() == rollDice){
			this.clickSound();
			moveAmount = (int)(Math.random()*6)+1;
			//System.out.println("Dice: " + moveAmount);
			if(boardGame.getTurn() ==1){
				boardGame.movePlayer1(moveAmount);
			}
			if(boardGame.getTurn() ==2){
				boardGame.movePlayer2(moveAmount);
			}
			boardGame.switchPlayer();
			if(boardGame.isStillPlaying()==false){
				stage++;
				this.endSound();
				rollDice.setVisible(false);
			}
			boardGame.drawCard();
			if(stage ==1){
				Continue.setVisible(true);
			}
			boardGame.updateScore();
			repaint();
		}
	}
}
