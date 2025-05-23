package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, escapePressed;

    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.titleState) {
            if(code == KeyEvent.VK_W) {
                if(gp.ui.commandNum == 0) {
                    gp.ui.commandNum = 2;
                } else {
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_UP) {
                if(gp.ui.commandNum == 0) {
                    gp.ui.commandNum = 2;
                } else {
                    gp.ui.commandNum--;
                }
            }
            if(code == KeyEvent.VK_S) {
                if(gp.ui.commandNum == 2) {
                    gp.ui.commandNum = 0;
                } else {
                    gp.ui.commandNum++;
                }
            }
            if(code == KeyEvent.VK_DOWN) {
                if(gp.ui.commandNum == 2) {
                    gp.ui.commandNum = 0;
                } else {
                    gp.ui.commandNum++;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(3);
                }
                if(gp.ui.commandNum == 1) {
                    //LOAD GAME
                }
                if(gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        //PLAY STATE
        if(gp.gameState == gp.playState) {
            //MOVE
            if(code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            //ENTER
            if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            //ESCAPE
            if(code == KeyEvent.VK_ESCAPE) {
                escapePressed = true;
            }

            //PAUSE
            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }

            //DEBUG
            if(code == KeyEvent.VK_T) {
                if(checkDrawTime == false) {
                    checkDrawTime = true;
                } else {
                    checkDrawTime = false;
                }
            }
        }

        //PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }

        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
