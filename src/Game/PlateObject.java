package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public class PlateObject implements GameObject{
	public static final int SPRITE_WIDTH = 40;
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	private boolean left;
	private boolean top;
	public boolean visible;
        Color color;
        
        public void setVisible(boolean visible){
		this.visible = visible;
	}
        
	public PlateObject(Color color){
		this.x = (int) (Math.random() * 500);
		this.y = (int) (Math.random() * 100);
		this.left = true;
		this.top = true;
		this.visible = true;
                this.color=color;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		spriteImages[0] = new BufferedImage(SPRITE_WIDTH, SPRITE_WIDTH,	BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = spriteImages[0].createGraphics();
		g2.setColor(color);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		double x = SPRITE_WIDTH/2;
		double y = SPRITE_WIDTH/2;
		int x1 = (int) ((SPRITE_WIDTH / 2.0) - x);
		int y1 = (int) ((SPRITE_WIDTH / 2.0) - y);
		int x2 = (int) ((SPRITE_WIDTH / 2.0) + x);
		int y2 = (int) ((SPRITE_WIDTH / 2.0) + y);
		g2.setStroke(new BasicStroke(3));
		g2.fillRect(x1+3, y1+3, x2-6, (y2-6)/3);
		g2.setColor(color);
		//g2.drawArc(x1+12, y1+8, x2-22, y2-22, 5, 80);
		g2.dispose();
	}

    public void setColor(Color color) {
        this.color = color;
    }
        
        public Color getColor(){
            return color;
        }

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int mY) {
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return SPRITE_WIDTH;
	}

	@Override
	public int getHeight() {
		return SPRITE_WIDTH/3;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

}
