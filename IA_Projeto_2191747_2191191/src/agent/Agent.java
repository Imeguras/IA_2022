package agent;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;

import enemies.Enemy;
import enemies.EnemyAction;
import gui.PointDimension;
import mummymaze.ActionDown;
import mummymaze.HeuristicTileDistance;
import searchmethods.*;

public class Agent<E extends State>
{

    protected E environment;
	public static SearchMethod[] searchMethods_st={
			new BreadthFirstSearch(),
			new UniformCostSearch(),
        	new DepthFirstSearch(),
        	new DepthLimitedSearch(),
        	new IterativeDeepeningSearch(),
        	new GreedyBestFirstSearch(),
        	new AStarSearch(),
        	new BeamSearch(),
        	new IDAStarSearch()
	};
	public static Heuristic[] heuristics_st={
		new HeuristicTileDistance()
	};
    //protected ArrayList<SearchMethod> searchMethods;
    protected SearchMethod searchMethod;
    //protected ArrayList<Heuristic> heuristics;
    protected Heuristic heuristic;
    protected Solution solution;

    public Agent(E environment)
    {
		this();
		this.environment = environment;
        
    }
	public Agent(){
		searchMethod = searchMethods_st[0];
	}

    public Solution solveProblem(Problem problem)
    {
		
        if (heuristic != null)
        {
            problem.setHeuristic(heuristic);
            heuristic.setProblem(problem);
			
        }
        solution = searchMethod.search(problem);
		//for each solution.getActions print action.toString()
		for (Action k: solution.getActions()) {
			System.out.println(k.toString());
		}
        return solution;	
    }

    public void executeSolution(){
		for(Action action : solution.getActions()){
			//PointDimension<Integer> normalize=new PointDimension<Integer>(0, 0);
			if(action instanceof EnemyAction){
				((EnemyAction)action).trackingEnemy.enemy_position=((EnemyAction)action).trackingEnemy.origin;
				break;
			}
			
		}
		//System.out.println();
        for(Action action : solution.getActions()){
			//if action is of EnemyAction type
			
				
            environment.executeAction(action);
			

        }
		
    }

    public boolean hasSolution()
    {
        return solution != null;
    }

    public void stop() {
        getSearchMethod().stop();
    }

    public boolean hasBeenStopped() {
        return getSearchMethod().hasBeenStopped();
    }

    public E getEnvironment() {
        return environment;
    }

    public void setEnvironment(E environment) {
        this.environment = environment;
    }

    public SearchMethod getSearchMethod() {
        return searchMethod;
    }

    public void setSearchMethod(SearchMethod searchMethod){
        this.searchMethod = searchMethod;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public String getSearchReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(searchMethod + "\n");
        if (solution == null) {
            sb.append("No solution found\n");
        } else {
            sb.append("Solution cost: " + Double.toString(solution.getCost()) + "\n");
        }
        sb.append("Num of expanded nodes: " + searchMethod.getStatistics().numExpandedNodes + "\n");
        sb.append("Max frontier size: " + searchMethod.getStatistics().maxFrontierSize + "\n");
        sb.append("Num of generated nodes: " + searchMethod.getStatistics().numGeneratedNodes+ "\n");

        return sb.toString();
    }
}
