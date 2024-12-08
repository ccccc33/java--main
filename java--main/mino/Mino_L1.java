import java.awt.Color;

// 定義 Mino_L1 類，繼承自 Mino，表示一個特定形狀的俄羅斯方塊（L 型）
public class Mino_L1 extends Mino {
    
    // 建構子，初始化 L 型方塊的顏色為橘色
    public Mino_L1() {
        create(Color.orange); // 調用父類的 create 方法，建立 L 型方塊並設定顏色
    }

    // 設定 L 型方塊的初始位置
    // 參數 x 和 y 為基準方塊的左上角坐標
    @Override
    public void setXY(int x, int y) {
        // L 型結構：
        // o
        // o
        // o o
        // 方塊的相對位置如下：
        // b[0] 是基準方塊
        b[0].x = x;           // 設定基準方塊 b[0] 的 x 坐標
        b[0].y = y;           // 設定基準方塊 b[0] 的 y 坐標

        // b[1] 位於基準方塊的正上方
        b[1].x = b[0].x;      // b[1] 的 x 坐標與基準方塊相同
        b[1].y = b[0].y - Block.SIZE; // b[1] 的 y 坐標為基準方塊的 y 坐標減去一個方塊的大小

        // b[2] 位於基準方塊的正下方
        b[2].x = b[0].x;      // b[2] 的 x 坐標與基準方塊相同
        b[2].y = b[0].y + Block.SIZE; // b[2] 的 y 坐標為基準方塊的 y 坐標加上一個方塊的大小

        // b[3] 位於基準方塊的右下方
        b[3].x = b[0].x + Block.SIZE; // b[3] 的 x 坐標為基準方塊的 x 坐標加上一個方塊的大小
        b[3].y = b[0].y + Block.SIZE; // b[3] 的 y 坐標為基準方塊的 y 坐標加上一個方塊的大小
    }
    public void getDirection1() {
        // o
        // o
        // o o
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x;
        tempB[1].y=b[0].y - Block.SIZE;
        tempB[2].x=b[0].x;
        tempB[2].y=b[0].y + Block.SIZE;
        tempB[3].x=b[0].x + Block.SIZE;
        tempB[3].y=b[0].y + Block.SIZE;

        updateXY(1);
    }
    public void getDirection2() {
        //
        // o o o
        // o
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x + Block.SIZE;
        tempB[1].y=b[0].y;
        tempB[2].x=b[0].x - Block.SIZE;
        tempB[2].y=b[0].y;
        tempB[3].x=b[0].x - Block.SIZE;
        tempB[3].y=b[0].y + Block.SIZE;

        updateXY(2);
    }
    public void getDirection3() {
        // o o
        //   o
        //   o
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x;
        tempB[1].y=b[0].y + Block.SIZE;
        tempB[2].x=b[0].x;
        tempB[2].y=b[0].y - Block.SIZE;
        tempB[3].x=b[0].x - Block.SIZE;
        tempB[3].y=b[0].y - Block.SIZE;

        updateXY(3);
    }
    public void getDirection4() {
        //     o
        // o o o
        //  
        tempB[0].x=b[0].x;
        tempB[0].y=b[0].y;
        tempB[1].x=b[0].x - Block.SIZE;
        tempB[1].y=b[0].y;
        tempB[2].x=b[0].x + Block.SIZE;
        tempB[2].y=b[0].y;
        tempB[3].x=b[0].x + Block.SIZE;
        tempB[3].y=b[0].y - Block.SIZE;

        updateXY(4);
    }
}