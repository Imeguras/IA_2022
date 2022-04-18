package showSolution;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;


public class SolutionPanel extends JFrame{

	private GameArea gameArea;
	private static LinkedList<String> lista;
//	/**
//	 * @param args
//	 */
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
		lista= new LinkedList<String>();
		PreRuntimeSettingsMenu init_frm = new PreRuntimeSettingsMenu();

		//showSolution(lista,10);
	}

	private SolutionPanel(){
		super("Show solution");
		gameArea = new GameArea();
		getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gameArea,BorderLayout.CENTER);
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent evt) {
//                // Exit the application
//                System.exit(0);
//            }
//        });
	}
	public static void addState(String state){
		//TODO parse and correct a state
		lista.add(state);
	}
	//TODO: remove
	public static void testInitial(){
		showSolution(lista, lista.size());
	}
	public static void showSolution(final List<String> states, final double solutionCost){
		final SolutionPanel p = new SolutionPanel();
		p.setVisible(true);
		p.pack();		
		Thread t = new Thread(){
            public void run(){
            	p.setSolutionCost(solutionCost);
            	for(String s : states)  {
                	p.setState(s);
                	try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        };
        t.start();
	}

	public static void showState(final String state){
		final SolutionPanel p = new SolutionPanel();
		p.setVisible(true);
		p.pack();
		Thread t = new Thread(){
			public void run(){
				p.setState(state);
				p.setShowSolutionCost(false);
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
