// package snake_vs_block;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Person {
	/**
	 *  name field for table
	 */
	private final SimpleStringProperty name;
	
	/**
	 *  score field for table
	 */
	private final SimpleIntegerProperty score;
	
	/**
	 *  date field for table
	 */
	private final SimpleStringProperty date;

	public Person(String name, int score, String dt) {
		this.name = new SimpleStringProperty(name);
		this.score = new SimpleIntegerProperty(score);
		this.date = new SimpleStringProperty(dt);
	}

	public String getName() {
		return name.get();
	}
	
	public int getScore() {
		return score.get();
	}
	
	public String getDate() {
		return date.get();
	}
}