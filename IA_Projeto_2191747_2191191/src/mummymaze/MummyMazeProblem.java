package mummymaze;

import agent.Action;
import agent.Problem;

import java.util.LinkedList;
import java.util.List;

public class MummyMazeProblem extends Problem<MummyMazeState>
{
    private List<Action> actions;
    private MummyMazeState goalState;

    public MummyMazeProblem(MummyMazeState initialState)
    {
        super(initialState);
        this.actions = new LinkedList<>();
        this.actions.add(new ActionDown());
        this.actions.add(new ActionUp());
        this.actions.add(new ActionLeft());
        this.actions.add(new ActionRight());
    }

    @Override
    public List<Action<MummyMazeState>> getActions(MummyMazeState state)
    {
        List<Action<MummyMazeState>> possibleActions = new LinkedList<>();
        for(Action action : actions)
        {
            if(action.isValid(state))
            {
                possibleActions.add(action);
            }
        }
        return possibleActions;
    }

    @Override
    public List<MummyMazeState> executeActions(MummyMazeState state) {
        LinkedList<MummyMazeState> successors= new LinkedList<>();
        for(Action a: actions){
            if(a.isValid(state)){
                MummyMazeState successor = (MummyMazeState) state.clone();
                a.execute(successor);
                successors.add(successor);
            }
        }

        return successors;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public MummyMazeState getGoalState()
    {
        return goalState;
    }

    public void setGoalState(MummyMazeState goalState) {
        this.goalState = goalState;
    }

    @Override
    public MummyMazeState getSuccessor(MummyMazeState state, Action action)
    {
        MummyMazeState successor = state.clone();
        action.execute(successor);
        return successor;
    }

    public boolean isGoal(MummyMazeState state)
    {
        return false;
    }

    public double computePathCost(List<Action> path)
    {
        return path.size();
    }
}
