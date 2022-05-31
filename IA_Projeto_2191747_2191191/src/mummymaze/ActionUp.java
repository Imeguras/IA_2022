package mummymaze;

import agent.Action;
import enemies.Enemy;

public class ActionUp extends Action<MummyMazeState>{

    public ActionUp(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        state.moveUp();
        state.setAction(this);
    }

    @Override
    public boolean isValid(MummyMazeState state){
		for (Enemy iterable_element : Enemy.enemies)
		{
            iterable_element.updateState(state);
			if(iterable_element.canKill()){
				return false;
			}
		}
        return state.canMoveUp();
    }
}