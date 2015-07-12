/*
 * InpToUUView.java
 * Author @ Jatish Khanna
 *
 * This is Java code that deals with the view defination of the project
 * The view produced contains GUI interface that handles the input as
 * wwll as the coonvertedtext
 */

package logic.gui;

/*
 *	Project Related Imports
 */

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import logic.exception.InvalidDataException;
import logic.translate.InputParser;

/**
 * @author Jatish Khanna
 * @version 3.0
 * @since April/12/2014
 */

@SuppressWarnings("serial")
public class BuildGUI extends javax.swing.JFrame implements ActionListener {

	// Start of Variable Declaration
	private JButton startConversion = new JButton();
	private JButton quitApp = new JButton();
	private JButton pasteClipboard = new JButton();
	private JButton openButton = new JButton("Open");

	private javax.swing.JScrollPane scrollEditor = new javax.swing.JScrollPane();;
	private javax.swing.JTextPane editor = new javax.swing.JTextPane();

	private static final Font textFont = new Font("Alvi Nastaleeq v1.0.0",
			Font.PLAIN, 20);

	long start = 0;
	private static InputParser textParser = null;
	private File inpageFile = null;
	private final static Logger logger = Logger.getLogger(BuildGUI.class
			.getName());
	private static FileHandler logHandler;
	private TextUI textUI = new TextUI();

	// End Of variable Declaration

	/*
	 * @param inputParser to initialize the reference of InputParser class
	 * 
	 * @see To Initialize the GUI components using call to initCoponents()method
	 */
	public BuildGUI(InputParser inputParser) {

		// Application default close operation
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

		textParser = inputParser; // Initializing the static reference from
									// constructor argument

		// "initComponents" user defined method which initializes the controls
		// and layout of GUI
		initComponents();

	}

	
	/**
	 * This method is called from within the constructor to initialize the GUI.
	 * Initialize-
	 * 		Logger components
	 * 		GUI application
	 * 		Format controls
	 */

	public void initComponents() {

		//Initialize logger reference
		loggerFormat();
		
		//format all the UI components (width, font...)
		formatControls();


		/*
		 * "Group Layout" as arrangement to place components on container 
		 * defines how the components have been placed onto container window
		 */

		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(mainPanelLayout);
		mainPanelLayout
				.setHorizontalGroup(mainPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								mainPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												mainPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																mainPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				scrollEditor,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				738,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																mainPanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				startConversion)
																		.addGap(58,
																				58,
																				58)
																		.addComponent(
																				pasteClipboard)
																		.addGap(58,
																				58,
																				58)
																		.addComponent(
																				quitApp)
																		.addGap(120,
																				120,
																				120)
																		.addComponent(
																				openButton)
																		.addGap(120,
																				120,
																				120)))));
		mainPanelLayout
				.setVerticalGroup(mainPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								mainPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												scrollEditor,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												370,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												mainPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																startConversion)
														.addComponent(
																pasteClipboard)
														.addComponent(quitApp)
														.addComponent(
																openButton))
										.addContainerGap(21, Short.MAX_VALUE)));
		
		/*
		 * Action Listener event handler for "openButton"
		 * Listens to the user defined events (mouse click)
		 * Uses swing components to load file (File Chooser)
		 * Restriction - only inpage file(*.inp)can be uploaded
		 */

		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();

				int option = chooser.showOpenDialog(BuildGUI.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					inpageFile = chooser.getSelectedFile();
					if (inpageFile.getName().endsWith(".inp")) {
						try {
							//File is read byte by byte
							//the return result is stored as String in TextUI class
							textUI.setEditorOriginalData(TextUI
									.readFile(inpageFile));
						} catch (IOException e) {
							logger.log(Level.SEVERE, e.getMessage());
						}

						editor.setText("");
						editor.setText(textUI.getEditorOriginalData());
						//Flag defines the Map to be loaded for file (Clip board map[false] or File map[true])
						textUI.setFileFlag(true);
					}
				}
			}
		});

		/*
		 * A user-defined size details as well visualizing container or Frame
		 * with the components
		 */
		setLayout(mainPanelLayout);
		setVisible(true);
		setSize(800, 600);
		setName("inpage_to_unicode");
		setTitle("Inpage to Unicode");
	}

	/*
	 * actionPerformed method associated to events occurred at Runtime.
	 * event starConversion  to start the conversion of text present in the clip board
	 * event pasteClipboard  to get the Clip board contents using clipboard class 
	 * event Event: (exit) to close and terminate application
	 * @exception InvalidDataException when content length is not valid
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		textUI.setEditorOriginalData(editor.getText());
		if (actionEvent.getSource().equals(startConversion)) {

			// Start Time captured to know the conversion time taken.
			start = System.currentTimeMillis();
			try {

				
				if (textUI.getEditorOriginalData().length() < 1)
					throw new InvalidDataException();
				
				textUI.setEditorTranslatedData(textParser.doConversion(
						textUI.getEditorOriginalData(), textUI.getFileFlag()));
				/*
				 * refreshing the editor area with Translated content
				 * reset the value of flag file
				 */
				editor.setText(textUI.getEditorTranslatedData());
				textUI.setFileFlag(false);
			} catch (InvalidDataException exception) {
				/*
				 * Exception is thrown when the data or text area' has not data
				 * or less than a char to converted after trimming Message is
				 * not logged will be displayed at the console
				 */
				logger.log(Level.SEVERE, exception.getMessage());
			}finally{
				 long time = System.currentTimeMillis() - start;
				 logger.log(Level.INFO, "Text conversion is done in "+time+" ms.");
			}

		} else if (actionEvent.getSource().equals(pasteClipboard)) {
			/*
			 * to fetch the contents from clip board 
			 * verification of plain/text is done 
			 * Restriction: not other than plain/text will be allowed
			 * @exception from the associated class
			 */
			try {
				textUI.setEditorOriginalData(new MyClipBoard()
						.getClipboardContents());
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
			} catch (UnsupportedFlavorException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}

			editor.setText(textUI.getEditorOriginalData());
			textUI.setFileFlag(false);
		} else {
			//perform the normal termination as well
			// disposing the program.
			System.exit(0);
		}

	}

	public void formatControls() {
		/*
		 *Components are being initialized
		 *Associating actionListener to objects
		 */
		editor.setToolTipText("Paste your Content to Convert..");
		editor.setName("ASCII_Editor");
		editor.setFont(textFont);
		editor.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollEditor.setViewportView(editor);
		editor.setText("");

		startConversion.setText("Convert");
		startConversion.setToolTipText("Click To convert");
		startConversion.setName("convertUU");
		if (startConversion.getActionListeners() != null)
			startConversion.removeActionListener(this);
		startConversion.addActionListener(this);

		quitApp.setText("Exit");
		quitApp.setToolTipText("Click to Exit..");
		quitApp.setName("exitOp");
		if (quitApp.getActionListeners() != null)
			quitApp.removeActionListener(this);
		quitApp.addActionListener(this);

		pasteClipboard.setText("Paste");
		pasteClipboard.setToolTipText("Click To Patse..");
		pasteClipboard.setName("pasteClip");
		if (pasteClipboard.getActionListeners() != null)
			pasteClipboard.removeActionListener(this);
		pasteClipboard.addActionListener(this);
	}
	
	/*
	 *associating the logger FileHandler
	 *specify the logger formatter
	 */
	
	private static void loggerFormat(){
		try {
			logHandler = new FileHandler("ustan-log.%u.%g.txt", true);
		} catch (SecurityException e2) {
			logger.log(Level.SEVERE, e2.getMessage());
		} catch (IOException e2) {
			logger.log(Level.SEVERE, e2.getMessage());
		}
		logger.addHandler(logHandler);
		logHandler.setFormatter(new SimpleFormatter());
	}

}
