package enemies;

import gui.GameArea;
import gui.PointDimension;
import gui.GameArea.state_abst;
import mummymaze.MummyMazeState;

import java.util.LinkedList;

public abstract class Enemy{

	public PointDimension<Integer> enemy_position;
	public PointDimension<Integer> origin;
	public boolean isValidPosition(int line, int col, state_abst[][] matriz){
		return line >= 0 && line < matriz.length && col >= 0 && col < matriz[0].length;
	}

	public boolean canMoveLeft(state_abst[][] matriz){
		int block_left = enemy_position.col - 2;
		int space_left = enemy_position.col - 1;
		if(isValidPosition(enemy_position.line, block_left, matriz)){   //If the position exists on the matrix
	
			//wall/door
			if(matriz[enemy_position.line][space_left] == GameArea.state_abst.WALL_VERTICAL
			|| matriz[enemy_position.line][space_left] == GameArea.state_abst.DOOR_VERTICAL_CLOSED){
				return false;
			}
		}
		else
		{
			return false;
		}

		return true;
	}
	public boolean canMoveRight(state_abst[][] matriz){
		//If the tile next to hero on the right is a vertical wall (|) or
		// closed door (=) it cannot move
		int block_right = enemy_position.col + 2;
		int space_right = enemy_position.col + 1;
		if(isValidPosition(enemy_position.line, block_right, matriz))   //If the position exists on the matrix
		{
			//wall/door
			if(matriz[enemy_position.line][space_right] == GameArea.state_abst.WALL_VERTICAL
			|| matriz[enemy_position.line][space_right] == GameArea.state_abst.DOOR_VERTICAL_CLOSED){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	public boolean canMoveUp(state_abst[][] matriz){
		//If the tile next to hero on the right is a vertical wall (|) or
		// closed door (=) it cannot move
		int block_above = enemy_position.line - 2;
		int space_above = enemy_position.line - 1;
		if(isValidPosition(block_above, enemy_position.col, matriz)){
			//wall/door
			if(matriz[space_above][enemy_position.col] == GameArea.state_abst.WALL_HORIZONTAL
			|| matriz[space_above][enemy_position.col] == GameArea.state_abst.DOOR_HORIZONTAL_CLOSED){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	public boolean canMoveDown(state_abst[][] matriz)
	{
		//If the tile next to hero on the right is a vertical wall (|) or
		// closed door (=) it cannot move
		int block_down = enemy_position.line + 2;
		int space_down = enemy_position.line + 1;
		//If the position exists on the matrix
		if(isValidPosition(block_down,enemy_position.col, matriz )){
			//wall/door
			if(matriz[space_down][enemy_position.col] == GameArea.state_abst.WALL_HORIZONTAL
			|| matriz[space_down][enemy_position.col] == GameArea.state_abst.DOOR_HORIZONTAL_CLOSED){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	public abstract void MoveDown(MummyMazeState state);
	public abstract void MoveUp(MummyMazeState state);
	public abstract void MoveLeft(MummyMazeState state);
	public abstract void MoveRight(MummyMazeState state);
	public abstract MummyMazeState MovePoll(MummyMazeState state);
	public abstract boolean canKill(MummyMazeState state);

	public Enemy(PointDimension<Integer> enemy_position, String name){
		this.origin= enemy_position;
		this.enemy_position = enemy_position;
		this.name = name;
		
	}

	public PointDimension<Integer> getEnemy_position() {
		return enemy_position.getClone();
		
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public abstract GameArea.state_abst getSymbol();
}
