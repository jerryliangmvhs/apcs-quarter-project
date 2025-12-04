import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//to do list: change texture of the rocks for level 2, add moving barriers, add background music


public class Screen extends JPanel implements KeyListener, ActionListener {
	//implements KeyListener, ActionListener

    //work on adding a button
	private Player player;
	private Map map = new Map();
	private Lava[] lava;
	private Coins[] coins;
	private int level = 0;
	private int points = 0;
	Color blue = new Color(0,0,255);
	Color green = new Color(0,255,0);
	Color red = new Color(255,0,0);
	Color transRed = new Color (1.0f,0f,0f,0.5f);
	Color yellow = new Color(255,255,0);
	Color black = new Color(0,0,0);
	Color transBlack = new Color(0.0f,0.0f,0.0f,0.5f);
	Color purple = new Color (255,0,255);
	Color characterPurple = new Color(100,0,255);
	Color orange = new Color (255,128,0);
	Color white = new Color(255,255,255);
	Color lightGray = new Color(200,200,200);
    private Color yellow2 = new Color(255,200,0);
	private int count = 0;
	private int lavaIndex = 0;
	private JButton play;
	private BufferedImage lavaScreen;
	private BufferedImage Title;
	private win win;
	private BufferedImage end;
	private BufferedImage finishFlag;
	private Barrier barrier;
	private Treasure treasure;
	private BufferedImage javaEdition;
	private BufferedImage Chest;
	private boolean diedToLava = false;
	private boolean diedToBarrier = false;
	
	public Screen() {
		//code to set up and instantiate the JButton
		this.setLayout(null);
		play = new JButton("PLAY");
		play.setFont(new Font("Phosphate-Inline", Font.BOLD, 50));
		play.setBackground(orange);
		play.setOpaque(true);
		play.setBorderPainted(false);
		
		play.setBounds(350, 400, 500, 100); //sets the location and size
		this.add(play); //add to JPanel
		play.setFocusable(false);
        play.addActionListener(this); //add the listener
        

		addKeyListener( this );
		setFocusable(true);

		//instantiating all the game's images.
		//blurred out lava screen you see in the start menu
		try {
            lavaScreen = ImageIO.read(new File("lava.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		//image for the game's title "The Floor is Lava"
		try {
            Title = ImageIO.read(new File("GameTitle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		//image for the victory background (blurred out image of Stanford University Campus)
		try {
            end = ImageIO.read(new File("EndBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		//finish flag for in the game so the player knows where to go
		try {
            finishFlag = ImageIO.read(new File("finishFlag.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		//this goes below the game's title to show the player what coding language was used.
		//used primarily as decoration.
		try {
            javaEdition = ImageIO.read(new File("javaEdition.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

		//this is to show the chest icon at the start screen. The actual chest seen when playing the game is through the treasure class.
		try {
            Chest = ImageIO.read(new File("closedChest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

		

	}
	public void levels() {
		if(level >= 3 && diedToLava == false) { //there is no level 3. This is only here so that the player gets sent out of the area where the winning sound would play.
			//if there was no code to teleport the player back at the end of the game, the level up sound would play indefinitely.
			level = 3;
			player = new Player(81,281); //this code is only used to move the player out of area where the win wound would play, otherwise it would play continously.
			win = new win(0,400);
		}
		else if(level == 2) {
			player = new Player(81,281);
			coins = new Coins[50];
			for(int i=0; i < coins.length; i++) {
				int x = 40*(int)(Math.random()*20+5)+10;
				int y = 40*(int)(Math.random()*15)+10;
				coins[i] = new Coins(x,y);
			}
			//a lava tile will be a 40x40
			

			lava = new Lava[450]; 
			for(int i=0; i<lava.length; i++) {
				int x = 40*(int)(Math.random()*20+5);
				int y = 40*(int)(Math.random()*15);
				lava[i] = new Lava(x,y);
				
			}

			//for moving barriers
			barrier = new Barrier(440,0);
			
			int x = 40*(int)(Math.random()*20+5);
			int y = 40*(int)(Math.random()*15);
			treasure = new Treasure(x,y);

		}
		else if(level == 1) {
			player = new Player(81,281);
			coins = new Coins[50];
			for(int i=0; i < coins.length; i++) {
				int x = 40*(int)(Math.random()*20+5)+10;
				int y = 40*(int)(Math.random()*15)+10;
				coins[i] = new Coins(x,y);
			}
			//a lava tile will be a 40x40
			lava = new Lava[450];
			for(int i=0; i<lava.length; i++) {
				int x = 40*(int)(Math.random()*20+5);
				int y = 40*(int)(Math.random()*15);
				lava[i] = new Lava(x,y);
			}
			int x = 40*(int)(Math.random()*20+5);
			int y = 40*(int)(Math.random()*15);
			treasure = new Treasure(x,y);
			
		}
		
	}

	public Dimension getPreferredSize() {
		//Sets the size of the panel
        return new Dimension(1200,600);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		Font font = new Font("Arial", Font.BOLD, 30);
		//Level 0 and 3: Start and End Screens
		//Level 1 and 2: Actual Gameplay
		if(level ==3 && diedToLava == true || diedToBarrier == true) { //for the "You Touched Lava" Screen
			g.drawImage(lavaScreen,0,0,1200,600,null);
			g.setColor(transRed);
			g.fillRect(0,0,1200,600);
			Font youDied = new Font("Arial", Font.BOLD,80);
			g.setFont(youDied);
			g.setColor(white);
			g.drawString("You Died!",425,100);
			Font score = new Font("Arial", Font.PLAIN, 30);
			g.setFont(score);

			if(diedToBarrier) {
				g.drawString("You bumped into a moving barrier!",375,150);
			}
			if(diedToLava) {
				g.drawString("You touched Lava!",475,150);
			}


			g.drawString("Your Score: " + points,500,200);
			g.drawString("Press ENTER to play again!",425,550);
		}
		if (level == 3 && diedToLava == false && diedToBarrier == false) { //code for the end victory screen
			g.drawImage(end, 0, 0, 1200, 600, null);
			g.setColor(transBlack);
			g.fillRect(0,0,1200,600);
			g.setColor(white);
			g.fillRect(350,50,550,200);
			Font congratulations = new Font("ComicSansMS", Font.BOLD, 50);
			g.setFont(congratulations);
			g.setColor(black);
			g.drawString("Congratulations!!!",400,100);
			g.drawString("You've Escaped!!!",400,160);
			Font scoreReport = new Font("Arial", Font.PLAIN, 25);
			g.setFont(scoreReport);
			g.drawString("Your Score: "+ points,525,200);
			Font playAgain = new Font("TrebuchetMS", Font.BOLD, 50);
			g.setFont(playAgain);
			g.setColor(purple);
			g.drawString("Press Enter to Play Again!!!",300,550);
			win.drawMe(g);

		}
		else if (level == 2) {
			map.drawStoneTiles2(g);
			map.drawStart2(g);
			map.drawEnd2(g);

			for(int i=0; i<coins.length; i++) {
				coins[i].drawCoins(g);
			}
			
			treasure.drawMe(g);

			for(int i=0; i<lava.length; i++) {
				lava[i].drawLavaTiles(g);
				lava[i].drawLavaParticles(g);
			}
			barrier.drawMe(g);

			map.drawHorizontalGrid(g,0,-1);
			map.drawVerticalGrid(g,-1,0);
			g.setFont(font);
			g.setColor(purple);
			g.drawString("Level " + level, 25, 50);
			g.drawString("START", 25, 550);
			g.drawString("END", 1050, 550);

			
			g.setFont(font);
			g.setColor(purple);
			g.drawString("Points: " + points, 25, 80);

			g.drawImage(finishFlag,1025,375,null);
			player.drawMe(g);
		}
		else if (level == 1) {
			map.drawStoneTiles(g);
			map.drawStart(g);
			map.drawEnd(g);

			for(int i=0; i<coins.length; i++) {
				coins[i].drawCoins(g);
			}

			treasure.drawMe(g);
			
			for(int i=0; i<lava.length; i++) {
				lava[i].drawLavaTiles(g);
				lava[i].drawLavaParticles(g);
			}

			map.drawHorizontalGrid(g,0,-1);
			map.drawVerticalGrid(g,-1,0);
			g.setFont(font);
			g.setColor(purple);
			g.drawString("Level " + level, 25, 50);
			g.drawString("START", 25, 550);
			g.drawString("END", 1050, 550);

			
			g.setFont(font);
			g.setColor(purple);
			g.drawString("Points: " + points, 25, 80);
			g.drawImage(finishFlag,1025,375,null);
			player.drawMe(g);

			
		}
		else if(level ==0) {
			g.drawImage(lavaScreen, 0, 0, 1200, 600, null);
			g.drawImage(Title, 50,50, null);
			g.drawImage(javaEdition,400,160,400,50,null);
			g.drawImage(Chest,60,250,80,80,null);
			g.setColor(purple);
			Font collectibles = new Font("Arial", Font.BOLD, 20);
			g.setFont(collectibles);
			g.drawString("In-Game Collectibles",20,250);
			g.drawString("Your Character",960,250);
			Font collectiblesDescription = new Font("Arial",Font.PLAIN,10);
			g.setColor(white);
			g.setFont(collectiblesDescription);
			g.drawString("Loot Chest: Up to 50 coins",40,340);
			g.drawString("Coins: 1 point each",50,450);

			g.setColor(yellow);
			g.fillOval(60,350,80,80);
			g.setColor(yellow2);
			g.fillOval(64,354,72,72);
			//for drawing the character icon

			int x = 1000;
			int y = 300;

			g.setColor(characterPurple);
			g.fillRect(x,y,80,80);
			g.setColor(white);
			g.fillRect(x+10,y+10,20,20);
			g.fillRect(x+50,y+10,20,20);
			g.setColor(black);
			//pupils
			g.fillRect(x+14,y+20,10,10);
			g.fillRect(x+54,y+20,10,10);
			//mouth
			g.fillRect(x+10,y+50,60,6);
			g.fillRect(x+10,y+40,6,10);
			g.fillRect(x+65,y+40,5,10);





			g.setColor(white);
			Font instructions = new Font("Arial", Font.BOLD, 18);
			g.setFont(instructions);
			g.drawString("Oh no! You have been trapped in a room with a slowly melting floor!", 300, 250);
			g.drawString("How to Play: The orange tiles called lava will generate on the screen.", 300, 275);
			g.drawString("Your goal is to make it to the other side and collect as many coins as possible",250,300);
			g.drawString("Use your arrow keys to move around the map.",410,325);
			g.drawString("If you touch lava or bump into a barrier, you will have to restart. Good Luck!",260,350);
			Font developersNote = new Font("Arial", Font.PLAIN, 10);
			g.setFont(developersNote);
			g.setColor(purple);
			g.drawString("For Developers: Press P to skip every level without having to play the game",425,510);
			Font version = new Font("CourierNewPS",Font.PLAIN,10);
			g.setFont(version);
			g.setColor(lightGray);
			g.drawString("v2024.12.15",1135,590);
			g.drawString("Sound effects from Minecraft and Roblox",5,580);
			g.drawString("@Jerry Liang 2024",5,590);

		}
		
	} 
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
			this.clickSound();
            level=1;
			play.setVisible(false);
			this.levels();
            repaint();
        }
    }

	public void keyPressed(KeyEvent e) {

		//prints the keycode based on the key clicked
		//System.out.println( e.getKeyCode()); 
		if( e.getKeyCode() == 38) {
			player.moveUp();
		}
		if( e.getKeyCode() == 40) {
			player.moveDown();
		}
		if( e.getKeyCode() == 37 ) {
			player.moveLeft();
		}
		if( e.getKeyCode() == 39) {
			player.moveRight();
		}
		if( e.getKeyCode() == 80) {
			play.setVisible(false);
			diedToLava = false;
			diedToBarrier = false;
			level++;
			this.levels();
			this.paintComponent(getGraphics()); //we need to call paintComponent so that it actually updates the screen.
		}
		if (e.getKeyCode() ==10) {
			if(level ==3) { //reset everything once game is restarted
				diedToLava = false;
				diedToBarrier = false;
				level = 0;
				points = 0;
				count = 0;
				lavaIndex = 0;
				this.levels();
				this.paintComponent(getGraphics()); //we need to call paintComponent so that it actually updates the screen.
				play.setVisible(true);
			}
		}
		//repaint();

		requestFocus();
		setFocusable(true);		
	}
	public void keyReleased(KeyEvent e) {

	}
	public void keyTyped(KeyEvent e) {

	}
	public void gameOverSound() {
		try {
			URL url = this.getClass().getClassLoader().getResource("GameOver.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}

	}
	public void levelCompleteSound() {
		try {
			URL url = this.getClass().getClassLoader().getResource("levelup.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}

	}
	public void clickSound() {
		try {
			URL url = this.getClass().getClassLoader().getResource("click.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
	public void chestSound() {
		try {
			URL url = this.getClass().getClassLoader().getResource("chestOpened.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
	public void coinCollectedSound() {
		int sound = (int)(Math.random()*3);
		if(sound == 0) {
			try {
				URL url = this.getClass().getClassLoader().getResource("coin1.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
		else if(sound == 1) {
			try {
				URL url = this.getClass().getClassLoader().getResource("coin2.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
		else if(sound == 2) {
			try {
				URL url = this.getClass().getClassLoader().getResource("coin3.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
		else if(sound == 3) {
			try {
				URL url = this.getClass().getClassLoader().getResource("coin3.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
	}
	public void animate() {
		//animate is also used as an infinite loop and will be used for anything that has needs to be constantly running.
		

		while(true) {
			if(level == 3 && diedToLava == false && diedToBarrier == false) {
				win.move(); //this is for the player animation at the end of the game when they survive
			}
			//code to slowly generate the lava on the screen
			if(level >=1 && level <3) {
				if(level == 1) {
					if(count %25 ==0 && lavaIndex<lava.length) { // %10 means divisible by 10, this is used to set a delay between each lava that generates
						lava[lavaIndex].setVisible(true);
						lava[lavaIndex].lavaSound();
						lavaIndex++;
						
					}
	
					if(lavaIndex < lava.length) {
						count++;
					}
				}
				if(level ==2) {
					if(count %5 ==0 && lavaIndex<lava.length) { // %10 means divisible by 10, this is used to set a delay between each lava that generates
						lava[lavaIndex].setVisible(true);
						lava[lavaIndex].lavaSound();
						lavaIndex++;
					}
	
					if(lavaIndex < lava.length) {
						count++;
					}
					//for animating the barriers.
					barrier.move();
				}
				


			//level up the player when it reaches the end.
				int playerX = player.getPlayerX();
				if(playerX >= 1160) {
					level++;
					this.levels();
					this.levelCompleteSound();
				}
				//have the player restart if they touch lava. have the player earn points if they collect coins
				if(player.getVisible() ==true) {
					//collision detection for lava
					if(lava != null) {
						for(int i=0; i<lava.length; i++) {
							if(lava[i].getVisible() == true) {
								boolean lavaResult = player.checkLava(lava[i]);
								if( lavaResult == true) {
									diedToLava = true;
									this.gameOverSound();
									player.setVisible(false);
									level = 3;
									count = 0; //reset the amount of lava when the game restarts
									lavaIndex = 0; //reset the lava count when the game restarts
									this.levels();
								}
						
							}
						}
					}
					if(coins != null) {
						for(int i=0; i<coins.length; i++) {
							//check collision only when the coins are visible
							if(coins[i].getVisible() == true) {
								boolean coinResult = player.checkCoins(coins[i]);
								if(coinResult == true) {
									this.coinCollectedSound();
									coins[i].setVisible(false);
									points++;
								}
								
							}
						}
					}
					if(barrier != null && level == 2) {
						boolean barrierResult = player.checkBarrier(barrier);
						if(barrierResult == true) {
							diedToBarrier = true;
							this.gameOverSound();
							player.setVisible(false);
							level = 3;
							count = 0; //reset the amount of lava when the game restarts
							lavaIndex = 0; //reset the lava count when the game restarts
							this.levels();
							//System.out.println("Barrier");
						}
					}
					//collision detection for treasure chest. if the player collides with a chest, they get coins.
					if(treasure != null) {
						if(treasure.getVisible()==true) {
							boolean treasureResult = player.checkTreasure(treasure);
							if(treasureResult == true) {
								int bonus = (int)(Math.random()*50);
								points = points + bonus;
								treasure.setVisible(false); //utilizes the closed chest texture that I made
								this.chestSound();
							}
						}
					}
					

				//ensures the player does not go off the screen.
					player.setBottomBounds();
					player.setLeftBounds();
					player.setRightBounds();
					player.setTopBounds();
				}
				//for animating the lava
				for(int i=0; i<lava.length; i++) {
					lava[i].moveParticles();
				}
				
			//sets the delay. This delay is necessary so it gives the game time to update the variables so it doesn't become 'null'	
			}
			try {
				Thread.sleep(30);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			repaint();
		}
	}
}
