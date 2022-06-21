package enemies;

import java.util.LinkedList;
import java.util.ListIterator;

import agent.Action;
import mummymaze.MummyMazeState;
public class EnemyActionRight extends EnemyAction{
	
	public EnemyActionRight(Enemy trackingEnemy) {
		super(trackingEnemy);
	
	}
	@Override
	public void execute(MummyMazeState State) {
		trackingEnemy.MoveRight(State);
		State.addAction(this);
		
	}

	@Override
	public boolean isValid(MummyMazeState State) {
		return trackingEnemy.canMoveRight(State.getMatrix());
	}
	@Override
	public void removeRedundantMoves(ListIterator<EnemyAction> actions) {
		//Remove any EnemyAction that is of EnemyActionLeft type
		while(actions.hasNext()) {
			EnemyAction action = actions.next();
			if(action instanceof EnemyActionLeft) {
				actions.remove();
			}
		}
		rewindMoves(actions);
		
	}
	
}
