import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

// 定義 Block 類，繼承自 Rectangle，表示俄羅斯方塊中的單一方塊
public class Block extends Rectangle {
    public int x, y; // 方塊的座標（左上角位置）
    public static final int SIZE = 30; // 方塊的固定大小（寬和高都是 30 像素）
    public Color c; // 方塊的顏色

    // 建構子，用於初始化方塊，並指定顏色
    public Block(Color c) {
        this.c = c; // 設定方塊顏色
    }

    // 繪製方塊的方法
    public void draw(Graphics2D g2) {
        // 為方塊與方塊之間有線條感，而非完全緊靠
        int margin = 2;
        // 設定繪圖顏色為方塊的顏色
        g2.setColor(c);
        // 在給定位置繪製矩形方塊
        g2.fillRect(x+margin, y+margin, SIZE-(margin*2), SIZE-(margin*2));
    }
}