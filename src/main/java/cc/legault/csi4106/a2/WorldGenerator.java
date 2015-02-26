/**
 * Philippe Legault - 6376254
 *
 * CSI 4106 - Artificial Intelligence I
 * University of Ottawa
 * February 2015
 */

package cc.legault.csi4106.a2;

/**
 * Contains worlds, as described in the assignment.
 */
public class WorldGenerator {

    /**
     * @return a 7x7 rectangle world with 4 smileys, and a home at the middle.
     */
    public static char[][] getDefaultWorld(){
        return new char[][]{
                {'S', ' ', ' ', 'X', ' ', ' ', 'S'},
                {' ', 'X', ' ', ' ', 'X', ' ', ' '},
                {' ', 'X', ' ', ' ', 'X', ' ', ' '},
                {' ', 'X', 'X', 'H', 'X', ' ', ' '},
                {' ', ' ', 'X', ' ', ' ', ' ', ' '},
                {' ', ' ', 'X', 'X', 'X', ' ', ' '},
                {'S', ' ', ' ', ' ', ' ', ' ', 'S'}};
    }

    /**
     * @return a 9x7 world with unusual shape, 4 smileys and a home at the middle.
     */
    public static char[][] getSecondWorld(){
        return new char[][]{
                {'S', ' ', ' ', 'X', ' ', ' ', ' ', ' ', 'S'},
                {' ', 'X', ' ', ' ', 'X', ' ', ' ', ' ', ' '},
                {' ', 'X', ' ', ' ', 'X', ' ', ' '},
                {' ', 'X', 'X', 'H', 'X', ' ', ' '},
                {' ', ' ', 'X', ' ', ' ', ' ', ' '},
                {' ', ' ', 'X', 'X', 'X', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'X', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'S'}};
    }

}
