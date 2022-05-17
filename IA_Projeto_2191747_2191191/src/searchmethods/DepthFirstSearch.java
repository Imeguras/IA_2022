package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
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
            State state = n.getState();

            if(problem.isGoal(state))
            {
                return new Solution(problem, n);
            }
        }

        return null;
    }

    @Override
    public void addSuccessorToFrontier(State successor, Node parent)
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
