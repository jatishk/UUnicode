/*
 * this class defines the method of conversion by
 * - Populating the Map(Inpage,Unicode) from the Database
 * - String length validation
 * - Mapping the Inpage char to Unicode equivalent
 * - features: Local Database
 */

package logic.translate;

import java.io.IOException;
import java.util.LinkedHashMap;

import logic.loader.DBMapLoad;

public class ConversionOnline {

	// Variable Declaration Start -do not modify

	private static ConversionOnline inputParser;

	// End of Variable Declaration

	//Restricts class initialization from external sources
	private ConversionOnline() {
		
	}

	/*
	 * @param textToParse the text data the need to be converted
	 * @param flagFile to choose from the Dictionary based upon value
	 * @return String the translated data
	 */
	public String doConversion(String textToParse, Boolean flagFile) {
		StringBuilder parsedText = null;
		LinkedHashMap<String, String> charConversionMap = DBMapLoad
				.getPreparedDictionary(flagFile);

		// Start of local variables
		String unicodeCurrentChar = "";
		String unicodeNextChar = "";
		String[] tempArray = null;
		parsedText = new StringBuilder();
		char currentChar = ' ';
		char nextChar = ' ';
		String mapSearch = "";
		boolean updateCurrent = true;
		int count = 0;
		// End of local variables

		int lengthStr = textToParse.length();
		count = lengthStr - 1;
		for (int i = 0; i < count; i++)
			//System.out.println((int) textToParse.charAt(i));
		/*
		 * Iterating till the end of String or text
		 */
		if (textToParse.length() > 1) {
			while (count > -1) {
				//
				mapSearch = "";

				// Process char by char
				if (!updateCurrent)
					currentChar = nextChar;
				else
					currentChar = textToParse.charAt(count--);

				unicodeCurrentChar = ((int) currentChar) + "";

				/*
				 * Match if the unicode value of current char.equals("4") Then
				 * continue the loop till next char or end
				 */
				if ((!unicodeCurrentChar.equals("4")) && count > -1) {

					// Left only single char
					// if (count != -1) {
					nextChar = textToParse.charAt(count--);
					updateCurrent = true;
					unicodeNextChar = ((int) nextChar) + "";

					/*
					 * Getting next char for the double char conversion Next
					 * char shouldn't be equivalent to 4 or Inpage Null char
					 */
					while (unicodeNextChar.equals("4") && count > -1) {
						nextChar = textToParse.charAt(count--);
						unicodeNextChar = ((int) nextChar) + "";
					}
					// }
					// Concatenating for the map double conversion
					
					mapSearch = unicodeNextChar + "-" + unicodeCurrentChar;
					/*
					 * Checking if the Double char mapping is their - Update the
					 * Current and next char to their mapped char if double
					 * conversion - if no mapping for double char go for single
					 * char - Updating the current based on the condition
					 * Breaking the conversion for the last char
					 */
					if (charConversionMap.containsKey(mapSearch)) {
						tempArray = charConversionMap.get(mapSearch).split("-");
						if (tempArray.length > 1)
							parsedText
									.append((char) Integer.parseInt(tempArray[1]));
						parsedText.append((char) Integer.parseInt(tempArray[0]));
						updateCurrent = true;
					} else {

						if (charConversionMap.containsKey(unicodeCurrentChar)) {
							currentChar = (char) Integer
									.parseInt(charConversionMap
											.get(unicodeCurrentChar));
						}
						parsedText.append(currentChar);

						if (count == -1 && (!unicodeNextChar.equals("4"))) {
							if (charConversionMap.containsKey(unicodeNextChar)) {
								nextChar = (char) Integer
										.parseInt(charConversionMap
												.get(unicodeNextChar));
							}

							parsedText.append(nextChar);
							break;
						}
						updateCurrent = false;
					}
				} else {
					updateCurrent = true;
				}
			}
		} else {
			/*
			 * if input length is 1
			 */
			unicodeCurrentChar = (int) textToParse.charAt(0) + "";
			if (charConversionMap.containsKey(unicodeCurrentChar)
					&& (!unicodeNextChar.equals("4"))) {
				currentChar = (char) Integer.parseInt(charConversionMap
						.get(unicodeCurrentChar));
			}
			parsedText.append(currentChar);
		}
		tempArray = null;
		// Returning the converted text
		return parsedText.reverse().toString();
	}

	//Initialize static dictionary 
	public static ConversionOnline getInstance() throws IOException {
		return ( inputParser != null ? inputParser : (new ConversionOnline()));
	}
}
