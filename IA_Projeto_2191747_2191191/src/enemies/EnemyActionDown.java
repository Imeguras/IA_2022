package enemies;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import agent.Action;
import mummymaze.MummyMazeState;

public class EnemyActionDown extends EnemyAction {

	public EnemyActionDown(Enemy trackingEnemy) {
		super(trackingEnemy);
		
	}

	@Override
	public void execute(MummyMazeState State) {
		trackingEnemy.MoveDown(State);
		State.addAction(this);
	}


	@Override
	public boolean isValid(MummyMazeState State) {
		
		return trackingEnemy.canMoveDown(State.getMatrix());
	}

	@Override
	public void removeRedundantMoves(ListIterator<EnemyAction> actions) {
		//Remove any EnemyAction that is of EnemyActionUp type
		while(actions.hasNext()) {
			EnemyAction action = actions.next();
			if(action instanceof EnemyActionUp) {
				actions.remove();
			}
		}
		rewindMoves(actions);
		
	}
	
	
}
