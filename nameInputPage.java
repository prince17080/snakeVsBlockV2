// package snake_vs_block;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class nameInputPage {
	/**
	 * page to take name input of the player
	 */
	static Scene inputScene;
	
	/**
	 * field to take name input of the player
	 */
	static TextField textField;
	
	public nameInputPage(Stage window) {
		
		Label name = new Label("Name : ");
		textField = new TextField();
		HBox box = new HBox();
		box.getChildren().addAll(name, textField);
		
		box.setLayoutX(50);
		box.setLayoutY(100);
		
		Button playButton = new Button("Let's Play") ;
		playButton.setOnAction(e -> {gamePage.newGame(window);window.setScene(gamePage.s);});
		playButton.setStyle("-fx-background-color: #51190F");
		playButton.setTextFill(Color.WHITE);
		playButton.setPrefSize(400, 100);
		playButton.setFont(Font.font(30));
		playButton.setLayoutX(100);
		playButton.setLayoutY(300);
		
		Group gInput = new Group();
		gInput.getChildren().addAll(box, playButton);
		
		inputScene = new Scene(gInput, 600, 600);
		inputScene.setFill(Color.BEIGE);
	}

}
