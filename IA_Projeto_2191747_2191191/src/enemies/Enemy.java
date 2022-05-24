package enemies;

import gui.GameArea;
import gui.PointDimension;
import mummymaze.MummyMazeState;

import java.util.LinkedList;

public abstract class Enemy
{
    protected PointDimension<Integer> enemy_position;
    protected MummyMazeState boardState;

    public static LinkedList<Enemy> enemies = new LinkedList<>();

    public boolean isValidPosition(int line, int column)
    {
        return line >= 0 && line < boardState.getMatrix().length && column >= 0 && column <
        boardState.getMatrix()[0].length;
    }

    public boolean canMoveLeft()
    {
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_left = enemy_position.col - 2;
        int space_left = enemy_position.col - 1;
        if(isValidPosition(enemy_position.line, block_left))   //If the position exists on the matrix
        {
            //wall/door
            if(boardState.getMatrix()[enemy_position.line][space_left] == GameArea.state_abst.WALL_VERTICAL
            || boardState.getMatrix()[enemy_position.line][space_left] == GameArea.state_abst.DOOR_VERTICAL_CLOSED)
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

    public void moveLeft(GameArea.state_abst symbol)
    {
        int block_left = enemy_position.col - 2;
        int space_left = enemy_position.col - 1;

        boardState.getMatrix()[enemy_position.line][block_left] = symbol;
        boardState.getMatrix()[enemy_position.line][enemy_position.col] = GameArea.state_abst.WALKABLE;

        enemy_position.col = block_left;
    }

    public boolean canMoveRight()
    {
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_right = enemy_position.col + 2;
        int space_right = enemy_position.col + 1;
        if(isValidPosition(enemy_position.line, block_right))   //If the position exists on the matrix
        {
            //wall/door
            if(boardState.getMatrix()[enemy_position.line][space_right] == GameArea.state_abst.WALL_VERTICAL
            || boardState.getMatrix()[enemy_position.line][space_right] == GameArea.state_abst.DOOR_VERTICAL_CLOSED)
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

    public Enemy(PointDimension<Integer> enemy_position, PointDimension<Integer> hero_position, String name)
    {
        this.enemy_position = enemy_position;
        this.name = name;
    }

    public PointDimension<Integer> getEnemy_position() {
        return enemy_position;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void move();
}
