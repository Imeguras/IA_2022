package searchmethods;

import agent.State;
import utils.NodePriorityQueue;

public class UniformCostSearch extends GraphSearch<NodePriorityQueue>
{
    public UniformCostSearch()
    {
        frontier = new NodePriorityQueue();
    }    
    
    // f = g
    @Override
    public void addSuccessorToFrontier(State successor, Node parent)
    {
        // this shouldnt be done like so... but for this project since other entities dont add up to the cost this is a simple fix
        double g = parent.getG() + successor.getHeroAction().getCost();

        if(!frontier.containsState(successor)) {
            if (!explored.contains(successor)) {
                frontier.add(new Node(successor, parent, g, g));
            }
        }
        else if(g < frontier.getNode(successor).getG())
        {   frontier.removeNode(successor);
            frontier.add(new Node(successor, parent, g, g));
        }
    }

    @Override
    public String toString()
    {
        return "Uniform cost search";
    }
}
