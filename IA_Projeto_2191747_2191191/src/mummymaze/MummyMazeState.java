package mummymaze;

import agent.Action;
import agent.State;
import enemies.Enemy;
import enemies.WhiteMummy;
import gui.GameArea;
import gui.PointDimension;
import gui.GameArea.state_abst;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

public class MummyMazeState extends State implements Cloneable{
	
    public static final int SIZE = 13;
    public boolean hero_dead = false;

    private GameArea.state_abst[][] matrix;

    private int hero_line;
    private int hero_column;
	
    private int exit_line;
    private int exit_column;
	
	public PointDimension<Integer> getHero_pos(){
		return new PointDimension<>(hero_line, hero_column);
	}
	public PointDimension<Integer> getExit_pos(){
		return new PointDimension<>(exit_line, exit_column);
	}

    public MummyMazeState(char[][] char_matrix){
		Enemy.enemies.clear();
        this.matrix = new state_abst[SIZE][SIZE];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = state_abst.getCorChar(char_matrix[i][j]);
                switch (matrix[i][j]) {
					case HERO:
						hero_line = i;
						hero_column = j;
						break;
					case STAIRS:
						exit_line=Math.max(1, Math.min(11, i));
						exit_column=Math.max(1, Math.min(11, j));
						break;
					case MUMMY_WHITE:
						new WhiteMummy(new PointDimension<Integer>(i, j), "White_Mummy");	
					default:
						break;
				}
            }
        }
    }
	public MummyMazeState(state_abst[][] cloned_matrix){
		Enemy.enemies.clear();
		this.matrix = new state_abst[SIZE][SIZE];
		//this.matrix= cloned_matrix;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				this.matrix[i][j] = cloned_matrix[i][j];
				switch (matrix[i][j]) {
					case HERO:
						hero_line = i;
						hero_column = j;
						break;
					case STAIRS:
						exit_line=Math.max(1, Math.min(11, i));
						exit_column=Math.max(1, Math.min(11, j));
						break;
					case MUMMY_WHITE:
						new WhiteMummy(new PointDimension<Integer>(i, j), "White_Mummy");	
						//System.out.println(Enemy.enemies.getFirst().getEnemy_position().toString());
					default:
						break;
				}
			}
		}

	}	
    @Override
    public void executeAction(Action action){
        
		action.execute(this);
		for (Enemy enemySubTurn : Enemy.enemies) {
			enemySubTurn.updateState(this);
			matrix=enemySubTurn.move().matrix;
		}
        firePuzzleChanged(null);
    }

    public boolean canMoveUp(){
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_above = hero_line - 2;
        int space_above = hero_line - 1;
        if(isValidPosition(block_above, hero_column)){   //If the position exists on the matrix
            switch (matrix[space_above][hero_column]){
				case WALL_HORIZONTAL: 
				case MUMMY_WHITE:
				case TRAP:
				case MUMMY_RED:
				case DOOR_HORIZONTAL_CLOSED:
				case SCORPION: 
					return false;
			
				default:
					return true;
					
			}
			
        }
        else
        {
            return false;
        }


    }

    public boolean canMoveDown()
    {
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_below = hero_line + 2;
        int space_below = hero_line + 1;
        if(isValidPosition(block_below, hero_column)){   //If the position exists on the matrix
            //wall/door
            switch(matrix[space_below][hero_column]){
            
				case WALL_HORIZONTAL: 
				case MUMMY_WHITE:
				case TRAP:
				case MUMMY_RED:
				case DOOR_HORIZONTAL_CLOSED:
				case SCORPION: 
					return false;
			
				default:
					return true;
					
			}

        }else{
            return false;
        }
        
    }

    public boolean canMoveLeft()
    {
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_left = hero_column - 2;
        int space_left = hero_column - 1;
        if(isValidPosition(hero_line, block_left))   //If the position exists on the matrix
        {
            //wall/door

			switch(matrix[hero_line][space_left]){
				case WALL_HORIZONTAL: 
				case MUMMY_WHITE:
				case TRAP:
				case MUMMY_RED:
				case DOOR_HORIZONTAL_CLOSED:
				case SCORPION: 
					return false;
		
				default:
					return true;
				
			}
        }
        else
        {
            return false;
        }

  
    }

    public boolean canMoveRight()
    {
        //If the tile next to hero on the right is a vertical wall (|) or
        // closed door (=) it cannot move
        int block_right = hero_column + 2;
        int space_right = hero_column + 1;
        if(isValidPosition(hero_line, block_right))   //If the position exists on the matrix
        {
        
            switch(matrix[hero_line][space_right]){	
				case WALL_HORIZONTAL: 
				case MUMMY_WHITE:
				case TRAP:
				case MUMMY_RED:
				case DOOR_HORIZONTAL_CLOSED:
				case SCORPION: 
				return false;		
				default:
					return true;
			
			}
        }
        else{
            return false;
        }
    }

    public void moveUp()
    {
        int block_above = hero_line - 2;
        int space_above = hero_line - 1;

        matrix[block_above][hero_column] = state_abst.HERO;
        matrix[hero_line][hero_column] =state_abst.WALKABLE;
		
        hero_line = block_above;
		
    }
	
    public void moveDown()
    {
        int block_below = hero_line + 2;
        int space_below = hero_line + 1;

        matrix[block_below][hero_column] = state_abst.HERO;
        matrix[hero_line][hero_column] = state_abst.WALKABLE;

        hero_line = block_below;
		
    }

    public void moveLeft()
    {
        int block_left = hero_column - 2;
        int space_left = hero_column - 1;

        matrix[hero_line][block_left] = state_abst.HERO;
        matrix[hero_line][hero_column] =state_abst.WALKABLE;

        hero_column = block_left;
		
    }

    public void moveRight()
    {
        int block_right = hero_column + 2;
        int space_right = hero_column + 1;

        matrix[hero_line][block_right] = state_abst.HERO;
        matrix[hero_line][hero_column] = state_abst.WALKABLE;

        hero_column = block_right;
		
    }
	

    public state_abst[][] getMatrix()
    {
        return this.matrix.clone();
    }

    public int getNumLines() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public int getTileValue(int line, int column) {
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return 0;//state_matrix[line][column];
    }

    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MummyMazeState)) {
            return false;
        }

        MummyMazeState o = (MummyMazeState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public MummyMazeState clone() {
        return new MummyMazeState(this.matrix);
    } 

    //Listeners
    private transient ArrayList<MummyMazeListener> listeners = new ArrayList<MummyMazeListener>(3);

    public synchronized void removeListener(MummyMazeListener l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(MummyMazeListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(MummyMazeEvent pe) {
        for (MummyMazeListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }
}
