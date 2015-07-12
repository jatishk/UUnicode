package logic.instantiate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBMapLoad {

	//start of Class variables
	private static LinkedHashMap<String, String> databaseClipboard = new LinkedHashMap<String, String>();
	private static LinkedHashMap<String, String> dbFileRead = new LinkedHashMap<String, String>();
	//end of Class variables
	
	//cannot create external object
	private DBMapLoad() {
	}

	/*
	 * @exception IOException if corresponding DB file is not found
	 * prepares a Dictionary data and assign it to class variables
	 * Loads only once
	 */
	public static void prepareDictionary() throws IOException, URISyntaxException {

		try {
			databaseClipboard = readDBFile("dbClip.txt");
		} catch (IOException e) {
			Logger.getLogger(DBMapLoad.class.getName()).log(Level.SEVERE, null, e);
			throw e;
		}
		try {
			dbFileRead = readDBFile("database.txt");
		} catch (IOException e) {
			Logger.getLogger(DBMapLoad.class.getName()).log(Level.SEVERE, "File databse.txt is not found.", e);
			throw e;
		}
	}

	/*
	 *@param fleName read the data from the file name or file path
	 *@return HashMap dictionary preparation is done 
	 *@exception IOException if file is not found to prepare dictionary
	 */
	private static LinkedHashMap<String, String> readDBFile(String fileName)
			throws IOException, URISyntaxException {
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
				Logger.getLogger(DBMapLoad.class.getName()).log(Level.INFO,DBMapLoad.class.getResource(fileName).toURI().toString());
				in = DBMapLoad.class.getResourceAsStream("\\resources\\"+fileName);
				readFile = new BufferedReader(new InputStreamReader(in));
			} catch (URISyntaxException e) {
				Logger.getLogger(DBMapLoad.class.getName()).log(Level.SEVERE, "Exception while loading DB file.", e);
				throw e;
			}catch(NullPointerException e){
				readFile = new BufferedReader(new FileReader(fileName));
			}

			while ((line = readFile.readLine()) != null) {
				lineFromFile = line.trim().split(",");
				if (lineFromFile[1] != null)
					tempMap.put(lineFromFile[0], lineFromFile[1]);
			}
			return tempMap;
		} finally {
			if (readFile != null)
				readFile.close();
		}
	}

	/*
	 *@param flagFile to retrieve HashMap based on value
	 *@return static final HashMap loaded from the DB file
	 *@exception no exception is thrown
	 */
	public static final LinkedHashMap<String, String> getPreparedDictionary(Boolean flagFile) {
		if (flagFile)
			return dbFileRead;
		return databaseClipboard;

	}
}
