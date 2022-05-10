package agent;

public abstract class WhiteMummyState
{
    protected Action action;

    public WhiteMummyState(){}

    public abstract void executeAction(Action action);
    
    public Action getAction(){
        return action;
    }

    public void setAction(Action action){
        this.action = action;
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}