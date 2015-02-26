/**
 * Philippe Legault - 6376254
 *
 * CSI 4106 - Artificial Intelligence I
 * University of Ottawa
 * February 2015
 */

package cc.legault.csi4106.a2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Main GUI Window. Build with IntelliJ GUI Builder.
 */
public class Window implements ActionListener{

    private final Maze maze;
    private JComboBox mapSelector;
    private JButton executeButton;
    private JPanel wrapper;
    private JTextArea obstaclesAreInNavyTextArea;
    private JTextArea execResults;

    /**
     * Constructor: binds the action listeners and instantiate the maze display
     */
    public Window(Maze maze){
        this.maze = maze;
        wrapper.add(maze, BorderLayout.CENTER);

        mapSelector.addItem("Default");
        mapSelector.addItem("Second Map");

        mapSelector.addActionListener(this);
        executeButton.addActionListener(this);
    }

    /**
     * @return The top-level JPanel.
     */
    public JPanel getWrapper(){
        return wrapper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == executeButton){
            MazeRunner solver = new MazeRunner();
            Point home = maze.getHomePosition();

            StringBuilder sb = new StringBuilder();
            for(Point smiley: maze.getSmileys()) {
                try {
                    List<Point> points = solver.execute(maze, (int) smiley.getX(), (int) smiley.getY(),
                            (int) home.getX(), (int) home.getY());

                    sb.append(String.format("Smiley (%d, %d) found home via", (int) smiley.getX(), (int) smiley.getY()));
                    for(Point p: points)
                        sb.append(String.format("\n   (%d, %d) ", (int) p.getX(), (int) p.getY()));
                    sb.append("\nand expanding " + solver.getNumberOfNodesVisited() + " nodes.\n\n");

                }catch(RuntimeException ex){
                    sb.append(String.format("The house can't be reached by smiley (%d, %d).\n",
                            (int) smiley.getX(), (int) smiley.getY()));
                }
            }

            execResults.setText(sb.toString());
            wrapper.validate();

        } else if(e.getSource() == mapSelector){
            String map = (String) mapSelector.getSelectedItem();
            if(map.equals("Default"))
                maze.setMap(WorldGenerator.getDefaultWorld());
            else
                maze.setMap(WorldGenerator.getSecondWorld());
        }
    }
}