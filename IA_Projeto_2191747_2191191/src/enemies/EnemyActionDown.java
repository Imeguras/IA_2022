package enemies;

import agent.Action;
import mummymaze.MummyMazeState;

public class EnemyActionDown extends Action<MummyMazeState> {
	public Enemy trackingEnemy;
	public EnemyActionDown(Enemy trackingEnemy) {
		super(0);
		this.trackingEnemy = trackingEnemy;
	}

	@Override
	public void execute(MummyMazeState State) {
		trackingEnemy.MoveDown(State);
		State.setAction(this);
	}

	@Override
	public boolean isValid(MummyMazeState State) {
		
		return trackingEnemy.canMoveDown(State.getMatrix());
	}
	
}
