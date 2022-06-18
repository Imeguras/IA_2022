package enemies;

import agent.Action;
import mummymaze.MummyMazeState;

public class EnemyActionUp extends Action<MummyMazeState> {
	public Enemy trackingEnemy;
	public EnemyActionUp(Enemy trackingEnemy) {
		super(0);
		this.trackingEnemy = trackingEnemy;
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
	
}
