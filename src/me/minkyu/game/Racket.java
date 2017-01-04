package me.minkyu.game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Racket {
	private int x = 0;
	private int xa = 0;
	private Game game;
	
	public Racket(Game game) { // racket constructor
		this.game = game;
	}
	
	public void move() { // moves the racket
		int cursorX = (int) MouseInfo.getPointerInfo().getLocation().getX();
		
		if (x + xa > 0 && x + xa < game.getWidth() - 60)
			x += xa;
	}
	
	public void paint(Graphics2D g) {
		g.fillRect(x, 330, 60, 10);
	}
}
