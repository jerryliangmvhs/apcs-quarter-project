import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class BoardGame {
    private Grid[][] grid;
    private int gridX = 60;
    private int gridY = 20;
    private int turn = 1;
    private Player player1;
    private Player player2;
    private House P1House;
    private House P2House;
    private Heart P1Heart;
    private Heart P2Heart;
    private Card[] card = new Card[9];
    private BufferedImage card1;
	private BufferedImage card2;
	private BufferedImage card3;
	private BufferedImage card4;
	private BufferedImage card5;
	private BufferedImage card6;
	private BufferedImage card7;
	private BufferedImage card8;
	private BufferedImage card9;
    private BufferedImage playerOneSprite;
    private BufferedImage playerTwoSprite;
    private BufferedImage player1Heart;
    private BufferedImage player2Heart;
    private BufferedImage player1House;
    private BufferedImage player2House;
    private int player1Score = 0;
    private int player2Score = 0;
    private int player1LifeTiles = 0;
    private int player2LifeTiles = 0;

    public BoardGame(){
        grid = new Grid[7][8];
        player1 = new Player(6,7, "Player 1");
        player2 = new Player(6,7, "Player 2");
        P1House = new House(1,1,"Player 1 House");
        P2House = new House(3,1,"Player 2 House");
        P1Heart = new Heart(1,6,"Player 1 Married");
        P2Heart = new Heart(3,6,"Player 2 Married");


        try {
            card1 = ImageIO.read(new File("toddler1.png"));
			card2 = ImageIO.read(new File("toddler2.png"));
			card3 = ImageIO.read(new File("toddler3.png"));
			card4 = ImageIO.read(new File("school1.png"));
			card5 = ImageIO.read(new File("school2.png"));
			card6 = ImageIO.read(new File("school3.png"));
			card7 = ImageIO.read(new File("adult1.png"));
			card8 = ImageIO.read(new File("adult2.png"));
			card9 = ImageIO.read(new File("adult3.png"));
            playerOneSprite = ImageIO.read(new File("player1.png"));
            playerTwoSprite = ImageIO.read(new File("player2.png"));
            player1Heart = ImageIO.read(new File("p1Heart.png"));
            player2Heart = ImageIO.read(new File("p2Heart.png"));
            player1House = ImageIO.read(new File("p1House.png"));
            player2House = ImageIO.read(new File("p2House.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        card[0] = new Card(card1);
		card[1] = new Card(card2);
		card[2] = new Card(card3);
		card[3] = new Card(card4);
		card[4] = new Card(card5);
		card[5] = new Card(card6);
		card[6] = new Card(card7);
		card[7] = new Card(card8);
		card[8] = new Card(card9);
		
		for(int i=0; i<card.length; i++){
			card[i].setVisible(false);
		}



        for(int r=0; r<grid.length; r++){
			for(int c=0; c<grid[r].length; c++){
				grid[r][c] = new Grid(gridX,gridY,false);
				gridX+=80;
			}
			gridX=60;
			gridY+=80;
		}
    }
    public void drawGame(Graphics g){
        for(int r=0; r<grid.length; r++){
            for(int c=0; c<grid[r].length; c++){
                if(r%2==0){
                    grid[r][c].setVisible(true);
                }
                if((r==1||r==5)&&c==0){
                    grid[r][c].setVisible(true);
                }
                if(r==3 && c==7){
                    grid[r][c].setVisible(true);
                }
                grid[r][c].drawGrid(g);
            }
        }
        player1.drawMe(g,playerOneSprite);
        player2.drawMe(g,playerTwoSprite);
        for(int i=0; i<card.length; i++){
            card[i].drawMe(g);
        }
        if((player1.getRow()==2 && player1.getColumn()<=1)||player1.getRow()<=1){
            P1House.drawMe(g,player1House);
        }
        if((player2.getRow()==2 && player2.getColumn()<=1)||player2.getRow()<=1){
            P2House.drawMe(g,player2House);
        }
        if((player1.getRow()==2 && player1.getColumn()<=6)||player1.getRow()<=1){
            P1Heart.drawMe(g,player1Heart);
        }
        if((player2.getRow()==2 && player2.getColumn()<=6)||player2.getRow()<=1){
            P2Heart.drawMe(g,player2Heart);
        }


    }
    public void movePlayer1(int num){
        for(int i=0; i<num; i++){
            if(player1.getRow()==6 || player1.getRow()==2){
                int currentCol = player1.getColumn();
                if(player1.getColumn()!=0){
                    int newColumn = currentCol - 1;
                    player1.setColumn(newColumn);
                }
                if(player1.getColumn()==0){
                    int currentRow = player1.getRow();
                    player1.setRow(currentRow-1);
                }
            }
            if(player1.getRow()==0 || player1.getRow() ==4){
                if(player1.getColumn()!=7){
                    int currentCol = player1.getColumn();
                    player1.setColumn(currentCol+1);
                }
                if(player1.getColumn()==7){
                    int currentRow = player1.getRow();
                    player1.setRow(currentRow-1);
                }
            }
            if(player1.getRow()==1||player1.getRow()==3||player1.getRow()==5){
                int currentRow = player1.getRow();
                player1.setRow(currentRow-1);
            }
            if(player1.getColumn()>7 && player1.getRow()==0){
                player1.setColumn(7);
                player1.setRow(0);
            }
        }  
    }
    public void movePlayer2(int num){
        for(int i=0; i<num; i++){
            if(player2.getRow()==6 || player2.getRow()==2){
                int currentCol = player2.getColumn();
                if(player2.getColumn()!=0){
                    int newColumn = currentCol - 1;
                    player2.setColumn(newColumn);
                }
                if(player2.getColumn()==0){
                    int currentRow = player2.getRow();
                    player2.setRow(currentRow-1);
                }
            }
            if(player2.getRow()==0 || player2.getRow() ==4){
                if(player2.getColumn()!=7){
                    int currentCol = player2.getColumn();
                    player2.setColumn(currentCol+1);
                }
                if(player2.getColumn()==7){
                    int currentRow = player2.getRow();
                    player2.setRow(currentRow-1);
                }
            }
            if(player2.getRow()==1||player2.getRow()==3||player2.getRow()==5){
                int currentRow = player2.getRow();
                player2.setRow(currentRow-1);
            }
        }
    }
    public boolean isStillPlaying(){
        if(player1.getRow()<0 && player2.getRow()<0){
            return false;
        }
        return true;
    }
    public boolean p1stillPlaying(){
        if(player1.getRow()<0){
            return false;
        }
        return true;
    }
    public boolean p2stillPlaying(){
        if(player2.getRow()<0){
            return false;
        }
        return true;
    }
    public void reset(){
        player2.setPosition(6,7);
        player1.setPosition(6, 7);
        player2LifeTiles = 0;
        player2Score = 0;
        player1LifeTiles = 0;
        player1Score = 0;
    }
    public int getP1Score(){
        return player1LifeTiles + player1Score;
    }
    public int getP2Score(){
        return player2LifeTiles + player2Score;
    }
    public int getP1LifeTiles(){
        return player1LifeTiles;
    }
    public int getP2LifeTiles(){
        return player2LifeTiles;
    }
    public int getP1Money(){
        return player1Score;
    }
    public int getP2Money(){
        return player2Score;
    }
    public void switchPlayer(){
		if(turn ==1 && p2stillPlaying()){
			turn = 2;
		}
		else if(turn == 2 && p1stillPlaying()){
			turn = 1;
		}
	}

    public void drawCard(){
        if(turn ==1){
            if(player1.getRow()<=2){
                int cardIndex = (int)(Math.random()*3)+6;
                card[cardIndex].setVisible(true);
            }
            if(player1.getRow()>2 && player1.getRow()<=4){
                int cardIndex = (int)(Math.random()*3)+3;
                card[cardIndex].setVisible(true);
            }
            if(player1.getRow()>4 && player1.getRow() <=6){
                int cardIndex = (int)(Math.random()*3);
                card[cardIndex].setVisible(true);
            }
        }
        else if(turn ==2){
            if(player2.getRow()<=2){
                int cardIndex = (int)(Math.random()*3)+6;
                card[cardIndex].setVisible(true);
            }
            if(player2.getRow()>2 && player2.getRow()<=4){
                int cardIndex = (int)(Math.random()*3)+3;
                card[cardIndex].setVisible(true);
            }
            if(player2.getRow()>4 && player2.getRow() <=6){
                int cardIndex = (int)(Math.random()*3);
                card[cardIndex].setVisible(true);
            }
        }

    }
    public void updateScore(){
        if(turn ==1){
            if(card[0].getVisible()){
                player1LifeTiles -=1;
            }
            if(card[1].getVisible()){
                player1LifeTiles += (int)(Math.random()*3)+1;
            }
            if(card[2].getVisible()){
                player1LifeTiles +=(int)(Math.random()*3)+1;
            }
            if(card[3].getVisible()){
                player1LifeTiles +=(int)(Math.random()*3)+1;
            }
            if(card[4].getVisible()){
                player1LifeTiles +=(int)(Math.random()*6)+1;
            }
            if(card[5].getVisible()){
                player1LifeTiles -=1;
            }
            if(card[6].getVisible()){
                player1LifeTiles +=(int)(Math.random()*6)+1;
                player1Score += (int)(Math.random()*6)+1;
            }
            if(card[7].getVisible()){
                player1LifeTiles +=(int)(Math.random()*3)+1;
                player1Score -=1;
            }
            if(card[8].getVisible()){
                player1LifeTiles +=(int)(Math.random()*3)+1;
                player1Score -=3;
            }
        }
        else if(turn ==2){
            if(card[0].getVisible()){
                player2LifeTiles -=1;
            }
            if(card[1].getVisible()){
                player2LifeTiles +=(int)(Math.random()*3)+1;
            }
            if(card[2].getVisible()){
                player2LifeTiles +=(int)(Math.random()*3)+1;
            }
            if(card[3].getVisible()){
                player2LifeTiles +=(int)(Math.random()*3)+1;
            }
            if(card[4].getVisible()){
                player2LifeTiles +=(int)(Math.random()*3)+1;
            }
            if(card[5].getVisible()){
                player2LifeTiles -=1;
            }
            if(card[6].getVisible()){
                player2LifeTiles +=(int)(Math.random()*6)+1;
                player2Score += (int)(Math.random()*6)+1;
            }
            if(card[7].getVisible()){
                player2LifeTiles +=(int)(Math.random()*3)+1;
                player2Score -=1;
            }
            if(card[8].getVisible()){
                player2LifeTiles +=(int)(Math.random()*3)+1;
                player2Score -=3;
            }
        }
    }
    public int getTurn(){
        return turn;
    }
    public void hideCards(){
        for(int i=0; i<card.length; i++){
            card[i].setVisible(false);
        }
    }
    public boolean isDisplayingCards(){
        for(int i=0; i<card.length; i++){
            if(card[i].getVisible()==true){
                return true;
            }
        }
        return false;
    }

}
