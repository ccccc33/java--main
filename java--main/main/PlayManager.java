import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Random;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class PlayManager {
    //drawing the UI
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    //Mino
    Mino currentMino;
    final int MINO_START_X;
    final int MINO_START_Y;
    //新增下一個方塊Mino
    Mino nextMino;
    final int NEXTMINO_X;
    final int NEXTMINO_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();

    //others
    public static int dropInterval=60;

    public PlayManager(){
        left_x = (GamePanel.WIDTH/2)-(WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        MINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        //設下一個新方塊的x和y座標(設在右側，確保不會與當前方塊重疊)
        NEXTMINO_X = right_x + 175;
        NEXTMINO_Y = top_y + 500;

        //建立最初第一個方塊。用隨機選擇方法pickMino()
        currentMino =pickMino();
        currentMino.setXY(MINO_START_X,MINO_START_Y);
        //建立下一個新方塊
        nextMino = pickMino();
        nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
    }

    //隨機選擇方塊
    private Mino pickMino(){

        Mino mino =null;
        int i = new Random().nextInt(7);

        switch(i){
            case 0:mino = new Mino_L1();break;
            case 1:mino = new Mino_L2();break;
            case 2:mino = new Mino_Square();break;
            case 3:mino = new Mino_Bar();break;
            case 4:mino = new Mino_T();break;
            case 5:mino = new Mino_Z1();break;
            case 6:mino = new Mino_Z2();break;
        }
        return mino;
    }

    //更新遊戲狀態
    public void update(){
        //檢查目前方塊是否移動
        if(currentMino.active == false){
            //為false代表方塊不再繼續降落。staticBlocks用來儲存已降落到底部的方塊，變成遊戲的一部份
            staticBlocks.add(currentMino.b[0]);
            staticBlocks.add(currentMino.b[1]);
            staticBlocks.add(currentMino.b[2]);
            staticBlocks.add(currentMino.b[3]);

            currentMino.deactivating = false;

            //切換下一個方塊變為目前移動的方塊，並為新方塊設初始新座標
            currentMino = nextMino;
            currentMino.setXY(MINO_START_X, MINO_START_Y);
            //隨機建立新的下一個方塊
            nextMino = pickMino();
            nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
        }else{
            currentMino.update();
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x-4,top_y-4,WIDTH+8,HEIGHT+8);

        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x,y,200,200);
        g2.setFont(new Font("Arial",Font.PLAIN,30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT",x+60,y+60);

        if(currentMino != null){
            currentMino.draw(g2);
        }

        //畫下一個方塊nextMino
        nextMino.draw(g2);
        //畫static blocks
        for(int i = 0 ; i < staticBlocks.size() ; i++){
            staticBlocks.get(i).draw(g2);
        }

        // 當遊戲被暫停時（被按空白鍵時），螢幕上會顯示PAUSED的字
        g2.setColor(Color.yellow);  //字體顏色
        g2.setFont(g2.getFont().deriveFont(50f));  // 字體大小
        if(KeyHandler.pausePressed){
            x = left_x + 70;  // 字串顯示時的x座標
            y = top_y + 320;  // 字串顯示時的y座標
            g2.drawString("PAUSED" , x ,y);
        }
    }
}
