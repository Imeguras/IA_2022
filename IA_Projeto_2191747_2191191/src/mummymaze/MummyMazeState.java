package mummymaze;

import agent.Action;
import agent.State;
import enemies.Enemy;
import enemies.EnemyOrderComparator;
import enemies.RedMummy;
import enemies.WhiteMummy;
import gui.GameArea;
import gui.PointDimension;
import gui.GameArea.state_abst;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MummyMazeState extends State implements Cloneable{
	private LinkedList<PointDimension<Integer>> state_doors;
    public static final int SIZE = 13;
    public boolean hero_dead = true;

    public GameArea.state_abst[][] matrix;

    private int hero_line;
    private int hero_column;
	
    private int exit_line;
    private int exit_column;
	public LinkedList<Enemy> enemies = new LinkedList<>();
	
	public PointDimension<Integer> getHero_pos(){
		return new PointDimension<>(hero_line, hero_column);
	}
	public PointDimension<Integer> getExit_pos(){
		return new PointDimension<>(exit_line, exit_column);
	}

    public MummyMazeState(char[][] char_matrix){
		super();
		state_doors= new LinkedList<PointDimension<Integer>>();
        this.matrix = new state_abst[SIZE][SIZE];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = state_abst.getCorChar(char_matrix[i][j]);
                switch (matrix[i][j]) {
					case HEROKEY:
					case HERO:
						hero_line = i;
						hero_column = j;
						hero_dead=false;
						break;
					case STAIRS:
						exit_line=Math.max(1, Math.min(11, i));
						exit_column=Math.max(1, Math.min(11, j));
						break;
					case MUMMY_WHITE:
						EnemyOrderComparator cmp= new EnemyOrderComparator();
						enemies.add(new WhiteMummy(new PointDimension<Integer>(i, j), "White_Mummy"));
						enemies.sort(cmp);	
						break; 
					case MUMMY_RED:
						EnemyOrderComparator cmp2= new EnemyOrderComparator();
						enemies.add(new RedMummy(new PointDimension<Integer>(i, j), "Red_Mummy"));
						enemies.sort(cmp2);
						break; 	
					case DOOR_HORIZONTAL_CLOSED:
					case DOOR_HORIZONTAL_OPENED:
					case DOOR_VERTICAL_CLOSED:
					case DOOR_VERTICAL_OPENED:
						state_doors.add(new PointDimension<Integer>(i, j));
						break; 
					default:
						break;
				}
            }
        }
		
    }
	public MummyMazeState(state_abst[][] cloned_matrix){
		this(cloned_matrix, new LinkedList<Action>());

	}	
    public MummyMazeState(state_abst[][] cloned_matrix, LinkedList<Action> subTurnsPersisted){
		super(subTurnsPersisted);
		state_doors= new LinkedList<PointDimension<Integer>>();
		this.matrix = new state_abst[SIZE][SIZE];
		//this.matrix= cloned_matrix;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				this.matrix[i][j] = cloned_matrix[i][j];
				switch (matrix[i][j]) {
					case HEROKEY:
					case HERO:
						hero_line = i;
						hero_column = j;
						this.hero_dead=false;
						break;
					case STAIRS:
						exit_line=Math.max(1, Math.min(11, i));
						exit_column=Math.max(1, Math.min(11, j));
						break;
					case MUMMY_WHITE:
						EnemyOrderComparator cmp= new EnemyOrderComparator();
						enemies.add(new WhiteMummy(new PointDimension<Integer>(i, j), "White_Mummy"));
						enemies.sort(cmp);	
						break;
					case MUMMY_RED:
						EnemyOrderComparator cmp2= new EnemyOrderComparator();
						enemies.add(new RedMummy(new PointDimension<Integer>(i, j), "Red_Mummy"));
						enemies.sort(cmp2);
						break; 
					case DOOR_HORIZONTAL_CLOSED:
					case DOOR_HORIZONTAL_OPENED:
					case DOOR_VERTICAL_CLOSED:
					case DOOR_VERTICAL_OPENED:
						state_doors.add(new PointDimension<Integer>(i, j));
					default:
						break;
				}
			}
		}
	}

	@Override
    public void executeAction(Action action){
        
		action.execute(this);
		firePuzzleChanged(null);
    }
	/*
	 * @Summary: Executes all subturns(actions) inside actions which represents a full turn
	 */
	@Override
	public void executeTurn() {
		for (Action<MummyMazeState> action : actions) {
			action.execute(this);
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
				case DOOR_HORIZONTAL_CLOSED:
					return false;
			}
			switch(matrix[block_above][hero_column]){
				//TODO MUMMYS COM CHAVES
				case MUMMY_WHITE:
				case TRAP:
				case MUMMY_RED:
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
            switch (matrix[space_below][hero_column]){
                case WALL_HORIZONTAL:
                case DOOR_HORIZONTAL_CLOSED:
                    return false;
            }

            switch(matrix[block_below][hero_column]){
            
				case MUMMY_WHITE:
				case TRAP:
				case MUMMY_RED:
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
            switch (matrix[hero_line][space_left]){
                case WALL_VERTICAL:
                case DOOR_VERTICAL_CLOSED:
                    return false;
            }
			switch(matrix[hero_line][block_left]){
				case MUMMY_WHITE:
				case TRAP:
				case MUMMY_RED:
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
            switch (matrix[hero_line][space_right]){
                case WALL_VERTICAL:
                case DOOR_VERTICAL_CLOSED:
                    return false;
            }
            switch(matrix[hero_line][block_right]){
				case MUMMY_WHITE:
				case TRAP:
				case MUMMY_RED:
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
	
		
		if(matrix[block_above][hero_column]==state_abst.KEY){
			matrix[block_above][hero_column] = state_abst.HEROKEY;
			open_close_door();
		}else{
			matrix[block_above][hero_column] = state_abst.HERO;
		}
		if(matrix[hero_line][hero_column]!=state_abst.HEROKEY){
			matrix[hero_line][hero_column] =state_abst.WALKABLE;
		}else{
			matrix[hero_line][hero_column] = state_abst.KEY;
		}
       
		
        hero_line = block_above;
		
    }
	
    public void moveDown()
    {
        int block_below = hero_line + 2;
        int space_below = hero_line + 1;

		if(matrix[block_below][hero_column]==state_abst.KEY){
			matrix[block_below][hero_column] = state_abst.HEROKEY;
			open_close_door();
		}else{
			matrix[block_below][hero_column] = state_abst.HERO;
		}
		if(matrix[hero_line][hero_column]!=state_abst.HEROKEY){
			matrix[hero_line][hero_column] =state_abst.WALKABLE;
		}else{
			matrix[hero_line][hero_column] = state_abst.KEY;
		}

        hero_line = block_below;
		
    }

    public void moveLeft()
    {
        int block_left = hero_column - 2;
        int space_left = hero_column - 1;
		if(matrix[hero_line][block_left]==state_abst.KEY){
			matrix[hero_line][block_left] = state_abst.HEROKEY;
			open_close_door();
		}else{
			matrix[hero_line][block_left] = state_abst.HERO;
		}
		if(matrix[hero_line][hero_column]!=state_abst.HEROKEY){
			matrix[hero_line][hero_column] =state_abst.WALKABLE;
		}else{
			matrix[hero_line][hero_column] = state_abst.KEY;
		}

        hero_column = block_left;
		
    }

    public void moveRight()
    {
        int block_right = hero_column + 2;
        int space_right = hero_column + 1;
		if(matrix[hero_line][block_right]==state_abst.KEY){
			matrix[hero_line][block_right] = state_abst.HEROKEY;
			open_close_door();
		}else{
			matrix[hero_line][block_right] = state_abst.HERO;
		}
		if(matrix[hero_line][hero_column]!=state_abst.HEROKEY){
			matrix[hero_line][hero_column] =state_abst.WALKABLE;
		}else{
			matrix[hero_line][hero_column] = state_abst.KEY;
		}

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
        return new MummyMazeState(this.matrix, this.actions);
    } 

	public MummyMazeState clone(boolean cloneActions) {
		if(cloneActions){
			return new MummyMazeState(this.matrix, this.actions);
		}
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

	public void open_close_door(){
		PointDimension<Integer> t;
		for(PointDimension<Integer> door : state_doors) {
			
		
			int line=door.line;
			int coluna=door.col;
			switch (matrix[line][coluna]) {
				case DOOR_HORIZONTAL_OPENED:
					matrix[line][coluna]=state_abst.DOOR_HORIZONTAL_CLOSED;
					break;
				case DOOR_HORIZONTAL_CLOSED:
					matrix[line][coluna]=state_abst.DOOR_HORIZONTAL_OPENED;
					break;
				case DOOR_VERTICAL_CLOSED:
					matrix[line][coluna]=state_abst.DOOR_VERTICAL_OPENED;
					break;
				case DOOR_VERTICAL_OPENED:
					matrix[line][coluna]=state_abst.DOOR_VERTICAL_CLOSED;
					break; 
				default:
					break; 
			}

        }   
           

        
    }
}
