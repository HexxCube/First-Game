package me.minkyu.game;

import java.awt.*;
import java.awt.event.KeyEvent;

class Ball {
	private static final int DIAMETER = 30;
	private static final int INITVEL = 2;
	
	private boolean paused = false;
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int xa = INITVEL;
	private int ya = INITVEL;
	
	private Game game;
	
	Ball(Game game, int width, int height) {
		this.width = width;
		this.height = height;
		this.game = game;
	}
	
	void move() {
		if (paused)
			return;
		
		if (x + xa < 0) {
			xa = Math.abs(xa);
		}
		if (x + DIAMETER >= width) {
			xa = -Math.abs(xa);
		}
		if (y + ya < 0) {
			ya = Math.abs(ya);
		}
		if (y + DIAMETER >= height) {
			game.gameOver();
		}
		
		if (collision()) { // does not reach
			game.changeScore();
			ya = -Math.abs(ya);
			xa += xa / Math.abs(xa);
			ya += ya / Math.abs(ya);
		}
		
		x += xa;
		y += ya;
	}
	
	void paint(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	void restart() {
		x = (int) (width * Math.random());
		y = 0;
		int sign = 1;
		if (Math.random() - 0.5 < 0)
			sign = -1;
		xa = INITVEL * sign;
		ya = INITVEL;
	}
	
	private boolean collision() {
		return game.racket.getBounds().intersects(getBounds());
	}
	
	void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'p')
			paused = !paused;
	}
	
	private Rectangle getBounds() {
		return new Rectangle(x, y + DIAMETER, DIAMETER, 1);
	}
	
	int getMiddleX() {
		return x + DIAMETER / 2;
	}
}