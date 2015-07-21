package logic.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.exception.InvalidFileToParseException;
import logic.logger.LoggerFormat;

public class InputFileParser {

	// start of Instance variables
	private String editorOriginalData = "";
	private Boolean fileFlag = false;
	private String editorTranslatedData = "";
	private final static Logger logger = LoggerFormat.getLogger();
	// end of Instance variables

	/*
	 * @param inpageFile file path to be loaded having content
	 * 
	 * @return String formated and de-limited output to covert
	 * 
	 * @exception IOException if the file is not there at location
	 */
	public static String readFile(File inpageFile) throws IOException, InvalidFileToParseException {
		BufferedReader bufferReaderCp1252 = new BufferedReader(
				new InputStreamReader(new FileInputStream(inpageFile), "ISO-8859-1"));
		if(inpageFile.length() < 3){
			bufferReaderCp1252.close();
			throw new InvalidFileToParseException("Input file is empty.");
		}	
		char[] charBuffer = new char[(int) inpageFile.length() - 1];
		StringBuffer tempString = new StringBuffer();

		for (int i = 0; i < charBuffer.length; i++) {
			bufferReaderCp1252.read(charBuffer, i, 1);
			tempString.append((char) charBuffer[i]);
			// System.out.println((int)charBuffer[i]);
		}
		bufferReaderCp1252.close();

		try {
			return removeSouceFormat(tempString.toString());
		} catch (InvalidFileToParseException e) {
			logger.log(Level.SEVERE, e.getMessage());
			throw e;
		}
	}

	/*
	 * @param stringHandle the raw data read from the file path
	 * 
	 * @return String the de-limited text to be converted
	 */
	private static String removeSouceFormat(String stringHandle) throws InvalidFileToParseException {
		int first = Math
				.max(stringHandle.indexOf("\u002f\u0000\u0000\u0000\u0004"),
						stringHandle
								.indexOf(
										"\u002f\u0000\u0000\u0000\u0004",
										(stringHandle
												.indexOf("\u002f\u0000\u0000\u0000\u0004") + 5)));
		int last = stringHandle.indexOf("\u0001\u0000\u0000\u0000", first + 1);
		first = first > 0 ? first : 0;
		last = last > 0 ? last : stringHandle.length();
		try{
		stringHandle = stringHandle.substring(first + 5, last - 4);
		}
		catch(StringIndexOutOfBoundsException exception){
			logger.log(Level.SEVERE, exception.getMessage());
			throw new InvalidFileToParseException("Please check the if file is not corrupted.");
		}
	return stringHandle;

	}

	// Accessors and Mutators

	public String getEditorOriginalData() {
		return editorOriginalData.trim();
	}

	public void setEditorOriginalData(String editorOriginalData) {
		this.editorOriginalData = editorOriginalData;
	}

	public Boolean getFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(Boolean fileFlag) {
		this.fileFlag = fileFlag;
	}

	public String getEditorTranslatedData() {
		return editorTranslatedData;
	}

	public void setEditorTranslatedData(String editorTranslatedData) {
		this.editorTranslatedData = editorTranslatedData;
	}
}
