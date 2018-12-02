 // package snake_vs_block;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Group;


import javafx.scene.image.*;
public class magnet {
	
	/**
	 * maximum distance for coin attraction
	 */
	int distance;
	
	/**
	 * coordinates of the magnet
	 */
	int x, y;
	
	/**
	 * image of the magnet
	 */
	Image img;
	
	/**
	 * group for magnet
	 */
	Group solid;
	
	public magnet(int d, int x, int y) throws FileNotFoundException {
		this.x = x;
		this.y = y;
		distance = d ;
		FileInputStream f = new FileInputStream("magnetImg.png");
		img = new Image(f) ;
		ImageView iv = new ImageView(img);
		iv.setFitWidth(50);
		iv.setFitHeight(50);
		solid = new Group();
		solid.getChildren().addAll(iv) ;
		solid.relocate(x, y);
	}

}
