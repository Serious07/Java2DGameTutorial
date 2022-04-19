package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		GetTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void GetTileImage() {
		try {
			// GRASS
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/tiles/grass.png"));
			
			// WALL
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/wall.png"));
			tile[1].collision = true;
			
			// WATER
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/water.png"));
			tile[2].collision = true;
			
			// EARTH
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResource("/tiles/earth.png"));
			
			// TREE
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResource("/tiles/tree.png"));
			tile[4].collision = true;
			
			// SAND
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResource("/tiles/sand.png"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				String numbers[] = line.split(" ");
				while(col < gp.maxWorldCol) {
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}