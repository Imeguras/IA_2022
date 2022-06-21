package searchmethods;

import agent.State;

public class DepthLimitedSearch extends DepthFirstSearch {

    public int limit;

    public DepthLimitedSearch() {
        this(28);
    }

    public DepthLimitedSearch(int limit) {
        this.limit = limit;
    }

    @Override
    public void addSuccessorToFrontier(State successor, Node parent) {
		if(parent.getDepth()>=limit){
			return;
		}
		if(!frontier.containsState(successor))
        {
            if(!parent.isCycle(successor))
            {
                frontier.addFirst(new Node(successor, parent));
            }
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Limited depth first search";
    }
}
