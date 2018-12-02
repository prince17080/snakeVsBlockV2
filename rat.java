// package snake_vs_block;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.crypto.spec.IvParameterSpec;

import javafx.scene.Group;


import javafx.scene.image.*;
public class rat {
	
	/**
	 * image for rat
	 */
	Image img;
	
	/**
	 * group for rat
	 */
	Group solid;
	public rat(int x, int y) throws FileNotFoundException {
		FileInputStream f = new FileInputStream("rat.gif");
		img = new Image(f) ;
		ImageView iv = new ImageView(img);
		iv.setFitWidth(100);
		iv.setFitHeight(50);
		solid = new Group();
		solid.getChildren().addAll(iv) ;
		solid.relocate(x, y);
	}
	

}
