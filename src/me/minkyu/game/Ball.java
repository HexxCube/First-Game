package me.minkyu.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Ball {
	private int x = 0;
	private int y = 0;
	private int xa = 5; // x velocity
	private int ya = 5; // y velocity
	
	private Game game;
	
	public Ball(Game game) { // ball constructor
		this.game = game;
	}
	
	public void move() { // moves the ball
		if (x + xa < 0)
			xa = Math.abs(xa);
		if (x + xa > game.getWidth() - 30)
			xa = -Math.abs(xa);
		if (y + ya < 0)
			ya = Math.abs(ya);
		if (y + ya > game.getHeight() - 30)
			ya = -Math.abs(ya);
		
		x += xa;
		y += ya;
	}
	
	public void paint(Graphics2D g) { // paints the ball
		g.fillOval(x, y, 30, 30);
	}
	
	public void keyPressed(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) {
	}
}