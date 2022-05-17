package mummymaze;

import agent.Heuristic;

public class HeuristicTilesOutOfPlace extends Heuristic<MummyMazeProblem, MummyMazeState>
{
    @Override
    public double compute(MummyMazeState state) {
        return 0;
    }
    
    @Override
    public String toString(){
        return "Tiles out of place";
    }    
}