import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;

// GamePanel 繼承自 JPanel 並實現 Runnable 接口，是遊戲的主要畫布，負責繪圖及遊戲邏輯
public class GamePanel extends JPanel implements Runnable { 
    // 設定遊戲畫布的寬度和高度
    public static final int WIDTH = 1280; // 遊戲畫布的寬度
    public static final int HEIGHT = 720; // 遊戲畫布的高度
    
    // 每秒畫面更新次數 (Frames Per Second)
    final int FPS = 60;

    // 用於運行遊戲循環的執行緒
    Thread gameThread;

    // 遊戲的主要邏輯管理器
    PlayManager pm;

    // 建構子，初始化 GamePanel
    public GamePanel() {
        // 設定畫布的首選大小為指定的寬度和高度
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        // 設定畫布的背景顏色為黑色
        this.setBackground(Color.black);
        
        // 設定為絕對佈局，內部組件的位置需手動設定
        this.setLayout(null);

        //KeyListener
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);

        // 初始化遊戲邏輯管理器
        pm = new PlayManager();
    }

    // 啟動遊戲的方法，創建並啟動遊戲執行緒
    public void launchGame() {
        gameThread = new Thread(this); // 將 GamePanel 作為執行緒的目標
        gameThread.start(); // 啟動執行緒，進入 run() 方法
    }

    // 重寫 Runnable 接口的 run() 方法，實現遊戲循環
    @Override
    public void run() {
        // 每幀的時間間隔（以奈秒為單位，1 秒 = 10^9 奈秒）
        double drawInterval = 1000000000 / FPS;
        double delta = 0; // 用於計算時間差
        long lastTime = System.nanoTime(); // 紀錄上一次的時間
        long currentTime;

        // 遊戲循環
        while (gameThread != null) {
            currentTime = System.nanoTime(); // 獲取當前時間
            delta += (currentTime - lastTime) / drawInterval; // 計算經過的幀數
            lastTime = currentTime; // 更新上一次的時間

            if (delta >= 1) { // 當累積幀數大於等於 1 時執行一次更新
                update(); // 更新遊戲邏輯
                repaint(); // 重繪畫面
                delta--; // 減少累積幀數
            }
        }
    }

    // 更新遊戲資訊或邏輯的方法，調用 PlayManager 的 update 方法
    private void update() {
        if(KeyHandler.pausePressed == false){
            pm.update(); // 當遊戲沒被暫停時，會更新遊戲顯示的資訊（如角色位置、遊戲狀態等）
        }
    }

    // 重寫 JPanel 的 paintComponent 方法，實現自定義繪圖
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // 呼叫父類方法，清空畫布
        Graphics2D g2 = (Graphics2D) g; // 將 Graphics 轉換為 Graphics2D，便於使用更高級的繪圖功能
        pm.draw(g2); // 調用 PlayManager 的繪圖方法，繪製遊戲內容
    }
}