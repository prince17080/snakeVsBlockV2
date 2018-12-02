// package snake_vs_block;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



public class gamePage {
	
	/**
	 * s is the scene of the Game Page
	 */
	static Scene s;
	
	/**
	 * snake is the instance of the Snake(Player)
	 */
	static snake snake = new snake();
	
	/**
	 * myBlocks contains the 5 blocks/walls which maybe visible 
	 */
	static ArrayList<block> myBlocks = new ArrayList<block>();
	
	/**
	 * myRat act as the Ball for the snake which increases the length of the snake when eaten by the snake
	 */
	static rat myRat ;
	
	/**
	 * score contains the total score at every time
	 */
	static int score ;
	
	/**
	 * shouldIncScoreCoin ensures the score increases when the snake hits the coin only once per coin
	 */
	static boolean shouldIncScoreCoin = true;
	
	/**
	 * shouldIncScoreRat ensures the score increases by 5 when the snake hits the rat
	 */
	static boolean shouldIncScoreRat = true;
	
	/**
	 * myCoin is the instance of Coin to render on the screen
	 */
	coin myCoin ;
	
	/**
	 * myMagnet is the instance of Magnet to render on the screen
	 */
	magnet myMagnet;
	
	/**
	 * myBomb is the instance of Bomb to render on the screen
	 */
	bomb myBomb;
	
	/**
	 * myShield is the instance of Shield to render on the screen
	 */
	shield myShield;
	
	/**
	 * whichToken tells to add which token on the top layer between 2 blocks layer
	 */
	int whichToken;
	
	/**
	 * isShield checks if the snake has Shield
	 */
	boolean isShield = false;
	
	/**
	 * represents the remaining shield life of the snake
	 */
	long shieldLife = 0;
	
	/**
	 * true if snake is equipped with a magnet
	 */
	boolean isMagnet = false;
	
	/**
	 * to implement attraction
	 */
	int countMagnet = 0;
	
	/**
	 * to check if last game was over or not
	 */
	boolean newGame = true;
	
	/**
	 * score label
	 */
	static Text myScore;
	
	/**
	 * to check if bomb was touched
	 */
	boolean isBomb = false ;
	
	/**
	 * to make the snake unable to move through walls
	 */
	boolean lock = false ;
	
	/**
	 * bonus feature
	 */
	static boolean devilMode = false;
	
	static Button exitButton = new Button("Home (Press W to Play)");
	
	
	AudioClip audioClipExplosion = new AudioClip(Paths.get("explosionSound.m4a").toUri().toString());
	AudioClip audioClipCoin = new AudioClip(Paths.get("coinSound.m4a").toUri().toString());
	AudioClip audioClipRat = new AudioClip(Paths.get("ratSound.m4a").toUri().toString());
	AudioClip audioClipShield = new AudioClip(Paths.get("shieldSound.m4a").toUri().toString());
	AudioClip audioClipBlock = new AudioClip(Paths.get("blockSound.m4a").toUri().toString());
	AudioClip audioClipMagnet = new AudioClip(Paths.get("magnetSound.m4a").toUri().toString());
	
	/**
	 * to save the current state of the game
	 */
	public static void serialize() {
		String[] arr = new String[4];
		arr[0] = nameInputPage.textField.getText();
		arr[1] = Integer.toString(score);
		arr[2] = Integer.toString(snake.length);
		arr[3] = Double.toString(snake.snake.getLayoutX());
		
		ObjectOutputStream out = null ;
		try {
			out = new ObjectOutputStream(new FileOutputStream("gameState.txt"));
			out.writeObject(arr);
		}
		catch(Exception e) {}
	}
	
	/**
	 * to initialise a new game
	 */
	public static void newGame(Stage window) {
		
		window.setScene(nameInputPage.inputScene);
		while(snake.length < 5) {
			snake.incLength(1);
		}
		score = 0;
		myScore.setText("0");
		snake.snake.setLayoutX(0);
		
		
	}
	
	/**
	 * to retrieve a saved game
	 */
	public static void resumeGame(Stage window) {
		
		String[] arr = new String[4] ;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("gameState.txt"));
			arr = (String[])in.readObject() ;
		}
		catch(Exception e) {}
		score = Integer.parseInt(arr[1]);
		myScore.setText(Integer.toString(score));
		snake.snake.setLayoutX(Double.parseDouble(arr[3]));
		nameInputPage.textField.setText(arr[0]);
		if(snake.length > 1) {
			
			if(snake.length > Integer.parseInt(arr[2])) {
				while(snake.length != Integer.parseInt(arr[2])) {
					snake.decLength(1);
				}
			}
			else {
				while(snake.length != Integer.parseInt(arr[2])) {
					snake.incLength(1);
				}
			}
		}
		else {
			System.out.println("Last game over.");
			
		}
		
	}
	
	public gamePage(Stage window) throws FileNotFoundException {
		Group gamePlay = new Group();
		exitButton = new Button("Home (Press W to Play)");
		exitButton.setLayoutX(0);
		exitButton.setLayoutY(0);
		exitButton.setVisible(true);
		exitButton.setDisable(false);
		exitButton.setStyle("-fx-background-color: #51190F");
		exitButton.setTextFill(Color.WHITE);
		exitButton.setPrefSize(400, 100);
		exitButton.setFont(Font.font(30));
		exitButton.setLayoutX(100);
		exitButton.setLayoutY(125);
		
		myScore = new Text("0");
		myScore.setLayoutX(275);
		myScore.setLayoutY(50);
		myScore.setFont(Font.font(30));
		
		Color[] colorList = {Color.AQUA, Color.BLUEVIOLET, Color.DEEPPINK, Color.GREENYELLOW, Color.CADETBLUE, Color.ORANGE} ;
		Random r = new Random();
		
		int xCo = 0 ;
		for(int i=0;i<5;i++) {
			block b = new block(r.nextInt(10)+1,xCo,-60,colorList[r.nextInt(6)]);
			myBlocks.add(b);
			xCo += 120;
		}
		
		myRat = new rat(0, -414);
		
		Group thisSnake = snake.snake;
        Group blocks = new Group();
        myCoin = new coin(r.nextInt(500)+50, -230);
        myBomb = new bomb(r.nextInt(500)+50, -500);
        myShield = new shield(r.nextInt(500)+50, -500);
        myMagnet = new magnet(10, r.nextInt(500)+50, -500);
        		
        for(int j=0;j<5;j++) {
        	blocks.getChildren().add(myBlocks.get(j).solid);
        }
        
        whichToken = r.nextInt(3);
        if(whichToken==0) {
        	myBomb.solid.setVisible(true);
        	myMagnet.solid.setVisible(false);
        	myShield.solid.setVisible(false);
        }
        else if(whichToken==1) {
        	myBomb.solid.setVisible(false);
        	myMagnet.solid.setVisible(true);
        	myShield.solid.setVisible(false);
        }
        else if(whichToken==2) {
        	myBomb.solid.setVisible(false);
        	myMagnet.solid.setVisible(false);
        	myShield.solid.setVisible(true);
        }
        
      
        gamePlay.getChildren().addAll(myScore, thisSnake, blocks, myRat.solid, myCoin.solid, myBomb.solid, myShield.solid, myMagnet.solid, exitButton);
        
         	AnimationTimer translateTransition = new AnimationTimer() {
    		int speed = snake.length;
    		boolean sl = false;
    		boolean ml = false;
    		
    		@Override
            public void handle(long now) {
    			speed += 0.003 ;
    			serialize();  			
            	
    			boolean[] tf = {true,false} ;
                blocks.setLayoutY(blocks.getLayoutY() + speed);
                myCoin.solid.setLayoutY(myCoin.solid.getLayoutY() + speed);
                myBomb.solid.setLayoutY(myBomb.solid.getLayoutY() + speed);
                myShield.solid.setLayoutY(myShield.solid.getLayoutY() + speed);
                myMagnet.solid.setLayoutY(myMagnet.solid.getLayoutY() + speed);
                myRat.solid.setLayoutY(myRat.solid.getLayoutY() + speed);
                myRat.solid.setLayoutX(myRat.solid.getLayoutX() + speed * (r.nextFloat()+0.5));
                
                if (blocks.getLayoutY() > 661) {
                	blocks.setLayoutY(-60);
                    int xCur = 0;
                    for(int i=0;i<5;i++) {
                    		block newBlock = new block(r.nextInt(10)+1, xCur, -60, colorList[r.nextInt(6)] );
                    		blocks.getChildren().set(i, newBlock.solid);
                    		myBlocks.set(i, newBlock);
                    		int v = r.nextInt(2);
                    		blocks.getChildren().get(i).setVisible(tf[v]);
                    		myBlocks.get(i).myRect.setVisible(tf[v]);
                    		xCur += 120 ;
                    		
                    }
                    
                }
                if(myRat.solid.getLayoutY()>600) {
                	myRat.solid.relocate(0, blocks.getLayoutY() - 365);
                	shouldIncScoreRat = true ;
                	myRat.solid.setVisible(true);
                }
                
                if(myCoin.solid.getLayoutY()>600) {
                	myCoin.solid.relocate(r.nextInt(500)+50, blocks.getLayoutY() - 230);
                	shouldIncScoreCoin = true ;
                	myCoin.solid.setVisible(true);
                	
                }
                
                if(myMagnet.solid.getLayoutY()>600) {
                	countMagnet += 1;
                	myMagnet.solid.relocate(r.nextInt(500)+50, blocks.getLayoutY() - 500);
                	myBomb.solid.relocate(r.nextInt(500)+50, blocks.getLayoutY() - 500);
                	myShield.solid.relocate(r.nextInt(500)+50, blocks.getLayoutY() - 500);
                	if(countMagnet%2==1)
                		isMagnet = false ;
                	whichToken = r.nextInt(3);
                	if(whichToken==0) {
                     	myBomb.solid.setVisible(true);
                     	myMagnet.solid.setVisible(false);
                     	myShield.solid.setVisible(false);
                     }
                    else if(whichToken==1) {
                     	myBomb.solid.setVisible(false);
                     	myMagnet.solid.setVisible(true);
                     	myShield.solid.setVisible(false);
                     }
                    else if(whichToken==2) {
                     	myBomb.solid.setVisible(false);
                     	myMagnet.solid.setVisible(false);
                     	myShield.solid.setVisible(true);
                     }
                }
                
                //********Snake Collided with Destroyer(Bomb)********//
                
                if(snake.collideWithBomb(myBomb) && myBomb.solid.isVisible()) {
                	if(devilMode)	
                		audioClipExplosion.play();
                	myBomb.solid.setVisible(false);
                	for(int i=0;i<5;i++) {
                		myBlocks.get(i).iv.setVisible(true);
                		if(myBlocks.get(i).solid.isVisible()) {
                			score += Integer.parseInt(myBlocks.get(i).myText.getText());
                		}
                	}
                	myScore.setText(Integer.toString(score));
                }
                
                
                //********Snake Collided with Blocks********//
                
                int collision = snake.collideWithBlock(blocks, myBlocks);
                if( collision != -1 ) {
                	if( shieldLife == 0 && !myBlocks.get(0).iv.isVisible() ) {
                		if( myBlocks.get(collision).val < 9 ) {
                			int blockValue = Integer.parseInt(myBlocks.get(collision).myText.getText());
                			if(snake.length > blockValue) {
                				long ctr = 0;
                				if(blockValue>5) {
	                				while(ctr<500000000l) {
	                					ctr+=1;
	                				}
                				}
                				snake.decLength(blockValue);
                			}
                			else {
                				while(snake.length>1) {
                					snake.decLength(1);
                				}
                				leaderBoardPage.updateTable(nameInputPage.textField.getText(), Integer.toString(score));
                				window.setScene(leaderBoardPage.lbScene);
                				leaderBoardPage.homeButton.setText("Last Score :"+myScore.getText()+"\nClick For Home");
                				leaderBoardPage.homeButton.setFont(new Font("Arial", 20));
                				newGame = false ;
                				stop();
                			}
                			
	                		myBlocks.get(collision).setVisibility(false);
	                		if(devilMode)	
	                			audioClipBlock.play();
	                		
	                		
                		}
     
                	}
                	else {
                		myScore.setText(Integer.toString(Integer.parseInt(myScore.getText())+Integer.parseInt(myBlocks.get(collision).myText.getText())));
                		score += Integer.parseInt(myBlocks.get(collision).myText.getText()) ;
                	}
                
                	
                	
                }
                else {
                	lock = false ;
                }
                
                
                if( shieldLife > 0 ) {
                	shieldLife -= 1;
                }
                   
                //********Snake Collided with Coin********//

                if( snake.collideWithCoin(myCoin) && shouldIncScoreCoin ) {
                	if(devilMode)	
                		audioClipCoin.play();
                	shouldIncScoreCoin = false;
                	myScore.setText(Integer.toString(Integer.parseInt(myScore.getText())+1));
                	score += 1;
                	myCoin.solid.setVisible(false);
                }
                
                //********Snake Collided with Ball(Rat)********//

                if( snake.collideWithRat(myRat) && shouldIncScoreRat ) {
                	if(devilMode)	
                		audioClipRat.play();
                	shouldIncScoreRat = false;
                	myScore.setText(Integer.toString(Integer.parseInt(myScore.getText())+5));
                	score += 5;
                	snake.incLength(1);
                	myRat.solid.setVisible(false);
                	
                }
                
                if ( !sl && myShield.solid.isVisible() ) {
                	isShield = snake.collideWithShield(myShield);
	            	if ( isShield ) {
	            		if(devilMode)	
	            			audioClipShield.play();
	            		myShield.solid.setVisible(false);
	            		sl = true;
	            		shieldLife = 300;
	            	}
                }
                if( shieldLife == 0 ) {
                	sl = false;
                	isShield = false ;
                	shieldLife = 0;
                }
                
                if(snake.collideWithMagnet(myMagnet) && !isMagnet && myMagnet.solid.isVisible()) {
                	myMagnet.solid.setVisible(false);
                	if(devilMode)	
                		audioClipMagnet.play();
                	isMagnet = true;
                }
                if(isMagnet) {
                	
                	if(myCoin.solid.getLayoutX()-300 > snake.snake.getLayoutX()) {
                		while(myCoin.solid.getLayoutX()-300>snake.snake.getLayoutX()) {
                			myCoin.solid.setLayoutX(myCoin.solid.getLayoutX()-5);
                		}
                	}
                	else {
                		while(myCoin.solid.getLayoutX()-300<snake.snake.getLayoutX()) {
                			myCoin.solid.setLayoutX(myCoin.solid.getLayoutX()+5);
                		}
                	}
                }
                
                
            }  
        };
        
               
        Scene gamePage = new Scene(gamePlay, 600, 600);
        gamePage.setFill(Color.BURLYWOOD);

        
        gamePage.setOnKeyPressed(new EventHandler<KeyEvent>() {
            boolean mode = false;
            @Override
            public void handle(KeyEvent event) {
            	//*** Left Arrow Key ***//
            	
            	
                if (event.getCode().toString().equals("LEFT") && !lock) {
                	double newX = thisSnake.getLayoutX() - 40;
                	if(newX >-300)
                		thisSnake.setLayoutX(thisSnake.getLayoutX() - 40);
                }
                
            	//*** Right Arrow Key ***//
                else if (event.getCode().toString().equals("RIGHT") && !lock) {
                	double newX = thisSnake.getLayoutX() + 40;
                	if(newX<300)
                		thisSnake.setLayoutX(thisSnake.getLayoutX() + 40);
                }
                
            	//*** "W" Key Pressed in Play Mode***//
                else if ((mode) && (event.getCode().toString().equals("W"))) {
                    translateTransition.stop();
                    exitButton.setDisable(false);
                    exitButton.setVisible(true);
                    mode = false;
                    
                }
                
            	//*** "W" Key Pressed in Pause Mode***//
                else if ((!mode) && (event.getCode().toString().equals("W"))) {
                	translateTransition.start();
                	exitButton.setVisible(false);
                	exitButton.setDisable(true);
                	mode = true;
                	
                	exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            			@Override
            			public void handle(MouseEvent event) {
            				if (event.MOUSE_CLICKED != null) {
            					translateTransition.stop();
            					application.window.setScene(mainPage.mainScene);
            				}
            			}	
            		});
                }
            }
        });
        s = gamePage ;
	}
}