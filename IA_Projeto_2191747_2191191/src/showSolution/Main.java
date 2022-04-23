package showSolution;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Main {
	public static boolean flagBoolean;
	public static final int levelCharSize=181;
	private GameArea gameArea;
	private static LinkedList<String> lista;
	private static JFrame context;

	public static void main(String[] args) {
		final Main main= new Main();
		PreRuntimeSettingsMenu init_frm = new PreRuntimeSettingsMenu(main);
		context=init_frm.getContext();
	}
	public Main(){
		flagBoolean=false;
		lista= new LinkedList<String>();
	}
	public void drawBoard(){
		if(flagBoolean){
			context.remove(gameArea);
		}
		gameArea = new GameArea();
		gameArea.setAlignmentX(0.5f);
		gameArea.setAlignmentY(1);
		context.add(gameArea,BorderLayout.CENTER);
		flagBoolean=true;
	}
	public static void removeAllStates(){
		lista.removeIf(s->s!="");
	}
	public static void addState(String state){
		//TODO Verify the validity of before adding a state
		if(state.length()!=levelCharSize){
			System.err.println("State size mismatch with the supposed theoretical size");
			return;
		}
		byte StairsPresent=0;
		GameArea.state[] possibleStates= GameArea.state.values();
		for (char c: state.toCharArray()) {
			if(!Arrays.stream(GameArea.state.values()).anyMatch(k->(k.getValue()==c?true:false))){
				System.err.println("Invalid state: " + c);
				return;
			}
		}

		lista.add(state);
	}
	public LinkedList<String> getStates_cloned(){
		return new LinkedList<>(lista);
	}
	public void showSolution(final List<String> states, final double solutionCost){
		drawBoard();

		context.setVisible(true);
		Thread t = new Thread(){
            public void run(){
				setSolutionCost(solutionCost);
            	for(String s : states)  {
					setState(s);
                	try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
            }
        };
        t.start();
	}

	public void showState(final String state){
		//final Main p = new Main();
		drawBoard();
		context.setVisible(true);
		Thread t = new Thread(){
			public void run(){
				setState(state);
				setShowSolutionCost(false);
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}


	private void setState(String state){
		gameArea.setState(state);
	}

	public void setShowSolutionCost(boolean showSolutionCost) {
		gameArea.setShowSolutionCost(showSolutionCost);
	}

	private void setSolutionCost(double solutionCost){
		gameArea.setSolutionCost(solutionCost);
	}

}
