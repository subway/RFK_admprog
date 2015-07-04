package program;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import view.Startup;

/**
 * @author Sabba
 *
 */
public class ProgramStart extends JFrame{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		Startup s = new Startup("start");
		s.setLocationRelativeTo(null);
		s.setVisible(true);
	    s.addWindowListener(
	      new WindowAdapter() 
	      {
	        public void windowClosing( WindowEvent e )
	        {
	          System.exit( 0 );
	        }
	      } );
	    
	    
	}

}
