package mummymaze;
import agent.Heuristic;
import enemies.Enemy;

public class HeuristicNumberEnemies extends Heuristic<MummyMazeProblem, MummyMazeState>{

    @Override
    public double compute(MummyMazeState state){
		return state.enemies.size();	
	}
    
    @Override
    public String toString(){
        return "The number of enemies remaining";
    }
}