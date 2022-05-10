package searchmethods;

import agent.WhiteMummyState;

public class Node implements Comparable<Node> {

    private WhiteMummyState whiteMummyState;
    private double cost;
    private double f;
    private Node parent;
    private int depth;

    public Node(WhiteMummyState whiteMummyState) {
        this.whiteMummyState = whiteMummyState;
    }

    public Node(WhiteMummyState whiteMummyState, Node parent) {
        this(whiteMummyState, parent, 0, 0);
    }    
    
    public Node(WhiteMummyState whiteMummyState, Node parent, double cost, double f) {
        this.whiteMummyState = whiteMummyState;
        this.cost = cost;
        this.f = f;
        this.parent = parent;
        this.depth = parent.depth + 1;
    }

    public WhiteMummyState getState() {
        return whiteMummyState;
    }

    public double getG() {
        return cost;
    }

    public double getF() {
        return f;
    }

    public Node getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public int compareTo(Node other) {
        return (f < other.f) ? -1 : (f == other.f) ? 0 : 1;
    }

    public boolean isCycle(WhiteMummyState whiteMummyState) {
        Node aux = this;
        do{
            if (whiteMummyState.equals(aux.getState())) {
                return true;
            }
            aux = aux.getParent();
        }while (aux != null);

        return false;
    }
    
    @Override
    public String toString(){
        return "" + f;
    }
}