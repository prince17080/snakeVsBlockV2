 // package snake_vs_block;
import javafx.scene.control.*;
import javafx.scene.Group;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene; 
import javafx.scene.paint.Color;
import javafx.scene.text.Font; 
import javafx.stage.Stage;
import javafx.scene.text.*;


public class application extends Application {
	
	/**
	 * main window
	 */
	static Stage window;
	
	/**
	 * all the different pages
	 */
	Scene main, lBoard, gamePlay, inputPage;
	
	@Override
	public void start(Stage mainScreen) throws FileNotFoundException {
		window = mainScreen ;
		main = new mainPage(window).mainScene;
		inputPage = new nameInputPage(window).inputScene;
		gamePlay = new gamePage(window).s ;
		lBoard = new leaderBoardPage(window).lbScene ;
		window.setScene(main);
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args) ;
	}
}
