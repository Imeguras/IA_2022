package enemies;

import java.util.LinkedList;
import java.util.ListIterator;

import agent.Action;
import mummymaze.MummyMazeState;

public class EnemyActionQuiet extends EnemyAction {

	public EnemyActionQuiet(Enemy trackingEnemy) {
		super(trackingEnemy);
		
	}
	@Override
	public void execute(MummyMazeState state) {
		state.addAction(this);
		
	}
	@Override
	public boolean isValid(MummyMazeState state) {
		return true;
	}
	@Override
	public void removeRedundantMoves(ListIterator<EnemyAction> actions) {
		//remove any EnemyAction that is of EnemyActionQuiet type
		while(actions.hasNext()) {
			EnemyAction action = actions.next();
			if(action instanceof EnemyActionQuiet) {
				actions.remove();
			}
		}
		rewindMoves(actions);
	}
	
}
