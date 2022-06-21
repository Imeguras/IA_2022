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
        state.addAction(this);
    }

    @Override
    public boolean isValid(MummyMazeState state){
		if(state.hero_dead){
			return false;
		}
		for (Enemy iterable_element : state.enemies){
			if(iterable_element.canKill(state)){
				return false;
			}
		}
        return state.canMoveUp();
    }
}