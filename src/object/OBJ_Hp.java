package object;

import Main.GamePanel;
import entity.Entity;


public class OBJ_Hp extends Entity {
    public OBJ_Hp(GamePanel gp) {
        super(gp);
        name = "Heart";
        image = setup("/objects/hp_full");
        image2 = setup("/objects/hp_half");
        image3 = setup("/objects/hp_blank");

    }
}
