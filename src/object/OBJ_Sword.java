package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Sword extends Entity {


    public OBJ_Sword(GamePanel gp) {

        super(gp);
        name = "Sword";
        down1 = setup("/objects/sword");
    }
}
