package mummymaze;

import agent.Agent;
import gui.GameArea;

import java.io.File;
import java.io.IOException;

public class MummyMazeAgent extends Agent<MummyMazeState>
{
    protected MummyMazeState initialEnvironment;
    
    public MummyMazeAgent(MummyMazeState environment)
    {
        super(environment);
        initialEnvironment = environment.clone();
        heuristics.add(new HeuristicTileDistance());
        heuristics.add(new HeuristicTilesOutOfPlace());
        heuristic = heuristics.get(0);
    }
            
    public MummyMazeState resetEnvironment()
    {
        environment = initialEnvironment.clone();
        return environment;
    }
                 
    public MummyMazeState readInitialStateFromFile(File file) throws IOException{
        java.util.Scanner scanner = new java.util.Scanner(file);

        char[][] matrix = new char[13][13];
    
        for (int i = 0; i < 13; i++)
        {
            String s = scanner.nextLine();
            matrix[i] = s.toCharArray();
        }

        initialEnvironment = new MummyMazeState(matrix);
        resetEnvironment();
        return environment;
    }
}