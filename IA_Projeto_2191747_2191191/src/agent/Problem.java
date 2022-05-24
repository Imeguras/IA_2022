package agent;

import mummymaze.MummyMazeState;

import java.util.List;

public abstract class Problem <S extends State>{

    public S InitialState;
    protected Heuristic heuristic;

    public S getInitialState() {
        return InitialState;
    }

    public Problem(S InitialState)
    {
        this.InitialState = InitialState;
    }

    public abstract List<Action<S>> getActions(S state);

    public abstract List<S> executeActions(S state);

    public abstract boolean isGoal(S state);

    public abstract S getSuccessor(S state, Action a);

    public double computePathCost(List<Action> path)
    {
        double pathCost = 0;

        for(Action action : path)
        {
            pathCost += action.getCost();
        }

        return pathCost;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public abstract MummyMazeState getGoalState();
}
