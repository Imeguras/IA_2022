package mummymaze;

import agent.Heuristic;

public class HeuristicTileDistance extends Heuristic<MummyMazeProblem, MummyMazeState>{

    @Override
    public double compute(MummyMazeState state){
        return state.getHero_pos().compareTo(state.getExit_pos());
    }
    
    @Override
    public String toString(){
        return "The Hypothenuse to the final position";
    }
}