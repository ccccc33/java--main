import java.awt.Color;
import java.awt.Graphics2D;

// 定義 Mino 類，代表遊戲中的一個方塊組（俄羅斯方塊中的一個形狀）
public class Mino {
    // 定義一組主要的 Block 陣列，用於表示 Mino 的四個方塊
    public Block b[] = new Block[4];

    // 定義一組暫存的 Block 陣列，用於臨時存儲方塊數據（例如旋轉操作時）
    public Block tempB[] = new Block[4];
    int autoDropCounter = 0;
    public int direction = 1;

    boolean leftCollision , rightCollision , bottomCollision;
    public boolean active = true;

    public boolean deactivating;
    int deactivateCounter = 0;

    // 建立一個 Mino，將四個方塊初始化為指定的顏色
    public void create(Color c) {
        // 使用指定的顏色初始化主要的 Block 陣列
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);

        // 使用相同的顏色初始化暫存的 Block 陣列
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }

    // 設定整個 Mino 的初始位置
    // 參數 x 和 y 指定 Mino 的位置，方法目前為空，需實現具體邏輯
    public void setXY(int x, int y) {}

    // 根據方向更新 Mino 的位置
    // 參數 direction 指定移動方向，例如向左或向右，方法目前為空，需實現具體邏輯
    public void updateXY(int direction) {

        checkRotationCollision(); //在方塊旋轉之前先呼叫方法，確認是否已碰撞到矩形邊界

        if(leftCollision == false && rightCollision == false && bottomCollision == false) {
            this.direction=direction;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }
    }
    public void getDirection1() {}
    public void getDirection2() {}
    public void getDirection3() {}
    public void getDirection4() {}

    // 當 Mino 移動或旋轉的時候確認遊戲中的碰撞檢測
    public void checkMovementCollision() {
        // 是否和遊戲中矩形的左邊、右邊、底部發生碰撞
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        checkStaticBlockCollision();

        // 矩形左邊
        for(int i = 0; i < b.length; i++){
            if(b[i].x == PlayManager.left_x){  //方塊的 x 座標等於 PlayManager.left_x 時，代表發生碰撞
                leftCollision = true;
            }
        }
        // 矩形右邊
        for(int i = 0; i < b.length; i++){
            if(b[i].x + Block.SIZE == PlayManager.right_x){
                rightCollision = true;
            }
        }
        // 矩形底部
        for(int i = 0; i < b.length; i++){
            if(b[i].y + Block.SIZE == PlayManager.bottom_y){
                bottomCollision = true;
            }
        }
    }


    public void checkRotationCollision() {
        
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        checkStaticBlockCollision();

        // 「旋轉後」是否和遊戲中矩形的左邊、右邊、底部發生碰撞
        // 矩形左邊
        for(int i = 0; i < b.length; i++){
            if(tempB[i].x < PlayManager.left_x){  //旋轉後的方塊的 x 座標小於遊戲矩形的左邊界 PlayManager.left_x 時，代表發生碰撞
                leftCollision = true;
            }
        }
        // 矩形右邊
        for(int i = 0; i < b.length; i++){
            if(tempB[i].x + Block.SIZE > PlayManager.right_x){
                rightCollision = true;
            }
        }
        // 矩形底部
        for(int i = 0; i < b.length; i++){
            if(tempB[i].y + Block.SIZE > PlayManager.bottom_y){
                bottomCollision = true;
            }
        }
    }

    //檢查目前移動的方塊和固定的方塊之間是否有碰撞，以讓他們彼此不會重疊到
    private void checkStaticBlockCollision(){
        for(int i = 0 ; i < PlayManager.staticBlocks.size() ; i++){
            int targetX = PlayManager.staticBlocks.get(i).x;
            int targetY = PlayManager.staticBlocks.get(i).y;

            //檢查目前移動方塊的下面是否和固定方塊發生碰撞
            for(int ii = 0; ii < b.length ; ii++){
                if(b[ii].y + Block.SIZE == targetY && b[ii].x == targetX){
                    bottomCollision = true;
                }
            }

            //檢查目前移動方塊的左側
            for(int ii = 0; ii < b.length ; ii++){
                if(b[ii].x - Block.SIZE == targetX && b[ii].y == targetY){
                    leftCollision = true;
                }
            }

            //檢查目前移動方塊的右側
            for(int ii = 0; ii < b.length ; ii++){
                if(b[ii].x + Block.SIZE == targetX && b[ii].y == targetY){
                    rightCollision = true;
                }
            }
        }
    }

    // 更新 Mino 的狀態
    // 方法目前為空，未具體實現邏輯，可用於更新遊戲邏輯（例如掉落或碰撞檢測）
    public void update() {

        if(deactivating){
            deactivating();
        }
        
        //移動方塊
        if(KeyHandler.upPressed){
            switch (direction){
                case 1:getDirection2();break;
                case 2:getDirection3();break;
                case 3:getDirection4();break;
                case 4:getDirection1();break;
            }
            KeyHandler.upPressed = false;
        }

        // 在往下、往左、往右之前，先確認是否撞到矩形邊界
        checkMovementCollision();


        if(KeyHandler.downPressed){
            
            // 如果 mino 還沒碰到底部則可以繼續往下
            if(bottomCollision == false){
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;

                autoDropCounter = 0;
            }
            KeyHandler.downPressed = false;
        }
        if(KeyHandler.leftPressed){
    
            if(leftCollision == false){
                b[0].x -= Block.SIZE;
                b[1].x -= Block.SIZE;
                b[2].x -= Block.SIZE;
                b[3].x -= Block.SIZE;

                KeyHandler.leftPressed = false;
            }
        }
        if(KeyHandler.rightPressed){
            if(rightCollision == false){
                b[0].x += Block.SIZE;
                b[1].x += Block.SIZE;
                b[2].x += Block.SIZE;
                b[3].x += Block.SIZE;

                KeyHandler.rightPressed = false;
            }
        }

        // 如果在底部碰撞，則方塊不會再自動降落;否則autoDropCounter++會增加，即方塊會自動降落
        if(bottomCollision){
            deactivating = true;
        }else{
            autoDropCounter++;
            if(autoDropCounter == PlayManager.dropInterval){
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                autoDropCounter=0;
            }
        }
        }

        //當方塊碰撞到底部時，不會立刻完全被固定住，仍能繼續其他移動
        private void deactivating(){
            //了解方塊在停用狀態時的持續時間
            deactivateCounter++;

            //等 45 frames 直到deactivate
            if(deactivateCounter == 45){  //等待大約0.75秒
                deactivateCounter = 0;
                checkMovementCollision();
                //檢查是否和底部發生碰撞
                if(bottomCollision){
                    active = false;
                }
            }
        }


    // 繪製 Mino 的方法
    // 接收 Graphics2D 物件作為參數，用於在畫布上繪製四個方塊
    public void draw(Graphics2D g2) {
        int margin = 2; // 定義方塊之間的間距，用於繪製邊框效果
        
        // 設定繪製顏色為方塊 b[0] 的顏色
        g2.setColor(b[0].c);

        // 繪製每個方塊，根據其位置 (x, y)，以及考慮到邊距
        g2.fillRect(b[0].x + margin, b[0].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(b[1].x + margin, b[1].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(b[2].x + margin, b[2].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        g2.fillRect(b[3].x + margin, b[3].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
    }
}