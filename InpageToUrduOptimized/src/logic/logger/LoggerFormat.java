package logic.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerFormat {
	
	private static FileHandler logHandler ;
	private final static Logger logger = Logger.getLogger(LoggerFormat.class.getName());
	/*
	 *associating the logger FileHandler
	 *specify the logger formatter
	 */
	
	public static void loggerFormat(){
		try {
			logHandler = new FileHandler("ustan-log.%u.%g.log", true);
		} catch (SecurityException e2) {
			logger.log(Level.SEVERE, e2.getMessage());
		} catch (IOException e2) {
			logger.log(Level.SEVERE, e2.getMessage());
		}
		logger.addHandler(logHandler);
		logHandler.setFormatter(new SimpleFormatter());
	}

	public static final Logger getLogger(){
		return ( logger != null ? logger : LoggerFormat.logger);
	}
	
}
