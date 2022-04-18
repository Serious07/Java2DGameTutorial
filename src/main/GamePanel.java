package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	// SCREEN SETTINGS
	final int originalTileSizes = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSizes * scale;
	final int maxScrenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScrenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	int fps = 60;
	
	TileManager tileManager = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/fps;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null){
			long currentTime = System.nanoTime();
			
			timer += (nextDrawTime - currentTime);
			
			Update();
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				
				remainingTime = remainingTime / 1000000;
				
				if(remainingTime < 0){
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			drawCount++;
			if(timer >= 1000000000){
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void Update(){
		player.Update();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		tileManager.draw(g2);
		player.Draw(g2);
		
		g2.dispose();
	}
}