package me.minkyu.game;

import java.awt.*;
import java.awt.event.KeyEvent;

class Ball {
	private static final int DIAMETER = 30;
	
	private int x = 0;
	private int y = 0;
	private int xa = 4;
	private int ya = 4;
	private int savedX;
	private int savedY;
	private int paused = 1;
	private int width;
	private int height;
	
	private Game game;
	
	Ball(Game game, int width, int height) {
		this.width = width;
		this.height = height;
		this.game = game;
	}
	
	void move() {
		if (paused == -1)
			return;
		
		if (x + xa < 0)
			xa = Math.abs(xa);
		if (x + xa > width - DIAMETER)
			xa = -Math.abs(xa);
		if (y + ya < 0)
			ya = Math.abs(ya);
		if (y + ya > height - DIAMETER)
			game.gameOver();
		if (isCollision()) {
			game.changeScore();
			ya = -Math.abs(ya);
			xa += xa/Math.abs(xa);
			ya += ya/Math.abs(ya);
		}
		
		x += xa;
		y += ya;
	}
	
	void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	void restart() {
		x = width / (int) (10 * Math.random() + 1);
		y = 0;
		xa = 4;
		ya = 4;
	}
	
	void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'p') {
			paused *= -1;
		}
	}
	
	private boolean isCollision() {
		return game.racket.getBounds().intersects(getBounds());
	}
	
	private Rectangle getBounds() {
		return new Rectangle(x, y + DIAMETER, DIAMETER, 1);
	}
	
	public int getX() {
		return x;
	}
}