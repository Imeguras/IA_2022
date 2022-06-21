package enemies;

import java.util.Iterator;
import java.util.LinkedList;

import agent.Action;
import gui.GameArea;
import gui.GameArea.state_abst;
import mummymaze.MummyMazeState;
import gui.PointDimension;


public class RedMummy extends Enemy{
	public LinkedList<EnemyAction> actions;

	public RedMummy(PointDimension<Integer> enemy_position, String name){
        super(enemy_position, "Red Mummy");
		actions = new LinkedList<EnemyAction>();
		this.actions.add(new EnemyActionLeft(this));
        this.actions.add(new EnemyActionRight(this));
		this.actions.add(new EnemyActionDown(this));
        this.actions.add(new EnemyActionUp(this));
		this.actions.add(new EnemyActionQuiet(this));
    }

	@Override
	public void MoveDown(MummyMazeState state) {
		trailBehind(state);
		int block_down = enemy_position.line + 2;
		int space_down = enemy_position.line + 1;
		futureStompVertical(state, block_down);
		
		
		enemy_position.line=block_down;
	}

	@Override
	public void MoveUp(MummyMazeState state) {
		trailBehind(state);
		int block_up = enemy_position.line - 2;
		int space_up = enemy_position.line - 1;
		
		futureStompVertical(state, block_up);
		enemy_position.line=block_up;
		
	}
	@Override
	public void MoveRight(MummyMazeState state){
		trailBehind(state);
		state.matrix[enemy_position.line][enemy_position.col] = state_abst.WALKABLE;
		int block_right = enemy_position.col + 2;
		int space_right = enemy_position.col + 1;
		
		futureStompHorizontal(state, block_right);
		enemy_position.col=block_right;
	}
	 

	@Override
	public void MoveLeft(MummyMazeState state) {
		trailBehind(state);
		int block_left = enemy_position.col - 2;
		int space_left = enemy_position.col - 1;
		futureStompHorizontal(state, block_left);

	
		enemy_position.col=block_left;
	}

	@Override 
	public MummyMazeState MovePoll(MummyMazeState state) {
		PointDimension<Integer> oldPos =getEnemy_position();
		PointDimension<Integer> secondPos;
		MummyMazeState oldState = state.clone();
		MummyMazeState secondState;
		LinkedList<EnemyAction> firstMove=(LinkedList<EnemyAction>)actions.clone();
		LinkedList<EnemyAction> secondMove;
		for (EnemyAction action : firstMove) {
			
			enemy_position=oldPos.getClone();
			state=oldState.clone();
			if(action.isValid(state)){
				action.execute(state);
				secondMove=(LinkedList<EnemyAction>)firstMove.clone();

				action.removeRedundantMoves(secondMove.listIterator());
				secondState=state.clone(); 
				secondPos=getEnemy_position();
				for (EnemyAction action2 : secondMove) {
					
					state=secondState.clone();
					enemy_position=secondPos.getClone();
					if(action2.isValid(state)){
						action2.execute(state);
						if (state.hero_dead) {
							return state.clone();
						//check if it made any progress in being in the same column as hero
						}else if(Math.abs(state.getHero_pos().line-oldPos.line)>Math.abs(state.getHero_pos().line-getEnemy_position().line) || Math.abs(state.getHero_pos().line-getEnemy_position().line)==0){
							return state.clone();
						//check if after being in the same column as hero, it made any progress in being in the same line as hero
						}else if(getEnemy_position().line==state.getHero_pos().line && Math.abs(state.getHero_pos().col-oldPos.col)<=Math.abs(state.getHero_pos().col-getEnemy_position().col)){
							return state.clone();
					
						}
					}//else continues by default
				}
			}//else continues by default
		}
		//restores the old postion or remain stuck in other words, because no progress was made
		this.enemy_position= oldPos; 
		return oldState.clone();
	}
	@Override
	public state_abst getSymbol(){
		return state_abst.MUMMY_RED;
	}
	@Override
	public state_abst getSymbolKey(){
		return state_abst.REDKEY;
	}
	@Override
	public boolean canKill(MummyMazeState state) {
		PointDimension<Integer> oldPos=this.enemy_position;
		MummyMazeState simulateState=state.clone();
		
		simulateState=this.MovePoll(simulateState);
		this.enemy_position=oldPos;
		if(simulateState.hero_dead){
			return true;
		}
		return false;
	}

	
	
}
