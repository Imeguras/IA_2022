package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;

public class IDAStarSearch extends InformedSearch {
    /*
     * Note that, on each iteration, the search is done in a depth first search way.    
     */
    
    private double limit;
    private double newLimit;    

    @Override
    public Solution search(Problem problem) {
        statistics.reset();        
        stopped = false;
        return null;
    }

    @Override
    protected Solution graphSearch(Problem problem) {
        return null;
    }

    @Override
    public void addSuccessorToFrontier(State successor, Node parent)
    {
			// this shouldnt be done like so... but for this project since other entities dont add up to the cost this is a simple fix
            double g = parent.getG() + successor.getHeroAction().getCost();
            if (!frontier.containsState(successor)) {
                double f = g + heuristic.compute(successor);
                if (f <= limit) {
                    if (!parent.isCycle(successor)) {
                        frontier.add(new Node(successor, parent, g, f));
                    }
                } else {
                    newLimit = Math.min(newLimit, f);
                }
            } else if (frontier.getNode(successor).getG() > g) {
                frontier.removeNode(successor);
                frontier.add(new Node(successor, parent, g, g + heuristic.compute(successor)));
            }
    }

    @Override
    public String toString() {
        return "IDA* search";
    }
}
