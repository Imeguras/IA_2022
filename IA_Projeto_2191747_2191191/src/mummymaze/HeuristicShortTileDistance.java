package mummymaze;

import agent.Heuristic;
import enemies.Enemy;

public class HeuristicShortTileDistance extends Heuristic<MummyMazeProblem, MummyMazeState>{

    @Override
    public double compute(MummyMazeState state){
		//geometricCompareTo all the enemies and get the shortest position possible
		double min_dist=Double.MAX_VALUE;
		for (Enemy iterable_element : state.enemies) {
			double dist=state.getHero_pos().geometricCompareTo(iterable_element.getEnemy_position());
			if(dist<min_dist){
				min_dist=dist;
			}
		}
		return min_dist;
		
        //return state.getHero_pos().geometricCompareTo(.getExit_pos());
    }
    
    @Override
    public String toString(){
        return "The shortest Hypothenuse to the enemy position";
    }
}