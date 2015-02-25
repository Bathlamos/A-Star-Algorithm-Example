package cc.legault.csi4106.a2;

public class WorldGenerator {

    public static final char SMILEY = 'S',
        OBSTACLE = 'X',
        HOME = 'H',
        FREE = ' ';

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
