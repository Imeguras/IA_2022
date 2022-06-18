package searchmethods;

import java.util.List;

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

        while(!frontier.isEmpty() && !stopped){
            Node n = frontier.poll();
            State state = n.getState();
			
			if(problem.isGoal(state)){
				System.out.print("Solution Frame:\n"+state.toString());
                return new Solution(problem, n);
            }
			List<State> successors = problem.executeActions(n.getState());
			addSuccessorsToFrontier(successors, n);
			//It only is the goalState if the enemies can't kill the hero when he is on the goal state
			
            //frontier.add(suc)
			//addSuccessorsToFrontier(successors, frontierNode);

            //computeStatistics(successors.size());

        }
		
        return null;
    }

    @Override
    public void addSuccessorToFrontier(State successor, Node parent){
        //TODO
        if(!frontier.containsState(successor))
        {
            if(!parent.isCycle(successor))
            {
                frontier.addFirst(new Node(successor, parent));
            }
        }
    }
	public void addSuccessorsToFrontier(List<State>  successors, Node parent){
		for (State state : successors) {
			addSuccessorToFrontier(state, parent);
		}
	}

    @Override
    public String toString() {
        return "Depth first search";
    }
}
