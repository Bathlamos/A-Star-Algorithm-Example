package cc.legault.csi4106.a2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Maze extends JPanel implements MouseListener, MouseMotionListener {

    public static final char SMILEY = 'S',
            OBSTACLE = 'X',
            HOME = 'H',
            EMPTY = ' ';

    private static final Color OBSTACLE_COLOR = new Color(44, 62, 80),
            SMILEY_COLOR = new Color(241, 196, 15),
            SMILEY_HOVER_COLOR = new Color(255, 230, 31),
            EMPTY_COLOR = new Color(236, 240, 241),
            EMPTY_HOVER_COLOR = new Color(214, 218, 219),
            HOME_COLOR = new Color(52, 152, 219);

    private final char[][] map;
    private final int mapWidth, mapHeight;
    private final double padding = 0.1; // In percent
    private Dimension origin;
    private Dimension mousePosition;
    private double zoomFactor;

    public Maze(char[][] map){
        this.map = map;
        mapHeight = map.length;
        int width = 0;
        for(char[] c: map)
            if(c.length > width)
                width = c.length;
        mapWidth = width;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Dimension panelDimensions = getSize();
        double panelWidth = panelDimensions.getWidth();
        double panelHeight = panelDimensions.getHeight();

        if(mapWidth / panelWidth > mapHeight / panelHeight)
            zoomFactor = panelWidth * (1 - 2 * padding) / mapWidth;
        else
            zoomFactor = panelHeight * (1 - 2 * padding) / mapHeight;

        origin = new Dimension((int) ((panelWidth - mapWidth * zoomFactor) / 2),
                (int) ((panelHeight - mapHeight * zoomFactor) / 2));

        //Draw the background
        g2.setColor(Color.WHITE);
        g2.clearRect(0, 0, (int) panelWidth, (int) panelHeight);

        //Set font settings
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(new Font(g2.getFont().getFontName(), Font.PLAIN, 25));
        FontMetrics fm = g.getFontMetrics();

        //Draw the board
        double cellDimen = zoomFactor * mapWidth / map.length;
        boolean hovering = false;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                char type = map[i][j];
                switch(type){
                    case SMILEY: g2.setColor(SMILEY_COLOR);
                        break;
                    case HOME: g2.setColor(HOME_COLOR);
                        break;
                    case OBSTACLE: g2.setColor(OBSTACLE_COLOR);
                        break;
                    case EMPTY:
                    default:g2.setColor(EMPTY_COLOR);
                        break;
                }

                int anchorX = (int) (origin.getWidth() + cellDimen * j);
                int anchorY = (int) (origin.getHeight() + cellDimen * i);

                //Check if we're hovering an empty cell
                if((type == EMPTY || type == SMILEY) && mousePosition != null &&
                        mousePosition.getWidth() == i && mousePosition.getHeight() == j){

                    g2.setColor(type == SMILEY? SMILEY_HOVER_COLOR: EMPTY_HOVER_COLOR);
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    hovering = true;
                }

                g2.fillRect(1 + anchorX, 1 + anchorY, (int) cellDimen - 2, (int) cellDimen - 2);

                String text = null;
                if(type == SMILEY)
                    text = "☻";
                else if(type == HOME)
                    text = "⌂";

                if(text != null) {
                    g2.setColor(Color.white);
                    int x = ((int) cellDimen - fm.stringWidth(text)) / 2;
                    int y = (fm.getAscent() + ((int) cellDimen - (fm.getAscent() + fm.getDescent())) / 2);
                    g2.drawString(text, anchorX + x, anchorY + y);
                }
            }
        }

        if(!hovering)
            setCursor(Cursor.getDefaultCursor());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mousePosition = getMapCell(e.getX(), e.getY());
        if(mousePosition != null) {
            int i = (int) mousePosition.getWidth();
            int j = (int) mousePosition.getHeight();
            if(map[i][j] == SMILEY)
                map[i][j] = EMPTY;
            else if(map[i][j] == EMPTY)
                map[i][j] = SMILEY;
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = getMapCell(e.getX(), e.getY());
        repaint();
    }

    private Dimension getMapCell(double x, double y){
        int i = (int) ((x - origin.getWidth()) / zoomFactor);
        int j = (int) ((y - origin.getHeight()) / zoomFactor);
        if(i < 0 || i >= mapWidth)
            return null;
        else if(j < 0 || j >= map[i].length)
            return null;
        return new Dimension(j, i);
    }
}
