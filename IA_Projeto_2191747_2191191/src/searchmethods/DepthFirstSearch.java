package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.WhiteMummyState;
import utils.NodeLinkedList;

public class DepthFirstSearch extends GraphSearch<NodeLinkedList> {

    public DepthFirstSearch() {
        frontier = new NodeLinkedList();
    }

    //Graph Search without explored list
    @Override
    protected Solution graphSearch(Problem problem)
    {
        //TODO
        frontier.clear();
        frontier.add(new Node(problem.getInitialState()));

        while(!frontier.isEmpty() && !stopped)
        {
            Node n = frontier.poll();
            WhiteMummyState whiteMummyState = n.getState();

            if(problem.isGoal(whiteMummyState))
            {
                return new Solution(problem, n);
            }
        }

        return null;
    }

    @Override
    public void addSuccessorToFrontier(WhiteMummyState successor, Node parent)
    {
        //TODO
        if(!frontier.containsState(successor))
        {
            if(!parent.isCycle(successor))
            {
                frontier.addFirst(new Node(successor, parent));
            }
        }
    }

    @Override
    public String toString() {
        return "Depth first search";
    }
}
