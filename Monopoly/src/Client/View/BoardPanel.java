
package Client.View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardPanel extends JPanel{

    private BufferedImage image;

    public BoardPanel() {
       try {                
          image = ImageIO.read(new File("Assets/Board.png"));
       } catch (IOException ex) {
            System.out.println("Board not found");
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);          
    }

}
