package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cave extends SuperObject{

    GamePanel gp;

    public OBJ_Cave(GamePanel gp) {
        name = "Cave";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cave.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
