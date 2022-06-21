package utils;

import agent.State;
import java.util.Queue;
import searchmethods.Node;

public interface NodeCollection extends Queue<Node> {
    public boolean containsState(State e);
	//Override poll() so that it returns the lowest but ignores 0
	
}
