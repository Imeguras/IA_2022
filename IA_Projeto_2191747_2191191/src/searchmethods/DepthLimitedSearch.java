package searchmethods;

import agent.State;

public class DepthLimitedSearch extends DepthFirstSearch {

    private int limit;

    public DepthLimitedSearch() {
        this(28);
    }

    public DepthLimitedSearch(int limit) {
        this.limit = limit;
    }

    @Override
    public void addSuccessorToFrontier(State successor, Node parent) {

        //TODO

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
