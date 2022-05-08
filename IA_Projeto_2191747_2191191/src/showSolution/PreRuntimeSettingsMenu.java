package showSolution;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;

public class PreRuntimeSettingsMenu extends JFrame{

    private JFrame preRuntime;
    private JPanel area_Pre;
    private JFileChooser dir_chooser;
    private JComboBox combo_LevelSelector;
    private JButton btn_dir;
    private JButton btn_solve;
    private String toopen="";
	private Configuration conf;
    private static String dir_path="./IA_Projeto_2191747_2191191/niveis";
    public PreRuntimeSettingsMenu(Main context) {
        super("Temple Maze Solver Ainet 2022");
		conf= new Configuration("app.config");
		dir_path=conf.getDefaultDir();
        preRuntime = new JFrame();
        preRuntime.setSize(600,600);
        preRuntime.setLocationRelativeTo(null);

        preRuntime.setLayout(new BorderLayout());

        area_Pre = new JPanel();

        area_Pre.setAlignmentX(0.5f);
        area_Pre.setAlignmentY(0f);


        dir_chooser = new JFileChooser(dir_path);
        dir_chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        dir_chooser.addActionListener(e->{
            if(dir_chooser.getSelectedFile().isDirectory()){
				toopen="";
                System.out.println("stuff");
                dir_path=dir_chooser.getSelectedFile().getAbsolutePath();
                combo_LevelSelector.removeAllItems();
                combo_LevelSelector.enableInputMethods(true);
                populateDirComboBox();
            }else if(dir_chooser.getSelectedFile().isFile()){
                System.out.println("stuff2");
                toopen=dir_chooser.getSelectedFile().getAbsolutePath();
                combo_LevelSelector.removeAllItems();
                combo_LevelSelector.addItem(toopen);
                combo_LevelSelector.setSelectedItem(toopen);
                combo_LevelSelector.enableInputMethods(false);

            }else{
                // TODO [$6277ddf6c32cd6000a522e1e]: Catch exception/state after failure in picking a file
                // Body: Basically if a user clicks on path button to choose a file/directory, but after the window pops up he decides to cancel it throws a exception which must be handled
            }
        });
        btn_dir = new JButton("Path");
        btn_dir.setMinimumSize(new Dimension(100,32));
        btn_dir.setPreferredSize(new Dimension(100,32));
        btn_dir.setAlignmentX(0);
        btn_dir.addActionListener(e->{
            dir_chooser.showOpenDialog(new JPanel());
        });

        combo_LevelSelector = new JComboBox();
        combo_LevelSelector.setMinimumSize(new Dimension(300,32));
        combo_LevelSelector.setPreferredSize(new Dimension(300,32));
        combo_LevelSelector.setAlignmentX(0.5f);
        populateDirComboBox();


        btn_solve = new JButton("Preview Map");
        btn_solve.setMinimumSize(new Dimension(86, 32));
        btn_solve.setPreferredSize(btn_solve.getMinimumSize());
        btn_solve.setAlignmentX(1);

        btn_solve.addActionListener(e -> {
            if(toopen.isEmpty()){
                toopen= dir_path+"/"+combo_LevelSelector.getSelectedItem().toString();
                System.out.println(toopen);
            }
            try{
                Main.removeAllStates();
                File fl_toopen = new File(toopen);
                FileReader fl_r= new FileReader(fl_toopen);
                toopen="";
				System.out.println("Detected file size: "+fl_toopen.length());
				char[] t = new char[(int)fl_toopen.length()];
                fl_r.read(t);
                String content = String.valueOf(t);
				fl_r.close();
                System.out.println(content);
                Main.addState(content);
                context.showState(context.getStates_cloned().getFirst());
				

            }catch (Exception k){
                System.err.println(k.getMessage());

            }
        });

        area_Pre.add(btn_dir);
        area_Pre.add(combo_LevelSelector);
        area_Pre.add(btn_solve);

        preRuntime.setContentPane(area_Pre);

        preRuntime.setFocusable(true);
        preRuntime.setVisible(true);

        preRuntime.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public JFrame getContext(){
        return this.preRuntime;
    }
    public void populateDirComboBox(){
        File dir = new File(dir_path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                combo_LevelSelector.addItem(child.getName());
            }

        } else {

            // TODO [#25]: Fix case dir_path does not exist or is not readable
            // Body: line 52 PreRuntimeSettingsMenu

        }
    }

}
