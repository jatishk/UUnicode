/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.instantiate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.gui.BuildGUI;
import logic.gui.SplashScreen;
import logic.translate.InputParser;

/**
 * 
 * @author Jatish Khanna Start Date: April/12/2014 End Date: May/01/2014
 *         Database Type: Local File (database.txt) Application Type: Swing
 * 
 **/
public class Main {
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		// Splash startup screen method call
		splashView();
		try {
			new BuildGUI(InputParser.getInstance());
		} catch (IOException e1) {
			LOGGER.log(Level.SEVERE, "Not able to find file", e1);
			System.exit(0);
		} catch (URISyntaxException e) {
			Logger.getLogger(DBMapLoad.class.getName()).log(Level.SEVERE,
					"Exception while loading DB file.", e);
			System.exit(0);
		}
	}

	private static void splashView() {
		SplashScreen s = new SplashScreen();
		s.setVisible(true);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			Logger.getLogger(DBMapLoad.class.getName()).log(Level.SEVERE, null,
					e);
		}
		s.dispose();
	}
}
