// package snake_vs_block;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class snake {
	
	/**
	 *  length of the snake
	 */
	static int length;
	
	/**
	 *  snake
	 */
	Group snake ;
	
	/**
	 *  radius of 1 ball of the snake
	 */
	int radius;
	public snake() {
		length = 5;
		radius = 10;
		snake = new Group();
		
        Circle head = new Circle();
        head.setRadius(radius);
        head.setFill(Color.BLACK);
        head.setLayoutX(300);
        head.setLayoutY(400);
        

        Circle body1 = new Circle();
        body1.setRadius(radius);
        body1.setFill(Color.SADDLEBROWN);
        body1.setLayoutX(300);
        body1.setLayoutY(400+2*radius);

        Circle body2 = new Circle();
        body2.setRadius(radius);
        body2.setFill(Color.SADDLEBROWN);
        body2.setLayoutX(300);
        body2.setLayoutY(400+4*radius);

        Circle body3 = new Circle();
        body3.setRadius(radius);
        body3.setFill(Color.SADDLEBROWN);
        body3.setLayoutX(300);
        body3.setLayoutY(400 + 6*radius);
        
        Circle body4 = new Circle();
        body4.setRadius(radius);
        body4.setFill(Color.SADDLEBROWN);
        body4.setLayoutX(300);
        body4.setLayoutY(400 + 8*radius);

        snake.getChildren().addAll(head, body1, body2, body3, body4);

	}
	
	/**
	 *  check collision of the snake with a wall
	 *  
	 *  @param blocks Group of blocks to check collision
	 *  @param myBlocks Arraylist of the blocks
	 *  @return index of the block with which the snake collided in the arrayList
	 */
	public int collisionWithWall(Group blocks, ArrayList<block> myBlocks) {
		int c = collisionWithWall(blocks, myBlocks);
		if(c!=-1) {
			double blockBottom = blocks.getLayoutY()+60 ;
			double blockTop = blockBottom-60 ;
			double snakeHead = snake.getChildren().get(0).getLayoutY();
			double snakeBottom = Math.min(600, snakeHead + length*20) ;
			if(blockBottom >= snakeHead && blockTop <= snakeBottom) {
				return 1;
			}
			
		}
		return 0 ;
	}
	
	/**
	 *  check collision of the snake with a block
	 *  
	 *  @param blocks Group of blocks to check collision
	 *  @param myBlocks Arraylist of the blocks
	 *  @return index of the block with which the snake collided in the arrayList
	 */
	public int collideWithBlock(Group blocks, ArrayList<block> myBlocks) {
		int y = -1;
		double blockBottom = blocks.getLayoutY()+60 ;
		double blockTop = blockBottom-60 ;
		double snakeHead = snake.getChildren().get(0).getLayoutY();
		double snakeBottom = Math.min(600, snakeHead + length*20) ;
		if(blockBottom == snakeHead) {
    		
	    		
	    		if(snake.getLayoutX()<=300 && snake.getLayoutX()>=180) {
	    			y = 4;
	    		}
	    		else if(snake.getLayoutX()<180 && snake.getLayoutX()>=60) {
	    			y = 3;
	    		}
	    		else if(snake.getLayoutX()<60 && snake.getLayoutX() >= -60) {
	    			y = 2;
	    		}
	    		else if(snake.getLayoutX()<-60 && snake.getLayoutX() >= -180) {
	    			y = 1;
	    		}
	    		else {
	    			y = 0;
	    		}
	    		
	    		if(!myBlocks.get(y).myRect.isVisible()) {
	    			y=-1;
	    		}
	
		}
		
		return y;
	}
	
	/**
	 *  check collision of the snake with a coin
	 *  @param myCoin coin with which we have to check the collision
	 *  @return true if collision occured, else false
	 */
	public boolean collideWithCoin(coin myCoin) {
		if(snake.getBoundsInParent().intersects(myCoin.solid.getBoundsInParent()))
			return true;
		return false;
	}
	
	/**
	 *  check collision of the snake with a rat
	 *  @param myRat rat with which we have to check the collision
	 *  @return true if collision occured, else false
	 */
	public boolean collideWithRat(rat myRat) {
		if(snake.getBoundsInParent().intersects(myRat.solid.getBoundsInParent()))
			return true;
		return false;
	}
	
	/**
	 *  check collision of the snake with a shield
	 *  @param myShield shield with which we have to check the collision
	 *  @return true if collision occured, else false
	 */
	public boolean collideWithShield(shield myShield) {
		if(snake.getBoundsInParent().intersects(myShield.solid.getBoundsInParent()))
			return true;
		return false;
	}
	
	/**
	 *  check collision of the snake with a bomb
	 *  @param myBomb bomb with which we have to check the collision
	 *  @return true if collision occured, else false
	 */
	public boolean collideWithBomb(bomb myBomb) {
		if(snake.getBoundsInParent().intersects(myBomb.solid.getBoundsInParent()))
			return true;
		return false;
	}
	
	/**
	 *  check collision of the snake with a magnet
	 *  @param myMagnet magnet with which we have to check the collision
	 *  @return true if collision occured, else false
	 */
	public boolean collideWithMagnet(magnet myMagnet) {
		if(snake.getBoundsInParent().intersects(myMagnet.solid.getBoundsInParent()))
			return true;
		return false;
	}

	/**
	 *  increases the length of the snake
	 *  @param l increase length by l units
	 *  
	 */
	public void incLength(int l) {
		for(int i=0;i<l;i++) {
			Circle body = new Circle();
	        body.setRadius(radius);
	        body.setFill(Color.SADDLEBROWN);
	        body.setLayoutX(300);
	        body.setLayoutY(400 + 2*length*radius);
	        length+=1;
	        snake.getChildren().add(body);
		}
	}
	
	/**
	 *  decreases the length of the snake
	 *  @param l decrease length by l units
	 *  
	 */
	public void decLength(int l) {
		for(int i=0;i<l;i++) {
			snake.getChildren().remove(length-1);
			length -= 1;
		}
	}

}
