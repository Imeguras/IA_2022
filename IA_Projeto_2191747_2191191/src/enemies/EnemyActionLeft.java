package enemies;

import java.util.LinkedList;
import java.util.ListIterator;

import agent.Action;
import mummymaze.MummyMazeState;
public class EnemyActionLeft extends EnemyAction{
	
	public EnemyActionLeft(Enemy trackingEnemy) {
		super(trackingEnemy);
	
	}
	@Override
	public void execute(MummyMazeState State) {
		trackingEnemy.MoveLeft(State);
		State.addAction(this);
		
	}

	@Override
	public boolean isValid(MummyMazeState State) {
		return trackingEnemy.canMoveLeft(State.getMatrix());
	}
	@Override
	public void removeRedundantMoves(ListIterator<EnemyAction> actions) {
		//Remove any EnemyAction that is of EnemyActionRight type
		while(actions.hasNext()) {
			EnemyAction action = actions.next();
			if(action instanceof EnemyActionRight) {
				actions.remove();
			}
		}
		rewindMoves(actions);
		
	}
	
}
