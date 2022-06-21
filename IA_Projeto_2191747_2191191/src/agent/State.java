package agent;

import java.util.*;


public abstract class State
{
	protected LinkedList<Action> actions;
    
    public State(){
		actions = new LinkedList<Action>();
	}

    public abstract void executeAction(Action action);
	/*
	 * @Summary: Executes every action in the states Actions list
	 */
    public abstract void executeTurn(); 
    public Action getLastAction(){
		//get the last action in actions
		if(actions.size() == 0){
			return null;
		}
		else{
			return actions.getLast();
		}
    }
	public Action PopLast(){
		//pop the last action in actions
		if(actions.size() == 0){
			return null;
		}
		else{
			return actions.removeLast();
		}
	}
	public Action PopFirst(){
		//pop the first action in actions
		if(actions.size() == 0){
			return null;
		}
		else{
			return actions.removeFirst();
		}
	}
	public Action getHeroAction(){
		//get the first action in actions
		if(actions.size() == 0){
			return null;
		}
		else{
			return actions.getFirst();
		}
	}
	/*
	 * @Summary: Adds an action to the states Actions list at the last position
	 */
	public void addAction(Action action){
		//check if the action already exists in actions 
		if(!actions.contains(action)){
			actions.addLast(action);
		}

	}
	public LinkedList<Action> getActions(){
		//get all actions in actions
		return (LinkedList<Action>)actions.clone();
	}

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}