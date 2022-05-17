package searchmethods;

import agent.State;

public class AStarSearch extends InformedSearch {

    //f = g + h
    @Override
    public void addSuccessorToFrontier(State successor, Node parent) {

        //TODO
        double g = parent.getG() + successor.getAction().getCost();

        if(frontier.containsState(successor))
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
