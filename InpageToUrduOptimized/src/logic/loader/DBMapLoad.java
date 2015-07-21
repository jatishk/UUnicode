package logic.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.logging.Level;

import logic.logger.LoggerFormat;

public class DBMapLoad {

	// start of Class variables

	private static LinkedHashMap<String, String> databaseClipboard = new LinkedHashMap<String, String>(100); //
	// For clipboard content conversion
	private static LinkedHashMap<String, String> dbFileRead = new LinkedHashMap<String, String>(100);
	// For input file conversion

	// end of Class variables

	// cannot create external object
	private DBMapLoad() {
	}

	/*
	 * @exception IOException if corresponding DB file is not found prepares a
	 * Dictionary data and assign it to class variables Loads only once
	 */
	public static void prepareDictionary() throws IOException {

		// File mapping for clip board content
		try {
			databaseClipboard = readDBFile("dbclip");
		} catch (IOException e) {
			LoggerFormat.getLogger().log(Level.SEVERE, "File name \"dbclip\" is not found.", e);
			throw e;
		}
		// File has mapping when reading from file
		try {
			dbFileRead = readDBFile("database");
		} catch (IOException e) {
			LoggerFormat.getLogger().log(Level.SEVERE, "File name \"database\" is not found.", e);
			throw e;
		}
	}

	/*
	 * @param fleName read the data from the file name or file path
	 * 
	 * @return HashMap dictionary preparation is done
	 * 
	 * @exception IOException if file is not found to prepare dictionary
	 */
	private static LinkedHashMap<String, String> readDBFile(String fileName) throws IOException {
		String[] lineFromFile = new String[2];
		String line = "";
		BufferedReader readFile = null;
		LinkedHashMap<String, String> tempMap = new LinkedHashMap<String, String>();
		InputStream in;
		try {

			/*
			 * Logic: reading the contents of the file line by line - Splitting
			 * the line based on delimiter "," - Validating for the null
			 * mappings - Populating the map - Once called while object
			 * instantiation
			 */
			try {
				LoggerFormat.getLogger().log(Level.INFO, DBMapLoad.class.getResource("/resources/" + fileName).toString());
				in = DBMapLoad.class.getClass().getResource("/resources/" + fileName).openStream();
				readFile = new BufferedReader(new InputStreamReader(in));
			}  catch (NullPointerException e) {
				readFile = new BufferedReader(new FileReader(fileName));
			}

			while ((line = readFile.readLine()) != null) {
				lineFromFile = line.trim().split(",");
				if (lineFromFile[1] != null)
					tempMap.put(lineFromFile[0], lineFromFile[1]);
			}
			return tempMap;
		} finally  {
			if (readFile != null)
				readFile.close();
		}
	}

	/*
	 * @param flagFile to retrieve HashMap based on value
	 * 
	 * @return static final HashMap loaded from the DB file
	 * 
	 * @exception no exception is thrown
	 */
	public static final LinkedHashMap<String, String> getPreparedDictionary(Boolean flagFile) {
		if (flagFile)
			return dbFileRead;
		return databaseClipboard;

	}
}
