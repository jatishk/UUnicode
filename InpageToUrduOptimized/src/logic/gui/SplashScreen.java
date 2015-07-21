package logic.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import logic.logger.LoggerFormat;

public class SplashScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3357042946402567175L;
	private JLabel imglabel;
	private ImageIcon img;
	private static JProgressBar pbar;
	Thread t = null;

	public SplashScreen() {
		super("Splash");

		setSize(635, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setResizable(false);

		img = new ImageIcon(getClass().getResource("/img/splash.png"));
		imglabel = new JLabel(img);

		setLayout(null);
		imglabel.setBounds(0, 0, 630, 320);

		pbar = new JProgressBar();
		pbar.setMinimum(0);
		pbar.setMaximum(100);
		pbar.setStringPainted(true);
		pbar.setForeground(Color.RED);
		pbar.setPreferredSize(new Dimension(310, 30));
		pbar.setBounds(0, 290, 404, 20);

		add(imglabel);
		add(pbar);
		
		Thread t = new Thread() {

			public void run() {
				int i = 0;
				while (i <= 100) {
					pbar.setValue(i);
					try {
						sleep(90);
					} catch (InterruptedException ex) {
						LoggerFormat.getLogger().log(Level.SEVERE, null, ex);
					}
					i++;
				}
			}
		};
		t.start();
	}
}