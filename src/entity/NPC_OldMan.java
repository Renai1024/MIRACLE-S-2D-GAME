package entity;

import Main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle(7,27,26,19);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        direction = "down";
        speed = 1;

        getImage();
    }

    public void getImage() {

        up1 = setup("/npc/OldMan_up1");
        up2 = setup("/npc/OldMan_up1");
        up3 = setup("/npc/OldMan_up2");
        up4 = setup("/npc/OldMan_up2");
        up5 = setup("/npc/OldMan_up3");
        up6 = setup("/npc/OldMan_up3");
        up7 = setup("/npc/OldMan_up4");
        up8 = setup("/npc/OldMan_up4");
        up9 = setup("/npc/OldMan_up5");
        up10 = setup("/npc/OldMan_up5");
        up11 = setup("/npc/OldMan_up6");
        up12 = setup("/npc/OldMan_up6");

        down1 = setup("/npc/OldMan_down1");
        down2 = setup("/npc/OldMan_down1");
        down3 = setup("/npc/OldMan_down2");
        down4 = setup("/npc/OldMan_down2");
        down5 = setup("/npc/OldMan_down3");
        down6 = setup("/npc/OldMan_down3");
        down7 = setup("/npc/OldMan_down4");
        down8 = setup("/npc/OldMan_down4");
        down9 = setup("/npc/OldMan_down5");
        down10 = setup("/npc/OldMan_down5");
        down11 = setup("/npc/OldMan_down6");
        down12 = setup("/npc/OldMan_down6");

        left1 = setup("/npc/OldMan_left1");
        left2 = setup("/npc/OldMan_left1");
        left3 = setup("/npc/OldMan_left2");
        left4 = setup("/npc/OldMan_left2");
        left5 = setup("/npc/OldMan_left3");
        left6 = setup("/npc/OldMan_left3");
        left7 = setup("/npc/OldMan_left4");
        left8 = setup("/npc/OldMan_left4");
        left9 = setup("/npc/OldMan_left5");
        left10 = setup("/npc/OldMan_left5");
        left11 = setup("/npc/OldMan_left6");
        left12 = setup("/npc/OldMan_left6");

        right1 = setup("/npc/OldMan_right1");
        right2 = setup("/npc/OldMan_right1");
        right3 = setup("/npc/OldMan_right2");
        right4 = setup("/npc/OldMan_right2");
        right5 = setup("/npc/OldMan_right3");
        right6 = setup("/npc/OldMan_right3");
        right7 = setup("/npc/OldMan_right4");
        right8 = setup("/npc/OldMan_right4");
        right9 = setup("/npc/OldMan_right5");
        right10 = setup("/npc/OldMan_right5");
        right11 = setup("/npc/OldMan_right6");
        right12 = setup("/npc/OldMan_right6");
    }

    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter == 240) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

}
