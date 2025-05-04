package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Hp extends SuperObject{
    public OBJ_Hp(GamePanel gp) {
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/hp_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/hp_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/hp_blank.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
