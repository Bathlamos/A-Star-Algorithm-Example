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

    /**
     * Processes the click of the "Execute" button, and change of world.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == executeButton){
            //Execute the program
            MazeRunner solver = new MazeRunner();
            Point home = maze.getHomePosition();

            StringBuilder sb = new StringBuilder();
            Point winner = null;
            int numMoves = Integer.MAX_VALUE;
            for(Point smiley: maze.getSmileys()) {
                try {
                    //Execute A* for the smiley
                    List<Point> points = solver.execute(maze, (int) smiley.getX(), (int) smiley.getY(),
                            (int) home.getX(), (int) home.getY());
                    //Output the path for the smiley
                    sb.append(String.format("Smiley (%d, %d) found home via", (int) smiley.getX(), (int) smiley.getY()));
                    for(Point p: points)
                        sb.append(String.format("\n   (%d, %d) ", (int) p.getX(), (int) p.getY()));
                    sb.append("\nand expanding " + solver.getNumberOfNodesVisited() + " nodes\nwith a path of " + points.size() + " cells.\n\n");

                    if(winner == null || numMoves > points.size()){
                        winner = smiley;
                        numMoves = points.size();
                    }

                }catch(RuntimeException ex){
                    sb.append(String.format("The house can't be reached by smiley (%d, %d).\n",
                            (int) smiley.getX(), (int) smiley.getY()));
                }
            }

            sb.append(String.format("\nSmiley (%d, %d) is the winner,\nwith %d moves", (int) winner.getX(), (int) winner.getY(), numMoves));

            //Outputs the results
            execResults.setText(sb.toString());
            wrapper.validate();

        } else if(e.getSource() == mapSelector){
            //Switch map
            String map = (String) mapSelector.getSelectedItem();
            if(map.equals("Default"))
                maze.setMap(WorldGenerator.getDefaultWorld());
            else
                maze.setMap(WorldGenerator.getSecondWorld());
        }
    }
}