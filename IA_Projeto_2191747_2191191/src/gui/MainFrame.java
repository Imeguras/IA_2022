package gui;

import agent.Agent;
import agent.Heuristic;
import agent.Solution;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import mummymaze.MummyMazeAgent;
import mummymaze.MummyMazeProblem;
import mummymaze.MummyMazeState;
import searchmethods.BeamSearch;
import searchmethods.DepthLimitedSearch;
import searchmethods.SearchMethod;
import utils.Configuration;

public class MainFrame extends JFrame {
    //
	private MummyMazeAgent agent;// = new MummyMazeAgent(new MummyMazeState());
	private JFileChooser fileChooser_dialog;
	private JButton btnOpenFileDialog;
    private JComboBox<SearchMethod> comboBoxSearchMethods;
    private JComboBox<Heuristic> comboBoxHeuristics;
    private JLabel labelSearchParameter; 
    private JTextField textFieldSearchParameter;
    private JButton buttonInitialState; 
	private JComboBox<String> combo_LevelSelector;
    private JButton buttonSolve;
    private JButton buttonStop;
    private JButton buttonShowSolution;
    private JButton buttonReset;
    private JTextArea textArea;
    private GameArea gameArea;

	private Configuration conf;
	private static String dir_path="./IA_Projeto_2191747_2191191/niveis";
    private String toopen="";

    public MainFrame() {
		agent= new MummyMazeAgent();
		btnOpenFileDialog=new JButton("Path");
		labelSearchParameter= new JLabel("limit/beam size:");
		textFieldSearchParameter= new JTextField("0", 5);
		buttonInitialState= new JButton("Preview Selected Level");
		buttonSolve = new JButton("Solve Selected Level");
		buttonStop = new JButton("Stop Solving");
		buttonShowSolution= new JButton("Show solution");
		buttonReset = new JButton("Reset to initial state");

		conf= new Configuration("app.config");
		dir_path=conf.getDefaultDir();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private void jbInit() throws Exception {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Mummy Maze Solver");
		fileChooser_dialog = new JFileChooser(dir_path);

        fileChooser_dialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser_dialog.addActionListener(e->{
			try {
				filePicker_ActionBehaviour(e);
			} catch (Exception filePickerException) {
				System.out.println(filePickerException.getMessage());	
			}
			
		});
        
		combo_LevelSelector = new JComboBox<String>();
        combo_LevelSelector.setMinimumSize(new Dimension(150,32));
        combo_LevelSelector.setPreferredSize(new Dimension(150,32));
        populateDirComboBox();
		//agent= new MummyMazeState();
		buttonStop.setEnabled(false);
		buttonShowSolution.setEnabled(false);
		buttonReset.setEnabled(false);
		
		JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel panelButtons = new JPanel(new FlowLayout());

		buttonSolve.addActionListener(new ButtonSolve_ActionAdapter(this));
		buttonInitialState.addActionListener(new ButtonInitialState_ActionAdapter(this));
		buttonStop.addActionListener(new ButtonStop_ActionAdapter(this));
        buttonShowSolution.addActionListener(new ButtonShowSolution_ActionAdapter(this));
        buttonReset.addActionListener(new ButtonReset_ActionAdapter(this));
		btnOpenFileDialog.addActionListener(e->{
            fileChooser_dialog.showOpenDialog(new JPanel());
        });
		
        panelButtons.add(btnOpenFileDialog);
		panelButtons.add(combo_LevelSelector); 
		panelButtons.add(buttonInitialState);
        panelButtons.add(buttonSolve);
        panelButtons.add(buttonStop);
		panelButtons.add(buttonShowSolution);
		panelButtons.add(buttonReset);
	

        JPanel panelSearchMethods = new JPanel(new FlowLayout());
		comboBoxHeuristics = new JComboBox<Heuristic>(Agent.heuristics_st);
        comboBoxSearchMethods = new JComboBox<SearchMethod>(Agent.searchMethods_st);
        
		comboBoxSearchMethods.addActionListener(new ComboBoxSearchMethods_ActionAdapter(this));
		textFieldSearchParameter.addKeyListener(new TextFieldSearchParameter_KeyAdapter(this));
		comboBoxHeuristics.addActionListener(new ComboBoxHeuristics_ActionAdapter(this));

		labelSearchParameter.setEnabled(false);
		textFieldSearchParameter.setEnabled(false);
		comboBoxHeuristics.setEnabled(false);

		panelSearchMethods.add(comboBoxSearchMethods);
		panelSearchMethods.add(labelSearchParameter);
		panelSearchMethods.add(textFieldSearchParameter);
        panelSearchMethods.add(comboBoxHeuristics);

        textFieldSearchParameter.setHorizontalAlignment(JTextField.RIGHT);
        
        JPanel puzzlePanel = new JPanel(new FlowLayout());
        gameArea = new GameArea();//agent.getEnvironment()
		textArea = new JTextArea(15, 31);
		JScrollPane scrollPane = new JScrollPane(textArea);
        
        textArea.setEditable(false);

        puzzlePanel.add(scrollPane);
		puzzlePanel.add(gameArea);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panelButtons, BorderLayout.NORTH);
        mainPanel.add(panelSearchMethods, BorderLayout.CENTER);
        mainPanel.add(puzzlePanel, BorderLayout.SOUTH);
        contentPane.add(mainPanel);

        pack();
    }
	/*
		@Summary Fill's the Directory/File Combobox with selectable levels
		
	*/
	public void populateDirComboBox() throws IOException{
		
		File dir = new File(dir_path);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				combo_LevelSelector.addItem(child.getName());
			}

		} else {
			throw new IOException("Either the directory/file doesn't exist, is empty or you lack permissions");
		}
    }
	public void filePicker_ActionBehaviour(ActionEvent e) throws IOException{
		
			try {
				if(fileChooser_dialog.getSelectedFile().isDirectory()){
					toopen="";
					
					dir_path=fileChooser_dialog.getSelectedFile().getAbsolutePath();
					combo_LevelSelector.removeAllItems();
					combo_LevelSelector.enableInputMethods(true);
					populateDirComboBox();
				}else if(fileChooser_dialog.getSelectedFile().isFile()){
					
					toopen=fileChooser_dialog.getSelectedFile().getAbsolutePath();
					combo_LevelSelector.removeAllItems();
					combo_LevelSelector.addItem(toopen);
					combo_LevelSelector.setSelectedItem(toopen);
					combo_LevelSelector.enableInputMethods(false);
	
				}
			} catch (IOException exc) {
				throw exc;
			}
            
        
	}
    public void buttonInitialState_ActionPerformed(ActionEvent e) {
		if(toopen.isEmpty()){
			toopen= dir_path+"/"+combo_LevelSelector.getSelectedItem().toString();
		}
		try{
			gameArea.setState(MummyMazeAgent.readStateFromFile(toopen));
			toopen="";
		}catch (Exception k){
			System.err.println(k.getMessage());

		}
    }

    public void comboBoxSearchMethods_ActionPerformed(ActionEvent e) {
        int index = comboBoxSearchMethods.getSelectedIndex();

        agent.setSearchMethod(Agent.searchMethods_st[index]);
        //gameArea.setState(agent.resetEnvironment());
        buttonSolve.setEnabled(true);
        buttonShowSolution.setEnabled(false);
        buttonReset.setEnabled(false);
        //also here?
		textArea.setText("");
        comboBoxHeuristics.setEnabled(index>4); //Informed serch methods
        textFieldSearchParameter.setEnabled(index == 3 || index == 7); // limited depth or beam search
        labelSearchParameter.setEnabled(index == 3 || index == 7); // limited depth or beam search
    }

    public void comboBoxHeuristics_ActionPerformed(ActionEvent e) {
        int index = comboBoxHeuristics.getSelectedIndex();
        agent.setHeuristic(Agent.heuristics_st[index]);
        //gameArea.setState(agent.resetEnvironment());
        buttonSolve.setEnabled(true);
        buttonShowSolution.setEnabled(false);
        buttonReset.setEnabled(false);
        //whats the point here?
		textArea.setText("");
    }
	// TODO: Fix the enabled and disabled buttons
	// BODY: During the show solution, or solve the level, etc... some buttons are enabled when they shouldn't, various functions should be created as to spare coding time and documentation
    public void buttonSolve_ActionPerformed(ActionEvent e) {

        SwingWorker worker = new SwingWorker<Solution, Void>() {
            @Override
            public Solution doInBackground() {
                textArea.setText("");
                buttonStop.setEnabled(true);
                buttonSolve.setEnabled(false);
				buttonInitialState.setEnabled(false);
				btnOpenFileDialog.setEnabled(false);
				if(toopen.isEmpty()){
					toopen= dir_path+"/"+combo_LevelSelector.getSelectedItem().toString();
				}
                try {
					agent.readInitialStateFromFile(toopen);
					toopen="";
                    prepareSearchAlgorithm();
					MummyMazeState a = agent.getEnvironment().clone();
                    MummyMazeProblem problem = new MummyMazeProblem(a);
					
                    agent.solveProblem(problem);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
                return null;
            }

            @Override
            public void done() {
                if (!agent.hasBeenStopped()) {
                    textArea.setText(agent.getSearchReport());
                    if (agent.hasSolution()) {
                        buttonShowSolution.setEnabled(true);
                    }
                }
				buttonInitialState.setEnabled(true);
				btnOpenFileDialog.setEnabled(true);
                buttonSolve.setEnabled(true);
                buttonStop.setEnabled(false);
            }
        };

        worker.execute();
    }

    public void buttonStop_ActionPerformed(ActionEvent e) {
        agent.stop();
        buttonShowSolution.setEnabled(false);
        buttonStop.setEnabled(false);
        buttonSolve.setEnabled(true);
    }

    public void buttonShowSolution_ActionPerformed(ActionEvent e) {
		gameArea.setState(agent.resetEnvironment());
        
        buttonShowSolution.setEnabled(false);
        buttonStop.setEnabled(false);
        buttonSolve.setEnabled(false);
        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() {
				
				
                agent.executeSolution();
                buttonReset.setEnabled(true);
                return null;
            }

            @Override
            public void done() {
                buttonShowSolution.setEnabled(true);
                buttonSolve.setEnabled(true);
            }
        };
        worker.execute();
    }

    public void buttonReset_ActionPerformed(ActionEvent e) {
        gameArea.setState(agent.resetEnvironment());
        buttonShowSolution.setEnabled(true);
        buttonReset.setEnabled(false);
    }

    private void prepareSearchAlgorithm() {
        if (agent.getSearchMethod() instanceof DepthLimitedSearch) {
            DepthLimitedSearch searchMethod = (DepthLimitedSearch) agent.getSearchMethod();
            searchMethod.setLimit(Integer.parseInt(textFieldSearchParameter.getText()));
        } else if (agent.getSearchMethod() instanceof BeamSearch) {
            BeamSearch searchMethod = (BeamSearch) agent.getSearchMethod();
            searchMethod.setBeamSize(Integer.parseInt(textFieldSearchParameter.getText()));
        }
    }
}

class ComboBoxSearchMethods_ActionAdapter implements ActionListener {

    private final MainFrame adaptee;

    ComboBoxSearchMethods_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.comboBoxSearchMethods_ActionPerformed(e);
    }
}

class ComboBoxHeuristics_ActionAdapter implements ActionListener {

    private final MainFrame adaptee;

    ComboBoxHeuristics_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.comboBoxHeuristics_ActionPerformed(e);
    }
}

class ButtonInitialState_ActionAdapter implements ActionListener {

    private final MainFrame adaptee;

    ButtonInitialState_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.buttonInitialState_ActionPerformed(e);
    }
}

class ButtonSolve_ActionAdapter implements ActionListener {

    private final MainFrame adaptee;

    ButtonSolve_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.buttonSolve_ActionPerformed(e);
    }
}

class ButtonStop_ActionAdapter implements ActionListener {

    private final MainFrame adaptee;

    ButtonStop_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.buttonStop_ActionPerformed(e);
    }
}

class ButtonShowSolution_ActionAdapter implements ActionListener {

    private final MainFrame adaptee;

    ButtonShowSolution_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.buttonShowSolution_ActionPerformed(e);
    }
}

class ButtonReset_ActionAdapter implements ActionListener {

    private final MainFrame adaptee;

    ButtonReset_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.buttonReset_ActionPerformed(e);
    }
}

class TextFieldSearchParameter_KeyAdapter implements KeyListener {

    private final MainFrame adaptee;

    TextFieldSearchParameter_KeyAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            e.consume();
        }
    }
}
