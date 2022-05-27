package mummymaze;

import agent.Action;
import enemies.Enemy;

public class ActionDown extends Action<MummyMazeState>{

    public ActionDown(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        state.moveDown();
		
        state.setAction(this);
    }

    @Override
    public boolean isValid(MummyMazeState state)
    {
		for (Enemy iterable_element : Enemy.enemies) {
			if(iterable_element.canKill()){
				return false;
			}
		}
        return state.canMoveDown();
    }
}