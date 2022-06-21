package mummymaze;

import agent.Heuristic;

public class HeuristicGeometricTileDistance extends Heuristic<MummyMazeProblem, MummyMazeState>{

    @Override
    public double compute(MummyMazeState state){
        return state.getHero_pos().geometricCompareTo(state.getExit_pos());
    }
    
    @Override
    public String toString(){
        return "The Hypothenuse to the final position";
    }
}