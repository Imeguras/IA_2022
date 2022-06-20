package enemies;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import agent.Action;
import mummymaze.MummyMazeState;

public abstract class EnemyAction extends Action<MummyMazeState>{
	public Enemy trackingEnemy;
	public EnemyAction(Enemy trackingEnemy ) {
		super(0);
		this.trackingEnemy = trackingEnemy;
	}
	public abstract void removeRedundantMoves(ListIterator<EnemyAction> actions); 
	public void rewindMoves(ListIterator<EnemyAction> actions){
		while (actions.hasPrevious()) {
			//Rewind the iterator to the first element
			actions.previous();


		}
	}
}
