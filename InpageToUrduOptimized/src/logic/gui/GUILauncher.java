/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.logger.LoggerFormat;
import logic.translate.ConversionOnline;

/**
 * 
 * @author Jatish Khanna Start Date: April/12/2014 End Date: May/01/2014
 *         Database Type: Local File (database.txt) Application Type: Swing
 * 
 **/
public class GUILauncher {
	private final static Logger LOGGER = LoggerFormat.getLogger();

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		// Splash startup screen method call
		splashView();
		try {
			new BuildGUI(ConversionOnline.getInstance());
		} catch (IOException e1) {
			LOGGER.log(Level.SEVERE, "Not able to find file", e1);
			System.exit(0);
		} 
	}

	private static void splashView() {
		SplashScreen s = new SplashScreen();
		s.setVisible(true);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			LoggerFormat.getLogger().log(Level.SEVERE, null, e);
		}
		s.dispose();
	}
}
