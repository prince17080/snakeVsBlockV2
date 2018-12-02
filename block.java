 // package snake_vs_block;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.Group;


import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class block {
	
	/**
	 * value of the block
	 */
	int val;
	
	/**
	 * x coordinate of the block
	 */
	int x;
	
	/**
	 * y coordinate of the block
	 */
	int y;
	
	/**
	 * group of the block
	 */
	Group solid;
	
	/**
	 * body of the block
	 */
	Rectangle myRect;
	
	/**
	 * text of the block
	 */
	Text myText;
	
	/**
	 * explosion gif for when the snake is equipped with a bomb
	 */
	ImageView iv;
	public block(int d, int x, int y, Color c) {
		Random r = new Random();
		try {
			iv = new ImageView(new Image(new FileInputStream("explosion.gif")));
			iv.setFitWidth(180);
			iv.setFitHeight(180);
			iv.setVisible(false);
		}
		catch(Exception e) {
			
		}
		val = d ;
		if(d<9) {
			x =x ;y=y;
			Rectangle b1 = new Rectangle(120, 120);	
			Text t1 = new Text(Integer.toString(val));
			t1.setX(x+50);
	        t1.setY(15);
	        t1.setFill(Color.ANTIQUEWHITE);
	        t1.setFont(Font.font(30));
	        b1.setFill(c);
			b1.setX(x);
			b1.setY(y);
			iv.setX(x-20);
			iv.setY(y-20);
			myText = t1;
			myRect = b1 ;
			solid = new Group();
			solid.getChildren().addAll(b1, t1, iv) ;
		}
		else {
			x =x ;y=y;
			Rectangle b1 = new Rectangle(30, r.nextInt(100)+50);
			Text t = new Text("0");
			t.setVisible(false);
	        b1.setFill(Color.BLACK);
	        b1.relocate(x+60, y+135);
			myRect = b1 ;
			solid = new Group();
			solid.getChildren().addAll(b1, t) ;
			
		}
	}
	
	/**
	 * to set visibility of the block
	 */
	public void setVisibility(boolean b) {
		solid.setVisible(b);
	}
}
