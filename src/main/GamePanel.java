package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	// SCREEN SETTINGS
	final int originalTileSizes = 16; // 16x16 tile
	final int scale = 3;
	
	final int tileSize = originalTileSizes * scale;
	final int maxScrenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScrenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
}