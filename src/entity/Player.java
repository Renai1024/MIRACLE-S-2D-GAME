package entity;

import Main.GamePanel;
import Main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(16,28,16,10);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 2;
        direction = "down";

        //PLAYER STATUS
        maxLife = 6;   //2 life = a heart
        life = maxLife;
    }

    public void getPlayerImage() {

        up1 = setup("/player/player_up1");
        up2 = setup("/player/player_up2");
        up3 = setup("/player/player_up3");
        up4 = setup("/player/player_up4");
        up5 = setup("/player/player_up5");
        up6 = setup("/player/player_up6");
        up7 = setup("/player/player_up7");
        up8 = setup("/player/player_up8");
        up9 = setup("/player/player_up9");
        up10 = setup("/player/player_up10");
        up11 = setup("/player/player_up11");
        up12 = setup("/player/player_up12");

        down1 = setup("/player/player_down1");
        down2 = setup("/player/player_down2");
        down3 = setup("/player/player_down3");
        down4 = setup("/player/player_down4");
        down5 = setup("/player/player_down5");
        down6 = setup("/player/player_down6");
        down7 = setup("/player/player_down7");
        down8 = setup("/player/player_down8");
        down9 = setup("/player/player_down9");
        down10 = setup("/player/player_down10");
        down11 = setup("/player/player_down11");
        down12 = setup("/player/player_down12");

        left1 = setup("/player/player_left1");
        left2 = setup("/player/player_left2");
        left3 = setup("/player/player_left3");
        left4 = setup("/player/player_left4");
        left5 = setup("/player/player_left5");
        left6 = setup("/player/player_left6");
        left7 = setup("/player/player_left7");
        left8 = setup("/player/player_left8");
        left9 = setup("/player/player_left9");
        left10 = setup("/player/player_left10");
        left11 = setup("/player/player_left11");
        left12 = setup("/player/player_left12");

        right1 = setup("/player/player_right1");
        right2 = setup("/player/player_right2");
        right3 = setup("/player/player_right3");
        right4 = setup("/player/player_right4");
        right5 = setup("/player/player_right5");
        right6 = setup("/player/player_right6");
        right7 = setup("/player/player_right7");
        right8 = setup("/player/player_right8");
        right9 = setup("/player/player_right9");
        right10 = setup("/player/player_right10");
        right11 = setup("/player/player_right11");
        right12 = setup("/player/player_right12");
    }


    public void update() {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if(keyH.upPressed) {
                direction = "up";

            }
            if (keyH.downPressed) {
                direction = "down";

            }
            if (keyH.leftPressed) {
                direction = "left";

            }
            if (keyH.rightPressed) {
                direction = "right";

            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.checker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.checker.checkObject(this,true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.checker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
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


    }



    public void pickUpObject(int i) {
        if(i != 999) {

        }
    }

    private void interactNPC(int i) {

        if(i != 999) {
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.GREEN);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
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

        g2.drawImage(image,screenX,screenY,null);

    }

}
