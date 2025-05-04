package entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1,up2,up3,up4,up5,up6,up7,up8,up9,up10,up11,up12,
                         down1,down2,down3,down4,down5,down6,down7,down8,down9,down10,down11,down12,
                         left1,left2,left3,left4,left5,left6,left7,left8,left9,left10,left11,left12,
                         right1,right2,right3,right4,right5,right6,right7,right8,right9,right10,right11,right12;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public String dialogues[] = new String[20];
    int dialogueIndex = 0;

    //CHARACTER STATUS
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}

    public void speak(){
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.checker.checkTile(this);
        gp.checker.checkObject(this, false);
        gp.checker.checkPlayer(this);

        if(!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        spriteCounter++;
        if(spriteCounter > 10) {
            if(spriteNum == 12) {
                spriteNum = 1;
            }else spriteNum++;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up" :
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}
                    if(spriteNum == 3) {image = up3;}
                    if(spriteNum == 4) {image = up4;}
                    if(spriteNum == 5) {image = up5;}
                    if(spriteNum == 6) {image = up6;}
                    if(spriteNum == 7) {image = up7;}
                    if(spriteNum == 8) {image = up8;}
                    if(spriteNum == 9) {image = up9;}
                    if(spriteNum == 10) {image = up10;}
                    if(spriteNum == 11) {image = up11;}
                    if(spriteNum == 12) {image = up12;}
                    break;
                case "down" :
                    if(spriteNum == 1) {image = down1;}
                    if(spriteNum == 2) {image = down2;}
                    if(spriteNum == 3) {image = down3;}
                    if(spriteNum == 4) {image = down4;}
                    if(spriteNum == 5) {image = down5;}
                    if(spriteNum == 6) {image = down6;}
                    if(spriteNum == 7) {image = down7;}
                    if(spriteNum == 8) {image = down8;}
                    if(spriteNum == 9) {image = down9;}
                    if(spriteNum == 10) {image = down10;}
                    if(spriteNum == 11) {image = down11;}
                    if(spriteNum == 12) {image = down12;}
                    break;
                case "left" :
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2) {image = left2;}
                    if(spriteNum == 3) {image = left3;}
                    if(spriteNum == 4) {image = left4;}
                    if(spriteNum == 5) {image = left5;}
                    if(spriteNum == 6) {image = left6;}
                    if(spriteNum == 7) {image = left7;}
                    if(spriteNum == 8) {image = left8;}
                    if(spriteNum == 9) {image = left9;}
                    if(spriteNum == 10) {image = left10;}
                    if(spriteNum == 11) {image = left11;}
                    if(spriteNum == 12) {image = left12;}
                    break;
                case "right" :
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2) {image = right2;}
                    if(spriteNum == 3) {image = right3;}
                    if(spriteNum == 4) {image = right4;}
                    if(spriteNum == 5) {image = right5;}
                    if(spriteNum == 6) {image = right6;}
                    if(spriteNum == 7) {image = right7;}
                    if(spriteNum == 8) {image = right8;}
                    if(spriteNum == 9) {image = right9;}
                    if(spriteNum == 10) {image = right10;}
                    if(spriteNum == 11) {image = right11;}
                    if(spriteNum == 12) {image = right12;}
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream( imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
