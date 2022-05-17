package mummymaze;

import agent.Action;
import agent.State;
import gui.GameArea;

import java.util.ArrayList;
import java.util.Arrays;

public class MummyMazeState extends State implements Cloneable
{
    public static final int SIZE = 13;

    private char[][] matrix;

    private int hero_line;
    private int hero_column;
    private int exit_line;
    private int exit_column;

    public MummyMazeState(char[][] matrix)
    {
        this.matrix = new char[SIZE][SIZE];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (matrix[i][j] == 'H') {
                    hero_line = i;
                    hero_column = j;
                }
            }
        }
    }

    public MummyMazeState(String matrix)
    {
        this.matrix = new char[SIZE][SIZE];
        String[] matrixLines = matrix.split("\n");

        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                this.matrix[i][j] = matrixLines[i].charAt(j);
                if (this.matrix[i][j] == 'H') {
                    hero_line = i;
                    hero_column = j;
                }
                if(this.matrix[i][j] == 'S')
                {
                    exit_line = i;
                    exit_column = j;
                }
            }
        }

        if(hero_line == exit_line && hero_column == exit_column)
        {
            //passed the level
            System.out.println("Level passed");
        }
        System.out.println(matrix);
    }

    @Override
    public void executeAction(Action action)
    {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveUp()
    {
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_above = hero_line - 2;
        int space_above = hero_line - 1;
        if(isValidPosition(block_above, hero_column))   //If the position exists on the matrix
        {
            //wall/door
            if(matrix[space_above][hero_column] == GameArea.state_abst.WALL_HORIZONTAL.getValue() ||
            matrix[space_above][hero_column] == GameArea.state_abst.DOOR_HORIZONTAL_CLOSED.getValue())
            {
                return false;
            }
        }
        else
        {
            return false;
        }

        return true;
    }

    public boolean canMoveDown()
    {
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_below = hero_line + 2;
        int space_below = hero_line + 1;
        if(isValidPosition(block_below, hero_column))   //If the position exists on the matrix
        {
            //wall/door
            if(matrix[space_below][hero_column] == GameArea.state_abst.WALL_HORIZONTAL.getValue() ||
            matrix[space_below][hero_column] == GameArea.state_abst.DOOR_HORIZONTAL_CLOSED.getValue())
            {
                return false;
            }
        }
        else
        {
            return false;
        }

        return true;
    }

    public boolean canMoveLeft()
    {
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_left = hero_column - 2;
        int space_left = hero_column - 1;
        if(isValidPosition(hero_line, block_left))   //If the position exists on the matrix
        {
            //wall/door
            if(matrix[hero_line][space_left] == GameArea.state_abst.WALL_VERTICAL.getValue() ||
            matrix[hero_line][space_left] == GameArea.state_abst.DOOR_VERTICAL_CLOSED.getValue())
            {
                return false;
            }
        }
        else
        {
            return false;
        }

        return true;
    }

    public boolean canMoveRight()
    {
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_right = hero_column + 2;
        int space_right = hero_column + 1;
        if(isValidPosition(hero_line, block_right))   //If the position exists on the matrix
        {
            //wall/door
            if(matrix[hero_line][space_right] == GameArea.state_abst.WALL_VERTICAL.getValue() ||
            matrix[hero_line][space_right] == GameArea.state_abst.DOOR_VERTICAL_CLOSED.getValue())
            {
                return false;
            }
        }
        else
        {
            return false;
        }

        return true;
    }

    public void moveUp()
    {
        int block_above = hero_line - 2;
        int space_above = hero_line - 1;

        matrix[block_above][hero_column] = 'H';
        matrix[hero_line][hero_column] = '.';

        hero_line = block_above;
    }

    public void moveDown()
    {
        int block_below = hero_line + 2;
        int space_below = hero_line + 1;

        matrix[block_below][hero_column] = 'H';
        matrix[hero_line][hero_column] = '.';

        hero_line = block_below;
    }

    public void moveLeft()
    {
        int block_left = hero_column - 2;
        int space_left = hero_column - 1;

        matrix[hero_line][block_left] = 'H';
        matrix[hero_line][hero_column] = '.';

        hero_column = block_left;
    }

    public void moveRight()
    {
        int block_right = hero_column + 2;
        int space_right = hero_column + 1;

        matrix[hero_line][block_right] = 'H';
        matrix[hero_line][hero_column] = '.';

        hero_column = block_right;
    }

    public char[][] getMatrix()
    {
        return this.matrix;
    }

    public int getNumLines() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public int getTileValue(int line, int column) {
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return 0;//state_matrix[line][column];
    }

    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MummyMazeState)) {
            return false;
        }

        MummyMazeState o = (MummyMazeState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public MummyMazeState clone() {
        return new MummyMazeState(this.matrix);
    }

    //Listeners
    private transient ArrayList<MummyMazeListener> listeners = new ArrayList<MummyMazeListener>(3);

    public synchronized void removeListener(MummyMazeListener l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(MummyMazeListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(MummyMazeEvent pe) {
        for (MummyMazeListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }
}
