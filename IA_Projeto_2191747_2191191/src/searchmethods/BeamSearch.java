package searchmethods;

import agent.State;
import utils.NodePriorityQueue;

public class BeamSearch extends AStarSearch {

    private int beamSize;

    public BeamSearch() {
        this(100);
    }

    public BeamSearch(int beamSize) {
        this.beamSize = beamSize;
    }

    @Override
    public void addSuccessorToFrontier(State successor, Node parent)
    {
		super.addSuccessorToFrontier(successor, parent);
        NodePriorityQueue aux = new NodePriorityQueue();
        while (frontier.size()< beamSize){
            aux.add(frontier.remove());
        }
        frontier= aux;
        // elimina os piores nos da fronteira(meno prioridade) cate a fronteira ficar com tamanho beamSize
       

    }

    public void setBeamSize(int beamSize) {
        this.beamSize = beamSize;
    }

    public int getBeamSize() {
        return beamSize;
    }

    @Override
    public String toString() {
        return "Beam search";
    }
}
