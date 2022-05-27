package mummymaze;

import agent.Action;
import enemies.Enemy;

public class ActionLeft extends Action<MummyMazeState>{

    public ActionLeft(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        state.moveLeft();
        state.setAction(this);
    }

    @Override
    public boolean isValid(MummyMazeState state){
		for (Enemy iterable_element : Enemy.enemies) {
			if(iterable_element.canKill()){
				return false;
			}
		}
        return state.canMoveLeft();
    }
}
