import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // 創建 JFrame 視窗
        JFrame window = new JFrame("Simple Tetris");

        // 設定視窗的行為：關閉視窗時終止程式
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 禁止調整視窗大小，確保遊戲畫面比例不變
        window.setResizable(false);

        // 創建遊戲畫布並將其添加到視窗中
        GamePanel gp = new GamePanel();
        window.add(gp);

        // 根據畫布大小調整視窗大小
        window.pack();

        // 將視窗置於螢幕正中央
        window.setLocationRelativeTo(null);

        // 顯示視窗
        window.setVisible(true);

        // 啟動遊戲迴圈
        gp.launchGame();
    }
}