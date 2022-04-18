package tile;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		
		GetTileImage();
	}
	
	public void GetTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/tiles/grass.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/wall.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/water.png"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, gp.tileSize * 2, 0, gp.tileSize, gp.tileSize, null);
	}
}