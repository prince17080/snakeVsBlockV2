 // package snake_vs_block;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import javafx.scene.Group;


import javafx.scene.image.*;

public class coin {
	
	/**
	 * image of the coin
	 */
	Image img;
	
	/**
	 * group for the coin
	 */
	Group solid;
	public coin(int x, int y)  {
		FileInputStream f = null;
		try {
			f = new FileInputStream("coinImg.gif");
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		img = new Image(f) ;
		ImageView iv = new ImageView(img);
		iv.setFitWidth(50);
		iv.setFitHeight(50);
		solid = new Group();
		solid.getChildren().addAll(iv) ;
		solid.relocate(x, y);
		
	}

}
