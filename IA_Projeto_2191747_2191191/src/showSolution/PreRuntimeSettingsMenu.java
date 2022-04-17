package showSolution;


import javax.swing.*;
import java.io.Console;
import java.io.File;

public class PreRuntimeSettingsMenu extends JFrame{
    private JFrame PreRuntime;
    private JComboBox LevelSelector;
    //TODO Non Hardcoded
    private static String dir_path="/home/micron/sav/Trabalhos/2021-2022/2ºSemestre/Inteligencia_artificial/Projeto/IA_Projeto_2191747_2191191/IA_2022/IA_Projeto_2191747_2191191/niveis";
    public PreRuntimeSettingsMenu() {
        PreRuntime = new JFrame();
        //TODO centrar o menu
        PreRuntime.setBounds(0, 0, 500, 500);
        PreRuntime.setFocusable(true);
        PreRuntime.setVisible(true);
        LevelSelector = new JComboBox();

        File dir = new File(dir_path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                LevelSelector.addItem(child.getName());
            }

        } else {


            //TODO line 10
        }

        PreRuntime.add(LevelSelector);
        //getContext().setContentPane(PreRuntime);
        PreRuntime.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //PreRuntime.pack();
    }
    public JFrame getContext(){
        return this.PreRuntime;
    }
}
