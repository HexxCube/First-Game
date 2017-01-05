package me.minkyu.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

class Racket {
	private static final int Y = 330;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	
	private int screenWidth;
	private	int x = 0;
	private int paused = 1;
	
	private Ball ball;
	
	Racket(Ball ball, int width) {
		screenWidth = width;
		this.ball = ball;
	}
	
	void move() {
		if (paused == -1) {
			x = ball.getX();
		}
	}
	
	void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'a') {
			paused *= -1;
		}
	}
	
	void paint(Graphics2D g) {
		g.fillRect(x, Y, WIDTH, HEIGHT);
	}
	
	Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, 1);
	}
	
	
	void mouseMoved(MouseEvent e) {
		if (paused == -1)
			return;
		x = e.getX() - WIDTH / 2;
		if (x < 0)
			x = 0;
		if (x > screenWidth - WIDTH) {
			x = screenWidth - WIDTH;
		}
	}
}
