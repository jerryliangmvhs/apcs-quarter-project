
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;


public class CardGame {
    private ArrayList<Card> deck;
	private ArrayList<Card> p1Cards;
	private ArrayList<Card> p2Cards;
	private ArrayList<Card> placedCards;

	private String requiredCombination = "";
	private String selectedCombination = "";

	private boolean blankScreen = false;
	private int minRequiredValue = 0;
	private int turn = 1;
	private int P1MinimumSelectedValue;
	private int P2MinimumSelectedValue;
	private boolean visible = false;
	private Color darkRed = new Color(160,0,0);
	private BufferedImage mainBackground;
	private BufferedImage howToPlayBackground;
	private BufferedImage endGame;
	private Color lightPink = new Color(255,210,210);

    public CardGame(){
        deck = new ArrayList<Card>();
        p1Cards = new ArrayList<Card>();
		p2Cards = new ArrayList<Card>();
		placedCards = new ArrayList<Card>();
		

		//hearts
        deck.add(new Card(3, "3", "hearts"));
        deck.add(new Card(4, "4", "hearts"));
        deck.add(new Card(5, "5", "hearts"));
		deck.add(new Card(6, "6", "hearts"));
        deck.add(new Card(7, "7", "hearts"));
		deck.add(new Card(8, "8", "hearts"));
        deck.add(new Card(9, "9", "hearts"));
		deck.add(new Card(10, "10", "hearts"));
		deck.add(new Card(11,"J","hearts"));
		deck.add(new Card(12,"Q","hearts"));
		deck.add(new Card(13,"K","hearts"));
		deck.add(new Card(14,"A","hearts"));
		deck.add(new Card(15, "2", "hearts"));

		//diamonds
		deck.add(new Card(3, "3", "diamonds"));
        deck.add(new Card(4, "4", "diamonds"));
        deck.add(new Card(5, "5", "diamonds"));
		deck.add(new Card(6, "6", "diamonds"));
        deck.add(new Card(7, "7", "diamonds"));
		deck.add(new Card(8, "8", "diamonds"));
        deck.add(new Card(9, "9", "diamonds"));
		deck.add(new Card(10, "10", "diamonds"));
		deck.add(new Card(11,"J","diamonds"));
		deck.add(new Card(12,"Q","diamonds"));
		deck.add(new Card(13,"K","diamonds"));
		deck.add(new Card(14,"A","diamonds"));
		deck.add(new Card(15, "2", "diamonds"));

		//spades
		deck.add(new Card(3, "3", "spades"));
        deck.add(new Card(4, "4", "spades"));
        deck.add(new Card(5, "5", "spades"));
		deck.add(new Card(6, "6", "spades"));
        deck.add(new Card(7, "7", "spades"));
		deck.add(new Card(8, "8", "spades"));
        deck.add(new Card(9, "9", "spades"));
		deck.add(new Card(10, "10", "spades"));
		deck.add(new Card(11,"J","spades"));
		deck.add(new Card(12,"Q","spades"));
		deck.add(new Card(13,"K","spades"));
		deck.add(new Card(14,"A","spades"));
		deck.add(new Card(15, "2", "spades"));

		//clubs
		deck.add(new Card(3, "3", "clubs"));
        deck.add(new Card(4, "4", "clubs"));
        deck.add(new Card(5, "5", "clubs"));
		deck.add(new Card(6, "6", "clubs"));
        deck.add(new Card(7, "7", "clubs"));
		deck.add(new Card(8, "8", "clubs"));
        deck.add(new Card(9, "9", "clubs"));
		deck.add(new Card(10, "10", "clubs"));
		deck.add(new Card(11,"J","clubs"));
		deck.add(new Card(12,"Q","clubs"));
		deck.add(new Card(13,"K","clubs"));
		deck.add(new Card(14,"A","clubs"));
		deck.add(new Card(15, "2", "clubs"));

		//jokers
		deck.add(new Card(16,"Joker","Little Joker"));
		deck.add(new Card(17,"Joker","Big Joker"));


        //shuffle
		this.shuffle();
		try{
			mainBackground = ImageIO.read(new File("mainBackground.png"));
		} catch (IOException e) {}
		try{
			howToPlayBackground = ImageIO.read(new File("howToPlayBackground.png"));
		} catch (IOException e) {}
		try{
			endGame = ImageIO.read(new File("endGame.png"));
		} catch (IOException e) {}

        
		//Edit: Need to give 20 cards to player 1, other 20 goes to player 2. We are not giving each player half a deck otherwise they'd know what the other player has due to process of elimination
		for(int i=0; i<20; i++){
			p1Cards.add(deck.get(0));
        	deck.remove(0);
		}
        
		for(int i=0; i<20; i++){
			p2Cards.add(deck.get(0));
        	deck.remove(0);
		}
		
		
		
    }
	public void drawTitleScreen(Graphics g){
		//put a background image here
		g.drawImage(mainBackground,0,0,null);
		if(visible == true){
			g.drawImage(howToPlayBackground,0,0,null);
			Font instructions = new Font("Arial",Font.BOLD,12);
			g.setColor(lightPink);
			g.setFont(instructions);
			g.drawString("Your goal is to place cards that have a greater value than the other player.",75,200);
			g.drawString("You can place a single card, a pair, triple or sequence of 5 (Example: 3,4,5,6,7), but must be consistent with the other player.",75,230);
			g.drawString("For Example: if the other player draws a single card, you must draw a single card but a greater value.",75,260);
			g.drawString("A quadruple counts as a bomb, and can be used when you don't have a match for what the other player drew.",75,290);
			g.drawString("A pair of Jokers count as a rocketship, and beats any kind of combination. Your opponent is forced to skip if you draw that.",75,320);
			g.drawString("You can click skip if you don't have any eligible cards to draw.",75,350);
			g.drawString("The first one to use up all their cards wins! Good Luck!",75,380);
		}
	}
	public void drawEndScreen(Graphics g){
		g.drawImage(endGame,0,0,null);
	}

    public void drawGame(Graphics g){
		Font font = null;
		int x = 0;
		int y = 0;
		g.setColor(darkRed);
		g.fillRect(0,0,800,600);

		//for drawing the placed cards
		x = 375;
		y = 225;
		for(int i=0; i<placedCards.size(); i++){
			placedCards.get(i).drawMe(g, x, y);
			x+=35;
		}
        if(blankScreen == false){
			//player 2
			if(turn ==2){
				font = new Font("Arial", Font.PLAIN, 30);
				g.setFont(font);
				g.setColor(Color.white);
				g.drawString("Player 2: ", 20, 40);

				x = 20;
				y = 50;

				for(int i=0; i<p2Cards.size(); i++){
					p2Cards.get(i).drawMe(g,x,y);
					x += 35;
				}
			}

			//player 1
			if(turn ==1){
				font = new Font("Arial", Font.PLAIN, 30);
				g.setFont(font);
				g.setColor(Color.white);
				g.drawString("Player 1: ", 20, 390);

				x= 20;
				y = 400;
				for(int i=0; i<p1Cards.size(); i++){
					p1Cards.get(i).drawMe(g,x,y);
					x += 35;
				}
			}
			
		}
	}
	
	
	public void setBlankScreen(boolean blankScreen){
		this.blankScreen = blankScreen;

	}
	public void switchPlayer(){
		if(selectedCombination.equals("rocket")==false){
			if(turn ==1){
				turn =2;
			}
			else if(turn ==2){
				turn =1;
			}
		}
	}

	
    public void shuffle(){
		//write code to shuffle your deck
		for(int i=0; i<deck.size(); i++){
            int j = (int)(Math.random()*deck.size());
			Card temp1 = deck.get(i);
			Card temp2 = deck.get(j);
			deck.set(i,temp2);
			deck.set(j,temp1);
        }
	
	}
	public void organizeCards(){
		for(int i=0; i<p1Cards.size()-1; i++){
			for(int j=0; j<p1Cards.size()-i-1; j++){
				if(p1Cards.get(j).getValue()>p1Cards.get(j+1).getValue()){
					Card temp = p1Cards.get(j);
					p1Cards.set(j,p1Cards.get(j+1));
					p1Cards.set(j+1,temp);
				}
			}
		} 
		for(int i=0; i<p2Cards.size()-1; i++){
			for(int j=0; j<p2Cards.size()-i-1; j++){
				if(p2Cards.get(j).getValue()>p2Cards.get(j+1).getValue()){
					Card temp = p2Cards.get(j);
					p2Cards.set(j,p2Cards.get(j+1));
					p2Cards.set(j+1,temp);
				}
			}
		}
	}
	public void selectP1Cards(int xInput, int yInput){
		int x=20;
		int y=400;
		for(int i=0; i<p1Cards.size(); i++){
			if(i<p1Cards.size()-1){ //for overlapping cards
				if(xInput >=x && xInput <= x + 35 && yInput >=y && yInput <= y + 150){
					p1Cards.get(i).highLight();
					
				}
			}
			else{ //for the last card which doesn't have any overlapping
				if(xInput >=x && xInput <= x + 90 && yInput>=y && yInput <= y + 150){
					p1Cards.get(i).highLight();
					
				}
			}
			
			x+=35;
		}
	}
	public void selectP2Cards(int xInput, int yInput){
		int x =20;
		int y = 50;
		for(int i=0; i<p2Cards.size(); i++){
			if(i<p2Cards.size()-1){
				if(xInput >=x && xInput <= x + 35 && yInput >=y && yInput <= y + 150){
					p2Cards.get(i).highLight();
					
				}
			}
			else{
				if(xInput >=x && xInput <= x + 90 && yInput >=y && yInput <= y + 150){
					p2Cards.get(i).highLight();
					
				}
			}
			
			x+=35;
		}
	}
	public void placeCards(){
		placedCards.clear(); //clear previous cards that were in the center so we know what cards the previous player really drew.
		if(turn ==1){
			for(int i=0; i<p1Cards.size(); i++){
				if(p1Cards.get(i).getSelected()==true){
					placedCards.add(p1Cards.get(i));
					p1Cards.remove(i);
					i--;
				}
			}
		}
		if(turn ==2){
			for(int i=0; i<p2Cards.size(); i++){
				if(p2Cards.get(i).getSelected()==true){
					placedCards.add(p2Cards.get(i));
					p2Cards.remove(i);
					i--;
				}
			}
		}
	}
	public String checkSingle(){
		for(int i=0; i<p1Cards.size(); i++){
			if(p1Cards.get(i).getSelected()==true && getP1SelectedCardAmount()==1){
				selectedCombination = "single";
				return selectedCombination;
			}
		}
		for(int i=0; i<p2Cards.size(); i++){
			if(p2Cards.get(i).getSelected()==true && getP2SelectedCardAmount()==1){
				selectedCombination = "single";
				return selectedCombination;
			}
		}
		return "";
	}
	public String checkPair(){
		for(int i=0; i<p1Cards.size()-1; i++){
			if(p1Cards.get(i).getSelected()==true && p1Cards.get(i+1).getSelected() == true && getP1SelectedCardAmount() == 2){
				if(p1Cards.get(i).getValue() == p1Cards.get(i+1).getValue()){
					selectedCombination = "pair";
					return selectedCombination;
				}	
			}
		}
		for(int i=0; i<p2Cards.size()-1; i++){
			if(p2Cards.get(i).getSelected()==true && p2Cards.get(i+1).getSelected() == true && getP2SelectedCardAmount() == 2){
				if(p2Cards.get(i).getValue() == p2Cards.get(i+1).getValue()){
					selectedCombination = "pair";
					return selectedCombination;
				}
			}
		}
		return "";
	}

	public String checkTriplet(){
		for(int i=0; i<p1Cards.size()-2; i++){
			if(p1Cards.get(i).getSelected()==true && p1Cards.get(i+1).getSelected() == true && p1Cards.get(i+2).getSelected()==true && getP1SelectedCardAmount()==3){
				if(p1Cards.get(i).getValue()==p1Cards.get(i+1).getValue() && p1Cards.get(i+1).getValue() == p1Cards.get(i+2).getValue()){
					selectedCombination = "triple";
					return selectedCombination;
				}
			}
		}
		for(int i=0; i<p2Cards.size()-2; i++){
			if(p2Cards.get(i).getSelected()==true && p2Cards.get(i+1).getSelected() == true && p2Cards.get(i+2).getSelected()==true && getP2SelectedCardAmount()==3){
				if(p2Cards.get(i).getValue()==p2Cards.get(i+1).getValue() && p2Cards.get(i+1).getValue() == p2Cards.get(i+2).getValue()){
					selectedCombination = "triple";
					return selectedCombination;
				}
			}
		}
		return "";
	}

	public String checkBomb(){ //quadruplet
		for(int i=0; i<p1Cards.size()-3; i++){
			if(p1Cards.get(i).getSelected()==true && p1Cards.get(i+1).getSelected()==true && p1Cards.get(i+2).getSelected()==true && p1Cards.get(i+3).getSelected()==true && getP1SelectedCardAmount() == 4){
				if(p1Cards.get(i).getValue()==p1Cards.get(i+1).getValue()){
					if(p1Cards.get(i+1).getValue() == p1Cards.get(i+2).getValue()){
						if(p1Cards.get(i+2).getValue()==p1Cards.get(i+3).getValue()){
							selectedCombination = "bomb";
							return selectedCombination;
						}
					}
				}
			}
		}
		for(int i=0; i<p2Cards.size()-3; i++){
			if(p2Cards.get(i).getSelected()==true && p2Cards.get(i+1).getSelected()==true && p2Cards.get(i+2).getSelected()==true && p2Cards.get(i+3).getSelected()==true && getP2SelectedCardAmount() == 4){
				if(p2Cards.get(i).getValue()==p2Cards.get(i+1).getValue()){
					if(p2Cards.get(i+1).getValue() == p2Cards.get(i+2).getValue()){
						if(p2Cards.get(i+2).getValue()==p2Cards.get(i+3).getValue()){
							selectedCombination = "bomb";
							return selectedCombination;
						}
					}
				}
			}
		}
		return "";
	}
	
	public String checkSequence(){
		for(int i=0; i<p1Cards.size()-4; i++){
			if(p1Cards.get(i).getSelected() == true && p1Cards.get(i+1).getSelected() == true && p1Cards.get(i+2).getSelected() == true && p1Cards.get(i+3).getSelected() == true && p1Cards.get(i+4).getSelected() == true && getP1SelectedCardAmount()==5){
				if(p1Cards.get(i).getValue() ==p1Cards.get(i+1).getValue()-1){
					if(p1Cards.get(i+1).getValue() == p1Cards.get(i+2).getValue()-1){
						if(p1Cards.get(i+2).getValue() == p1Cards.get(i+3).getValue()-1){
							if(p1Cards.get(i+3).getValue() == p1Cards.get(i+4).getValue()-1){
								selectedCombination = "sequence";
								return selectedCombination;
							}
						}
					}
				}
			}
		}
		for(int i=0; i<p2Cards.size()-4; i++){
			if(p2Cards.get(i).getSelected() == true && p2Cards.get(i+1).getSelected() == true && p2Cards.get(i+2).getSelected() == true && p2Cards.get(i+3).getSelected() == true && p2Cards.get(i+4).getSelected() == true && getP2SelectedCardAmount()==5){
				if(p2Cards.get(i).getValue() ==p2Cards.get(i+1).getValue()-1){
					if(p2Cards.get(i+1).getValue() == p2Cards.get(i+2).getValue()-1){
						if(p2Cards.get(i+2).getValue() == p2Cards.get(i+3).getValue()-1){
							if(p2Cards.get(i+3).getValue() == p2Cards.get(i+4).getValue()-1){
								selectedCombination = "sequence";
								return selectedCombination;
							}
						}
					}
				}
			}
		}
		return "";
	}
	public String checkRocket(){
		for(int i=0; i<p1Cards.size()-1; i++){
			if(p1Cards.get(i).getSelected()==true && p1Cards.get(i+1).getSelected()==true && getP1SelectedCardAmount()==2){
				if(p1Cards.get(i).getValue()==16 && p1Cards.get(i+1).getValue()==17){
					
					selectedCombination = "rocket";
					return selectedCombination;
				}
			}
		}
		for(int i=0; i<p2Cards.size()-1; i++){
			if(p2Cards.get(i).getSelected()==true && p2Cards.get(i+1).getSelected()==true && getP2SelectedCardAmount()==2){
				if(p2Cards.get(i).getValue()==16 && p2Cards.get(i+1).getValue()==17){
					
					selectedCombination = "rocket";
					return selectedCombination;
				}
			}
		}
		return "";
	}

	public void updateMinimumRequiredValue(){
		for(int i=0; i<p1Cards.size(); i++){
			if(p1Cards.get(i).getSelected()==true){
				minRequiredValue = p1Cards.get(i).getValue();
				//System.out.println(minRequiredValue);
				return; //stops the loop when the lowest card value in a move is detected.
			}
		}
		for(int i=0; i<p2Cards.size(); i++){
			if(p2Cards.get(i).getSelected()==true){
				minRequiredValue = p2Cards.get(i).getValue();
				//System.out.println(minRequiredValue);
				return; //stops the loop when the lowest card value in a move is detected.
			}
		}
	}

	public int getP1MinimumSelectedValue(){
		int i=0;
		for(i=0; i<p1Cards.size(); i++){
			if(p1Cards.get(i).getSelected()==true){
				System.out.println(p1Cards.get(i).getValue());
				P1MinimumSelectedValue = p1Cards.get(i).getValue();
				return P1MinimumSelectedValue;
			}
		}
		return -1;
	}

	public int getP2MinimumSelectedValue(){
		int i=0;
		for(i=0; i<p2Cards.size(); i++){
			if(p2Cards.get(i).getSelected()==true){
				P2MinimumSelectedValue = p2Cards.get(i).getValue();
				return P2MinimumSelectedValue;
			}
		}
		return -1;
	}
	public void updateRequiredCombination(){
		if(requiredCombination.equals("")){
			requiredCombination = selectedCombination;
		}
	}
	public void clearMinimumSelectedValue(){
		P1MinimumSelectedValue = 0;
		P2MinimumSelectedValue = 0;
	}

	public String getSelectedCombination(){
		return selectedCombination;
	}

	public int getP1SelectedCardAmount(){
		int counter = 0;
		for(int i=0; i<p1Cards.size(); i++){
			if(p1Cards.get(i).getSelected()==true){
				counter++;
			}
		}
		return counter;
	}

	public int getP2SelectedCardAmount(){
		int counter = 0;
		for(int i=0; i<p2Cards.size(); i++){
			if(p2Cards.get(i).getSelected()==true){
				counter++;
			}
		}
		return counter;
	}
	
	public int getMinimumRequiredValue(){
		return minRequiredValue;
	}

	public String getRequiredCombination(){
		return requiredCombination;
	}
	
	public void reset(){
		minRequiredValue = 0;
		requiredCombination = "";
	}
	public void clearSelectedCombination(){
		selectedCombination = "";
	}
	
	public int getPlayer1CardAmount(){
		return p1Cards.size();
	}
	public int getPlayer2CardAmount(){
		return p2Cards.size();
	}

	public void showHowToPlay(boolean v){
		visible = v;
	}
}