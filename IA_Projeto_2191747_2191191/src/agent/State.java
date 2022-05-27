package agent;

import enemies.Enemy;

public abstract class State
{
    protected Action action;

    public State(){}

    public abstract void executeAction(Action action);
    
    public Action getAction(){
        return action;
    }

    public void setAction(Action action){
        this.action = action;
		//devido ao boogaloo que isto é tem de ser aqui
		
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}