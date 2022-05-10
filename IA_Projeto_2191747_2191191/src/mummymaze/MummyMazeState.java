package mummymaze;

import agent.Action;
import agent.WhiteMummyState;
import showSolution.GameArea;

import java.util.ArrayList;
import java.util.Arrays;

public class MummyMazeState extends WhiteMummyState implements Cloneable
{
    //Define goal matrix/state as a winnable state (hero on top of exit)
    public static final int SIZE = 13;
    //String state = GameArea.state;
    private GameArea.state state_matrix[][];
    private int lineBlank;
    private int columnBlank;
    private int hero_line;
    private int hero_column;

    public MummyMazeState(String matrix, GameArea ga)
    {
        this.state_matrix = new GameArea.state[SIZE][SIZE];
        int f=0;
        for (int i = 0; i < getNumLines(); i++)
        {
            for (int j = 0; j < getNumColumns(); j++)
            {
                f++;
                GameArea.state state = Arrays.stream(GameArea.state.values()).anyMatch(k->(k.getValue() == matrix.charAt(f)));
                System.out.println(this.state_matrix[i][j].getValue());
            }
        }
    }

    @Override
    public void executeAction(Action action)
    {
        action.execute(this);
        firePuzzleChanged(null);
    }

    //Validate "goal state": a state where the hero is on top of the stairs
    public void validateIfLevelHasBeenPassed()
    {

    }

    public boolean canMoveUp()
    {
        return lineBlank != 0;
    }

    public boolean canMoveRight()
    {
        return columnBlank != matrix.length - 1;
    }

    public boolean canMoveDown()
    {
        return lineBlank != matrix.length - 1;
    }

    public boolean canMoveLeft()
    {
        return columnBlank != 0;
    }

    public void moveUp()
    {
        matrix[lineBlank][columnBlank] = matrix[--lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = 0;
    }

    public void moveRight()
    {
        matrix[lineBlank][columnBlank] = matrix[lineBlank][++columnBlank];
        matrix[lineBlank][columnBlank] = 0;
    }

    public void moveDown()
    {
        matrix[lineBlank][columnBlank] = matrix[++lineBlank][columnBlank];
        matrix[lineBlank][columnBlank] = 0;
    }

    public void moveLeft()
    {
        matrix[lineBlank][columnBlank] = matrix[lineBlank][--columnBlank];
        matrix[lineBlank][columnBlank] = 0;
    }

    //Heuristics
    public double computeTilesOutOfPlace(MummyMazeState finalState)
    {
        int h = 0;
        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < this.matrix[0].length; j++)
            {
                if(this.matrix[i][j] != 0 && this.matrix[i][j] != finalState.matrix[i][j])
                {
                    h++;
                }
            }
        }

        return h;
    }

    public double computeTileDistances(MummyMazeState finalState)
    {
        int h = 0;

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++)
            {
                if(this.matrix[i][j] != 0)
                {
                    //h += Math.abs(i - linesfinalMatrix[this.matrix[i][j]]) + Math.abs(j -
                    //colsfinalMatrix[this.matrix[i][j]]);
                }
            }
        }

        return h;
    }

    public double computeTileDistancesOptimized(MummyMazeState finalState)
    {
        int h = 0;

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++)
            {
                if(this.matrix[i][j] != 0)
                {

                }
            }
        }

        return h;
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
        return matrix[line][column];
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
        return new MummyMazeState(matrix);
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
