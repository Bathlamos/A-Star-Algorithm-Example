package cc.legault.csi4106.a2;

import javax.swing.*;
import java.awt.*;

public class Maze extends JPanel {

    private final char[][] map;
    private final int mapWidth, mapHeight;
    private final double padding = 0.1; // In percent

    public Maze(char[][] map){
        this.map = map;
        mapHeight = map.length;
        int width = 0;
        for(char[] c: map)
            if(c.length > width)
                width = c.length;
        mapWidth = width;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension panelDimensions = getSize();
        double panelWidth = panelDimensions.getWidth();
        double panelHeight = panelDimensions.getHeight();

        double zoomFactor = 1;

        if(mapWidth / panelWidth > mapHeight / panelHeight)
            zoomFactor = panelWidth * (1 - 2 * padding) / mapWidth;
        else
            zoomFactor = panelHeight * (1 - 2 * padding) / mapHeight;

        Dimension origin = new Dimension((int) ((panelWidth - mapWidth * zoomFactor) / 2),
                (int) ((panelHeight - mapHeight * zoomFactor) / 2));

        //Draw the background
        g.setColor(Color.WHITE);
        g.clearRect(0, 0, (int) panelWidth, (int) panelHeight);

        //Draw the board
        g.setColor(Color.GRAY);
        g.drawRect((int) origin.getWidth(), (int) origin.getHeight(),
                (int) (mapWidth * zoomFactor), (int) (mapHeight * zoomFactor));

    }
}
