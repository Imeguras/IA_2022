package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// TODO [#16]: make the hero move once per turn
// Body: as said tittle
 

// TODO [#17]: add regular mummy behaviour
// Body: they should move twice per turn, after the hero and they should try to be on the same Y as the hero and then the same X
 


// TODO [#18]: add red mummy behaviour
// Body: they should move twice per turn, after the regular mummy and they should try to be on the same X as the hero and then the same Y
 

// TODO [#19]: add scorpions behaviour
// Body: they should move once per turn, after the red mummies are done, they have the same behaviour as the regular mummy
 

// TODO [#20]: add key behaviour
// Body: they should open gates once the hero stands
 

// TODO [#21]: death square behaviour
// Body: although mummies and other env. stuff can stay on it, hero's cant
 

// TODO [#22]: fighting behaviour
// Body: if two hostile entities stay on the same tile they should kill one in order for the level to have a solution
 

// TODO [#23]: implement search methods to the hero
// Body: as said tittle
public class Main {

    public Main()
	{

		
		MainFrame frame = new MainFrame();
		// Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2,
				  (screenSize.height - frameSize.height) / 2);

		frame.setVisible(true);
    }

    public static void main(String[] args)
	{
        SwingUtilities.invokeLater(new Runnable()
		{
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException |
                         InstantiationException |
                         IllegalAccessException | 
                         UnsupportedLookAndFeelException exception) {
                    exception.printStackTrace(System.err);
   		}
   		new Main();
            }
   		});
    }
}
