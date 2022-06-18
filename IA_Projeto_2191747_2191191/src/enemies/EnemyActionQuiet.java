package enemies;

import agent.Action;
import mummymaze.MummyMazeState;

public class EnemyActionQuiet extends Action<MummyMazeState> {
	public Enemy trackingEnemy;
	public EnemyActionQuiet(Enemy trackingEnemy) {
		super(0);
		this.trackingEnemy = trackingEnemy;
	}
	@Override
	public void execute(MummyMazeState state) {
		state.setAction(this);
		
	}
	@Override
	public boolean isValid(MummyMazeState state) {
		return true;
	}
	
}
