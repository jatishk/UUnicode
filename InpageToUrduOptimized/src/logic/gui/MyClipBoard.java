package logic.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClipBoard {

	// Start of variable Declaration
	private String textOnClipBoard;

	// End of variable Declaration

	/*
	 * Gettter and setter to access private instaces outside the class
	 */
	public String getTextOnClipBoard() {
		return textOnClipBoard;
	}

	public void setTextOnClipBoard(String textOnClipBoard) {
		this.textOnClipBoard = textOnClipBoard;
	}

	/*
	 * method uses the system default Clip board tool kit to fetch and populate
	 * the content Verification of plain/text is mandatory or the Text should be
	 * of String flavor
	 */
	public String getClipboardContents() throws IOException, UnsupportedFlavorException {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		/*
		 * odd: the Object param of getContents is not currently used Action to
		 * check wheather the clipboard contents is string or not
		 */
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null)
				&& contents.isDataFlavorSupported(DataFlavor.stringFlavor);

		if (hasTransferableText) {
			try {
				try {
					// Type casting of text as well as copying the content's of
					// string into variable
					this.setTextOnClipBoard((String) contents
							.getTransferData(DataFlavor.stringFlavor));
				} catch (IOException ex) {
					Logger.getLogger(MyClipBoard.class.getName()).log(
							Level.SEVERE, null, ex);
					throw ex;
				}
			} catch (UnsupportedFlavorException ex) {
				Logger.getLogger(MyClipBoard.class.getName()).log(
						Level.SEVERE, null, ex);
				throw ex;
			}

		}
		// Returning the clip board contents to the method call
		return this.getTextOnClipBoard().trim();
	}
}
