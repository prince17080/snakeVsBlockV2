 // package snake_vs_block;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text; 
import javafx.stage.Stage;

public class mainPage {
	/** 
	 * mainScene is the Scene of the Main Page
	 * gamePlay is the Scene of the Game Page
	 */
	static Scene mainScene, gamePlay;

	/**
	 * img is used to view the Devil Image
	 */
	ImageView img;
	
	public mainPage(Stage window)  {
		try {
			img = new ImageView(new Image(new FileInputStream("skeleton.gif")));
			img.setLayoutY(125);
			img.setLayoutX(125);
			img.setVisible(false);
		} catch (FileNotFoundException e1) {}
		
		// startButton to Start New Game
		Button startButton = new Button("Start Game") ;
		startButton.setOnAction(e -> {window.setScene(nameInputPage.inputScene);});
 		startButton.setStyle("-fx-background-color: #51190F");
		startButton.setTextFill(Color.WHITE);
		startButton.setPrefSize(400, 100);
		startButton.setFont(Font.font(30));
		startButton.setLayoutX(100);
		startButton.setLayoutY(75);
		
		// resumeButton to Resume previous game if any. 
		Button resumeButton = new Button("Resume Last Game") ;
		resumeButton.setOnAction(e -> {
			if(gamePage.snake.length<=1){
				window.setScene(nameInputPage.inputScene);
			}
			else {
				gamePage.resumeGame(window);window.setScene(gamePage.s);
			}
			});
		
		resumeButton.setStyle("-fx-background-color: #51190F");
		resumeButton.setTextFill(Color.WHITE);
		resumeButton.setPrefSize(400, 100);
		resumeButton.setFont(Font.font(30));
		resumeButton.setLayoutX(100);
		resumeButton.setLayoutY(200);

		//lbButton to open Leader Board Page
 		Button lbButton = new Button("View Leaderboard") ;
		lbButton.setOnAction(e -> window.setScene(leaderBoardPage.lbScene));
		lbButton.setStyle("-fx-background-color: #51190F");
		lbButton.setTextFill(Color.WHITE);
		lbButton.setPrefSize(400, 100);
		lbButton.setFont(Font.font(30));
		lbButton.setLayoutX(100);
		lbButton.setLayoutY(325);
		
		//devilButton to start the Devil Mode.
		Button devilButton = new Button("Devil Mode") ;
		devilButton.setOnAction(e -> {
			if(gamePage.devilMode) {
				gamePage.devilMode = false;
				img.setVisible(false);
			}
			else {
				img.setVisible(true);
				gamePage.devilMode = true ;
			}
			
			});
		devilButton.setStyle("-fx-background-color: #51190F");
		devilButton.setTextFill(Color.WHITE);
		devilButton.setPrefSize(400, 100);
		devilButton.setFont(Font.font(30));
		devilButton.setLayoutX(100);
		devilButton.setLayoutY(450);
		
		//mainHead is the Title of the Game
		Text mainHead = new Text("Snake vs Block");
		mainHead.setFont(Font.font(50));
		mainHead.setLayoutX(120);
		mainHead.setLayoutY(60);
		
		
		Group gMain = new Group() ;
		gMain.getChildren().addAll(mainHead, startButton,resumeButton,lbButton, devilButton, img) ;
		
		
		mainScene = new Scene(gMain,600,600) ;
		mainScene.setFill(Color.BEIGE);
	}
}
