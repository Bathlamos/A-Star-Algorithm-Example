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

/**
 * Main GUI Window. Build with IntelliJ GUI Builder.
 */
public class Window {

    private JComboBox sourceCity, destinationCity;
    private JButton executeButton;
    private JComboBox searchAlgorithm;
    private JPanel wrapper;
    private JLabel numberOfNodesGenerated;
    private JLabel maxNumberOfNodes;
    private JLabel path;

    /**
     * Constructor: binds the action listeners and instantiate the maze display
     */
    public Window(Maze maze){
        wrapper.add(maze, BorderLayout.CENTER);
    }

    /**
     * @return The top-level JPanel.
     */
    public JPanel getWrapper(){
        return wrapper;
    }

}