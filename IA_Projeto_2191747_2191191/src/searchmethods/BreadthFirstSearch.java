package searchmethods;

import agent.WhiteMummyState;
import utils.NodeLinkedList;

public class BreadthFirstSearch extends GraphSearch<NodeLinkedList>
{
    public BreadthFirstSearch() {
        frontier = new NodeLinkedList();
    }

    @Override
    public void addSuccessorToFrontier(WhiteMummyState successor, Node parent)
    {
        if(!explored.contains(successor) && !frontier.containsState(successor))
        {
            frontier.addLast(new Node(successor, parent));
        }

    }

    @Override
    public String toString() {
        return "Breadth first search";
    }
}