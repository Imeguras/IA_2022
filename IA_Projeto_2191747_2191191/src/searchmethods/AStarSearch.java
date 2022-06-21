package searchmethods;

import agent.State;

public class AStarSearch extends InformedSearch {

    //f = g + h
    @Override
    public void addSuccessorToFrontier(State successor, Node parent) {
		// this shouldnt be done like so... but for this project since other entities dont add up to the cost this is a simple fix
        double g = parent.getG() + successor.getActions().getFirst().getCost();

        if(!frontier.containsState(successor))
        {
            if(!explored.contains(successor))
            {
                frontier.add(new Node(successor, parent, g, g + heuristic.compute(successor)));
            }
        }
        else if(g < frontier.getNode(successor).getG())
        {
            frontier.removeNode(successor);
            frontier.add(new Node(successor, parent, g, g + heuristic.compute(successor)));
        }
    }

    @Override
    public String toString() {
        return "A* search";
    }
}
