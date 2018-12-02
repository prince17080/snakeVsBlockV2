// package snake_vs_block;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class leaderBoardPage {
	
	/**
	 * leaderboard page
	 */
	static Scene lbScene ;
	
	/**
	 * data of the table
	 */
	static final ObservableList<Person> data = FXCollections.observableArrayList();
	
	/**
	 * table
	 */
	private TableView table = new TableView();
	
	/**
	 * name, score and date of the current player
	 */
	Label playerName;
	Label playerScore;
	Label currentDate;
	
	/**
	 * data of the table for serializing
	 */
	static ArrayList<String> dataList = new ArrayList<String>() ;
	
	/**
	 * Button for going back to home
	 */
	static Button homeButton = new Button("Home") ;
	
	public leaderBoardPage(Stage window) {
		deserialize();
		Group gBoard = new Group();
		Label label = new Label("          LEADER BOARD ");
		label.setFont(new Font("Arial", 30));
	
		
		table.setEditable(false);
		
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));

		TableColumn scoreCol = new TableColumn("Score");
		scoreCol.setMinWidth(100);
		scoreCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("score"));

		TableColumn dtCol = new TableColumn("Date");
		dtCol.setMinWidth(200);
		dtCol.setCellValueFactory(new PropertyValueFactory<Person, String>("date"));

		table.setItems(data);
		table.getColumns().addAll(nameCol, scoreCol, dtCol);
		
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table);
		vbox.setLayoutX(90);
		
		homeButton.setOnAction(e -> {window.setScene(mainPage.mainScene);});
		homeButton.setStyle("-fx-background-color: #51190F");
		homeButton.setTextFill(Color.WHITE);
		homeButton.setPrefSize(400, 75);
		homeButton.setFont(Font.font(30));
		homeButton.setLayoutX(100);
		homeButton.setLayoutY(500);
		
		gBoard.getChildren().addAll(vbox, homeButton);
		
		
		
		lbScene = new Scene(gBoard, 600, 600);
		lbScene.setFill(Color.BEIGE);
	}
	
	/**
	 * update table after every game
	 */
	public static void updateTable(String name, String score) {
		String date = new Date().toString() ;
		dataList.add(name);
		dataList.add(score);
		dataList.add(date);
		serialize();
		data.add(new Person(name, Integer.parseInt(score), date));
	}
	
	/**
	 * saving the state of the leaderboard page
	 */
	public static void serialize() {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("lbState.txt"));
			out.writeObject(dataList);
		}
		catch (Exception e) {}
	}
	
	/**
	 * retrieving the state of the leaderboard page
	 */
	public void deserialize() {
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream("lbState.txt"));
			dataList = (ArrayList<String>) in.readObject();
		}
		catch (Exception e) {}
		for (int i = 0; i < dataList.size(); i += 3) {
			data.add(new Person(dataList.get(i), Integer.parseInt(dataList.get(i+1)), dataList.get(i+2)));
		}
		
	}
	
}