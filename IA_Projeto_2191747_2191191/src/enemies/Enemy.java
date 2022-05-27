package enemies;

import gui.GameArea;
import gui.PointDimension;
import gui.GameArea.state_abst;
import mummymaze.MummyMazeState;

import java.util.LinkedList;

public abstract class Enemy{
	protected PointDimension<Integer> enemy_position;
	protected MummyMazeState boardState;

	public static LinkedList<Enemy> enemies = new LinkedList<>();

	public boolean isValidPosition(int line, int column)
	{
		return line >= 0 && line < boardState.getMatrix().length && column >= 0 && column <
		boardState.getMatrix()[0].length;
	}

	public boolean canMoveLeft(){
		//If the tile next to hero on the right is a vertical wall (|) or
		// closed door (=) it cannot move
		int block_left = enemy_position.col - 2;
		int space_left = enemy_position.col - 1;
		if(isValidPosition(enemy_position.line, block_left)){   //If the position exists on the matrix
	
			//wall/door
			if(boardState.getMatrix()[enemy_position.line][space_left] == GameArea.state_abst.WALL_VERTICAL
			|| boardState.getMatrix()[enemy_position.line][space_left] == GameArea.state_abst.DOOR_VERTICAL_CLOSED){
				return false;
			}
		}
		else
		{
			return false;
		}

		return true;
	}
	//GameArea.state_abst symbol
	public state_abst[][] moveLeft(){
		int block_left = enemy_position.col - 2;
		int space_left = enemy_position.col - 1;
		state_abst[][] matrix= boardState.getMatrix();
		matrix[enemy_position.line][block_left] = getSymbol();
		matrix[enemy_position.line][enemy_position.col] = GameArea.state_abst.WALKABLE;
		
		enemy_position.col = block_left;
		return matrix; 
	}

	public boolean canMoveRight(){
		//If the tile next to hero on the right is a vertical wall (|) or
		// closed door (=) it cannot move
		int block_right = enemy_position.col + 2;
		int space_right = enemy_position.col + 1;
		if(isValidPosition(enemy_position.line, block_right))   //If the position exists on the matrix
		{
			//wall/door
			if(boardState.getMatrix()[enemy_position.line][space_right] == GameArea.state_abst.WALL_VERTICAL
			|| boardState.getMatrix()[enemy_position.line][space_right] == GameArea.state_abst.DOOR_VERTICAL_CLOSED){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	public state_abst[][] moveRight(){
		int block_right = enemy_position.col + 2;
		int space_right = enemy_position.col + 1;

		state_abst[][] matrix= boardState.getMatrix();
		matrix[enemy_position.line][block_right] = getSymbol();
		matrix[enemy_position.line][enemy_position.col] = GameArea.state_abst.WALKABLE;

		enemy_position.col = block_right;
		return matrix; 
	}
	public boolean canMoveUp(){
		//If the tile next to hero on the right is a vertical wall (|) or
		// closed door (=) it cannot move
		int block_above = enemy_position.line - 2;
		int space_above = enemy_position.line - 1;
		if(isValidPosition(block_above, enemy_position.line)){
			//wall/door
			if(boardState.getMatrix()[space_above][enemy_position.col] == GameArea.state_abst.WALL_HORIZONTAL
			|| boardState.getMatrix()[space_above][enemy_position.col] == GameArea.state_abst.DOOR_HORIZONTAL_CLOSED){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	public state_abst[][] moveUp(){
		int block_up = enemy_position.line - 2;
		int space_up = enemy_position.line - 1;
		state_abst[][] matrix= boardState.getMatrix();
		matrix[block_up][enemy_position.col] = getSymbol();
		matrix[enemy_position.line][enemy_position.col] = GameArea.state_abst.WALKABLE;

		enemy_position.line = block_up;
		return matrix; 
	}
	public boolean canMoveDown(){
		//If the tile next to hero on the right is a vertical wall (|) or
		// closed door (=) it cannot move
		int block_down = enemy_position.line + 2;
		int space_down = enemy_position.line + 1;
		//If the position exists on the matrix
		if(isValidPosition(block_down,enemy_position.col )){
			//wall/door
			if(boardState.getMatrix()[space_down][enemy_position.col] == GameArea.state_abst.WALL_HORIZONTAL
			|| boardState.getMatrix()[space_down][enemy_position.col] == GameArea.state_abst.DOOR_HORIZONTAL_CLOSED){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	public state_abst[][] moveDown(){
		int block_down = enemy_position.line + 2;
		int space_down = enemy_position.line + 1;
		state_abst[][] matrix= boardState.getMatrix();
		matrix[block_down][enemy_position.col] = getSymbol();
		matrix[enemy_position.line][enemy_position.col] = GameArea.state_abst.WALKABLE;

		enemy_position.line = block_down;
		return matrix; 
	}
	public boolean canKill(){
		return false; 
	}
	public Enemy(PointDimension<Integer> enemy_position, String name)
	{
		this.enemy_position = enemy_position;
		this.name = name;
	}

	public PointDimension<Integer> getEnemy_position() {
		return enemy_position;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void updateState(MummyMazeState t){
		this.boardState=t; 
	}
	public abstract MummyMazeState move();
	public abstract GameArea.state_abst getSymbol();
}
