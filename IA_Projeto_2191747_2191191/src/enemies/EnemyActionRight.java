package enemies;

import agent.Action;
import mummymaze.MummyMazeState;
public class EnemyActionRight extends Action<MummyMazeState>{
	public Enemy trackingEnemy;
	public EnemyActionRight(Enemy trackingEnemy) {
		super(0);
		this.trackingEnemy = trackingEnemy;
	}
	@Override
	public void execute(MummyMazeState State) {
		trackingEnemy.MoveRight(State);
		State.setAction(this);
		
	}

	@Override
	public boolean isValid(MummyMazeState State) {
		return trackingEnemy.canMoveRight(State.getMatrix());
	}
	
}
