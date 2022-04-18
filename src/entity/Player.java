package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH){
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResource("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResource("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResource("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResource("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResource("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResource("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResource("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResource("/player/boy_right_2.png"));
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setDefaultValues(){
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void Update() {
		if(keyH.upPressed == true ||
		   keyH.downPressed == true ||
		   keyH.leftPressed == true ||
		   keyH.rightPressed == true) {
			if(keyH.upPressed == true){
				direction = "up";
				y -= speed;
			}
			if(keyH.downPressed == true){
				direction = "down";
				y += speed;
			}
			if(keyH.leftPressed == true){
				direction = "left";
				x -= speed;
			}
			if(keyH.rightPressed == true){
				direction = "right";
				x += speed;
			}
			
			spriteCounter++;
			
			if(spriteCounter > 12){
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void Draw(Graphics2D g2) {
		BufferedImage image = null;
		switch (direction) {
		case "up":
			switch(spriteNum) {
			case 1:
				image = up1;
				break;
			case 2:
				image = up2;
				break;
			}
			break;
		case "down":
			switch(spriteNum) {
			case 1:
				image = down1;
				break;
			case 2:
				image = down2;
				break;
			}
			break;
		case "left":
			switch(spriteNum) {
			case 1:
				image = left1;
				break;
			case 2:
				image = left2;
				break;
			}
			break;
		case "right":
			switch(spriteNum) {
			case 1:
				image = right1;
				break;
			case 2:
				image = right2;
				break;
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}