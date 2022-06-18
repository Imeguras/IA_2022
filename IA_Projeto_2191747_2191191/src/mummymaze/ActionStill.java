package mummymaze;


import agent.Action;
import enemies.Enemy;
import gui.GameArea;

public class ActionStill extends Action<MummyMazeState> {


    public ActionStill(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        state.setAction(this);
    }

    @Override
    public boolean isValid(MummyMazeState state){
		if(state.hero_dead){
			return false;
		}
		for (Enemy iterable_element : state.enemies) {

			if(iterable_element.canKill(state)){
				return false;
			}
		}
        return true;
    }
}

