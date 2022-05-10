package mummymaze;

import java.io.File;
import java.io.IOException;

public class MummyMazeAgent extends agent.WhiteMummyAgent<MummyMazeState>
{
    protected MummyMazeState initialEnvironment;
    
    public MummyMazeAgent(MummyMazeState environment)
    {
        super(environment);
        initialEnvironment = environment.clone();
        //heuristics.add(new HeuristicTileDistance());
        //heuristics.add(new HeuristicTilesOutOfPlace());
        //heuristic = heuristics.get(0);
    }
            
    public MummyMazeState resetEnvironment()
    {
        environment = initialEnvironment.clone();
        return environment;
    }
                 
    public MummyMazeState readInitialStateFromFile(File file) throws IOException
    {
        java.util.Scanner scanner = new java.util.Scanner(file);

        //char[][] matrix = new char[13][13];
        
        /*for (int i = 0; i < 3; i++)
        {
            String s = scanner.nextLine();
            matrix[i] = s.toCharArray();
        }*/


		//Tens de lhe passar um estado em string do ficheiro que esta escolhido na combobox(NAO USAR O BOTAO PREVIEW)
        initialEnvironment = new MummyMazeState("");
        resetEnvironment();
        return environment;
    }
}
