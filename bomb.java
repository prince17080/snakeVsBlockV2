 // package snake_vs_block;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Group;


import javafx.scene.image.*;
public class bomb {
	
	/**
	 * remaining life of the bomb
	 */
	float time;
	
	/**
	 * image for the bomb
	 */
	Image img;
	
	/**
	 * group of the bomb
	 */
	Group solid;
	public bomb(int x, int y) throws FileNotFoundException {
		time = 5;
		FileInputStream f = new FileInputStream("bomb.gif");
		img = new Image(f) ;
		ImageView iv = new ImageView(img);
		iv.setFitWidth(50);
		iv.setFitHeight(50);
		solid = new Group();
		solid.getChildren().addAll(iv) ;
		solid.relocate(x, y);
	}

}
