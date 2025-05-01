package object_tree;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MapObjectManager {
    GamePanel gp;
    MapObject[] objects;
    int mapObjectNum[][];


    public MapObjectManager(GamePanel gp){

        this.gp = gp;

        objects = new MapObject[20];
        mapObjectNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/object01.txt");
    }

    public void getTileImage() {

        try {

            objects[1] = new MapObject();
            objects[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects-tree/tree1.png")));
            objects[1].collision = true;

            objects[2] = new MapObject();
            objects[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects-tree/tree2.png")));
            objects[2].collision = true;

            objects[3] = new MapObject();
            objects[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects-tree/tree3.png")));
            objects[3].collision = true;

            objects[4] = new MapObject();
            objects[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects-tree/tree4.png")));
            objects[4].collision = true;

            objects[5] = new MapObject();
            objects[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects-tree/tree5.png")));
            objects[5].collision = true;

            objects[6] = new MapObject();
            objects[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects-tree/tree6.png")));

            objects[7] = new MapObject();
            objects[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects-tree/tree7.png")));

            objects[8] = new MapObject();
            objects[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects-tree/tree8.png")));

            objects[9] = new MapObject();
            objects[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects-tree/tree9.png")));




        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filaPath) {
        try {
            InputStream is = getClass().getResourceAsStream(filaPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapObjectNum[col][row] = num;
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

            int objectNum = mapObjectNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenY &&
                    objectNum != 0) {
                g2.drawImage(objects[objectNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;


            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;

                worldRow++;

            }
        }
    }
}
