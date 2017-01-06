package me.minkyu.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

class Racket {
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	private static int width;
	private static int y;
	
	private boolean auto = false;
	private int x = 0;
	
	private Game game;
	
	Racket(Game game, int width, int height) {
		Racket.width = width;
		this.game = game;
		y = (int) (height * 0.8);
	}
	
	void move() {
		if (auto) {
			x = game.ball.getMiddleX() - WIDTH / 2;
		}
	}
	
	void paint(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	void mouseMoved(MouseEvent e) {
		if (auto)
			return;
		
		x = e.getX() - WIDTH / 2;
		if (x < 0)
			x = 0;
		if (x > width - WIDTH) {
			x = width - WIDTH;
		}
	}
	
	void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'a') {
			auto = !auto;
		}
	}
	
	Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, 5);
	}
}
