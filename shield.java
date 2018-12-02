// package snake_vs_block;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Group;


import javafx.scene.image.*;
public class shield {
	
	/**
	 * remaining life of the shield
	 */
	float time;
	
	/**
	 * image for shield
	 */
	Image img;
	
	/**
	 * group for shield
	 */
	Group solid;
	public shield(int x, int y) throws FileNotFoundException {
		time = 5;
		FileInputStream f = new FileInputStream("shield.gif");
		img = new Image(f) ;
		ImageView iv = new ImageView(img);
		iv.setFitWidth(80);
		iv.setFitHeight(80);
		solid = new Group();
		solid.getChildren().addAll(iv) ;
		solid.relocate(x, y);
	}

}
