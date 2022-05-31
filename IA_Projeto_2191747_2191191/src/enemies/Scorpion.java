package enemies;

import gui.GameArea;
import gui.PointDimension;
import mummymaze.MummyMazeState;

public class Scorpion extends Enemy
{
    public Scorpion(PointDimension<Integer> enemy_position, String name) {
        super(enemy_position, name);
    }

    @Override
    public boolean canKill() {
        return false;
    }

    @Override
    public MummyMazeState move() {
        return null;
    }

    @Override
    public GameArea.state_abst getSymbol() {
        return null;
    }
}
