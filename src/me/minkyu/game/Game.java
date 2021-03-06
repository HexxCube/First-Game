package me.minkyu.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JPanel {
	private static int width = 300;
	private static int height = 400;
	
	Ball ball = new Ball(this, width, height);
	Racket racket = new Racket(this, width, height);
	
	private int score = 0;
	private int highScore = 0;
	
	private Game() {
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				ball.keyPressed(e);
				racket.keyPressed(e);
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				racket.mouseMoved(e);
			}
		});
		
		setFocusable(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.setPreferredSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(game);
//		frame.pack();
		frame.setLocationRelativeTo(null);
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(5);
		}
	}
	
	private void restart() {
		score = 0;
		System.out.println("Game restarted.");
	}
	
	private void move() {
		ball.move();
		racket.move();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		ball.paint(g2d);
		racket.paint(g2d);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Consolas", Font.BOLD, 12));
		g2d.drawString("Score: " + String.valueOf(score), 10, 30);
		g2d.setColor(Color.BLUE);
		g2d.drawString("High Score: " + String.valueOf(highScore), 90, 30);
	}
	
	void changeScore() {
		score++;
	}
	
	void gameOver() {
		int ret = JOptionPane.showConfirmDialog(this, "Your score is " + score + ". Restart?", "Game Over", JOptionPane.YES_NO_OPTION);
		if (ret == JOptionPane.YES_OPTION) {
			if (score > highScore)
				highScore = score;
			ball.restart();
			restart();
		} else
			System.exit(ABORT);
		
	}
}