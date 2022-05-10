package searchmethods;

import agent.WhiteMummyState;
import utils.NodePriorityQueue;

public class UniformCostSearch extends GraphSearch<NodePriorityQueue>
{
    public UniformCostSearch()
    {
        frontier = new NodePriorityQueue();
    }    
    
    // f = g
    @Override
    public void addSuccessorToFrontier(WhiteMummyState successor, Node parent)
    {
        //Parent cost + cost of the successor
        double g = parent.getG() + successor.getAction().getCost();

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
