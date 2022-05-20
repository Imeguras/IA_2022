package mummymaze;

import agent.Agent;
import gui.GameArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MummyMazeAgent extends Agent<MummyMazeState>{
    protected MummyMazeState initialEnvironment;
    
	public MummyMazeAgent(){
		super();
		heuristic = heuristics_st[0];
	}
    public MummyMazeAgent(MummyMazeState environment){
        super(environment);
        initialEnvironment = environment.clone();
        heuristic = heuristics_st[0];
    }
            
    public MummyMazeState resetEnvironment()
    {
        environment = initialEnvironment.clone();
        return environment;
    }
                 
	/*
	* @Summary Reads a file of any kind and return a MummyMazeState with the appropriate matrix in it, unlike readStateFromFile, this function will also overwrite the initialEnvironment and overwrites the environment to be a copy of initialEnvironment
	* @Returns the attribute initialEnvironment
	*/
    public MummyMazeState readInitialStateFromFile(String file) throws IOException{
		System.out.println(file);
        initialEnvironment = readStateFromFile(file);
        resetEnvironment();
        return environment;
    }
	/*
	* @Summary Reads a file of any kind and return a MummyMazeState with the appropriate matrix in it
	* @Returns MummyMazeState with a representation of the board game inside
	*/
	public static MummyMazeState readStateFromFile(String file) throws IOException{
		File vir_file = new File(file);
		BufferedReader fl_r= new BufferedReader(new FileReader(vir_file));

		System.out.println("Detected file size: "+vir_file.length());
		
		char[][] matrix = new char[MummyMazeState.SIZE][MummyMazeState.SIZE];
		for (int i = 0; i < MummyMazeState.SIZE; i++) {
			matrix[i]=fl_r.readLine().toCharArray();
		}
		fl_r.close();
		return new MummyMazeState(matrix);
	}
}
