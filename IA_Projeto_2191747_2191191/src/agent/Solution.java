package agent;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import searchmethods.Node;

public class Solution {
    private final Problem problem;
    private final LinkedList<Action> actions;

    public Solution(Problem problem, Node goalNode){
        this.problem = problem;
        Node node = goalNode;
        actions = new LinkedList<>();
        while(node.getParent() != null){
			/*
			 * add every action in node.getState.getActions to the actions list
			 */
			ListIterator<Action> t=node.getState().getActions().listIterator();
			
			for (Action k; t.hasNext();) {
				k=t.next();
				actions.addFirst(k);
			}
			node = node.getParent();
        }        
    }

    public double getCost(){
        return problem.computePathCost(actions);
    }

    public List<Action> getActions(){
        return actions;
    }
	
}