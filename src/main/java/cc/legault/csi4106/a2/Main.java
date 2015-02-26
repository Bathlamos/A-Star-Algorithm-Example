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
 * Launcher for the application. Initializes the GUI.
 */
public class Main {

    public static void main(String[] args){

        JFrame frame = new JFrame("CSI 4106 Assignment 2 -- Informed Search Algorithms");
        frame.setContentPane(new Window(new Maze(WorldGenerator.getDefaultWorld())).getWrapper());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 700));
        frame.pack();
        frame.setVisible(true);

    }

}