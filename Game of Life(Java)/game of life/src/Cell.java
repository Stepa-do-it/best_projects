import javax.swing.*;
import java.awt.*;
public class Cell {
    public static JFrame frame = new JFrame("Cells Field");
    public static void drawCells(char[][] array) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int cellSize = 20; // Размер клетки
                int startX = 0;   // Начальная координата X вывода клеток
                int startY = 0;  // Начальная координата Y вывода клеток

                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array[i].length; j++) {
                        if (array[i][j] == '1') {

                            g.setColor(Color.BLUE);
                            g.fillRect(startX + j * cellSize, startY + i * cellSize, cellSize, cellSize);
                        } else if (array[i][j] == '0') {

                            g.setColor(Color.WHITE);
                            g.drawRect(startX + j * cellSize, startY + i * cellSize, cellSize, cellSize);
                        }
                    }
                }
            }
        };
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}



