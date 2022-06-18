package enemies;

import agent.Action;
import mummymaze.MummyMazeState;
public class EnemyActionLeft extends Action<MummyMazeState>{
	public Enemy trackingEnemy;
	public EnemyActionLeft(Enemy trackingEnemy) {
		super(0);
		this.trackingEnemy = trackingEnemy;
	}
	@Override
	public void execute(MummyMazeState State) {
		trackingEnemy.MoveLeft(State);
		State.setAction(this);
		
	}

	@Override
	public boolean isValid(MummyMazeState State) {
		return trackingEnemy.canMoveLeft(State.getMatrix());
	}
	
}
