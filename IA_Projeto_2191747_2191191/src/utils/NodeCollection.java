package utils;

import agent.WhiteMummyState;
import java.util.Queue;
import searchmethods.Node;

public interface NodeCollection extends Queue<Node> {
    public boolean containsState(WhiteMummyState e);
}
