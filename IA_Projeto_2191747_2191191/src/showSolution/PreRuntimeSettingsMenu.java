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
    //TODO Non Hardcoded
    private static String dir_path="/home/micron/sav/Trabalhos/2021-2022/2ºSemestre/Inteligencia_artificial/Projeto/IA_Projeto_2191747_2191191/IA_2022/IA_Projeto_2191747_2191191/niveis";
    public PreRuntimeSettingsMenu(Main context) {
        super();
        preRuntime = new JFrame();
        //TODO centrar o menu ao ecrã
        preRuntime.setBounds(0, 0, 500, 500);
        preRuntime.setFocusable(true);
        preRuntime.setVisible(true);
        preRuntime.setLayout(new BorderLayout());

        area_Pre = new JPanel();
        area_Pre.setAlignmentX(0.5f);
        area_Pre.setAlignmentY(0f);
        //area_Pre.setPreferredSize(preRuntime.getSize());
        preRuntime.setContentPane(area_Pre);

        combo_LevelSelector = new JComboBox();
        combo_LevelSelector.setMinimumSize(new Dimension(400,32));
        combo_LevelSelector.setPreferredSize(new Dimension(400,32));
        combo_LevelSelector.setAlignmentX(0);

        //combo_LevelSelector.setLocation(32,32);
        File dir = new File(dir_path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                combo_LevelSelector.addItem(child.getName());
            }

        } else {


            //TODO line 10
        }


        btn_solve = new JButton("Preview Map");
        btn_solve.setMinimumSize(new Dimension(86, 32));
        btn_solve.setPreferredSize(btn_solve.getMinimumSize());
        btn_solve.setAlignmentX(1);

        btn_solve.addActionListener(e -> {
            //TODO bad dir'ing
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
        preRuntime.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public JFrame getContext(){
        return this.preRuntime;
    }


}
