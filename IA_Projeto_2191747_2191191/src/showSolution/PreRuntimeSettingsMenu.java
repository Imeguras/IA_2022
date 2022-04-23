package showSolution;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;

public class PreRuntimeSettingsMenu extends JFrame{

    private JFrame preRuntime;
    private JPanel area_Pre;
    private JComboBox combo_LevelSelector;
    private JButton btn_solve;
    /*
     @todo add a directory picker
     @body maybe one that enables either picking an entire directory with a selectionable combobox or a just a file
      */
    private static String dir_path="/home/micron/sav/Trabalhos/2021-2022/2ºSemestre/Inteligencia_artificial/IA_2022/IA_Projeto_2191747_2191191/niveis";
    public PreRuntimeSettingsMenu(Main context) {
        super("Temple Maze Solver Ainet 2022");
        preRuntime = new JFrame();
        //TODO centrar o menu ao ecrã
        preRuntime.setSize(600,600);
        preRuntime.setLocationRelativeTo(null);
        //preRuntime.setBounds(0, 0, 500, 500);

        preRuntime.setLayout(new BorderLayout());

        area_Pre = new JPanel();

        area_Pre.setAlignmentX(0.5f);
        area_Pre.setAlignmentY(0f);


        combo_LevelSelector = new JComboBox();
        combo_LevelSelector.setMinimumSize(new Dimension(400,32));
        combo_LevelSelector.setPreferredSize(new Dimension(400,32));
        combo_LevelSelector.setAlignmentX(0);


        File dir = new File(dir_path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                combo_LevelSelector.addItem(child.getName());
            }

        } else {
            /*
            @todo Fix case dir_path does not exist or is not readable
            @body line 52 PreRuntimeSettingsMenu
             */
        }


        btn_solve = new JButton("Preview Map");
        btn_solve.setMinimumSize(new Dimension(86, 32));
        btn_solve.setPreferredSize(btn_solve.getMinimumSize());
        btn_solve.setAlignmentX(1);

        btn_solve.addActionListener(e -> {
            /*
            @todo Fix bad dir'ing
            @body 65 PreRuntimeSettingsMenu
             */
            String toopen= dir_path+"/"+combo_LevelSelector.getSelectedItem().toString();
            try{
                Main.removeAllStates();
                File fl_toopen = new File(toopen);
                FileReader fl_r= new FileReader(fl_toopen);
                char[] t = new char[Main.levelCharSize];
                fl_r.read(t);
                String content = String.valueOf(t);
                System.out.println(content);
                Main.addState(content);
                context.showState(context.getStates_cloned().getFirst());

            }catch (Exception k){
                System.err.println(k.getMessage());

            }
        });


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


}
