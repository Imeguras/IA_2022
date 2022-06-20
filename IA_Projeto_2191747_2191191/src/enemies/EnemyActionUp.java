package enemies;

import java.util.LinkedList;
import java.util.ListIterator;

import agent.Action;
import mummymaze.MummyMazeState;

public class EnemyActionUp extends EnemyAction {
	
	public EnemyActionUp(Enemy trackingEnemy) {
		super(trackingEnemy);
		
	}

	@Override
	public void execute(MummyMazeState State) {
		trackingEnemy.MoveUp(State);
		State.setAction(this);
	}

	@Override
	public boolean isValid(MummyMazeState State) {
		
		return trackingEnemy.canMoveUp(State.getMatrix());
	}

	@Override
	public void removeRedundantMoves(ListIterator<EnemyAction> actions) {
		//Remove any EnemyAction that is of EnemyActionDown type
		while(actions.hasNext()) {
			EnemyAction action = actions.next();
			if(action instanceof EnemyActionDown) {
				actions.remove();
			}
		}
		rewindMoves(actions);
		
	}

}
