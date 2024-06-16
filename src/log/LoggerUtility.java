package log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Generate Log4j logger.
 * 
 *  Like text or html file.
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA 
 */
public class LoggerUtility {
	private static final String TEXT_LOGS = "src/log/log4j-text.properties";
	private static final String HTML_LOGS = "src/log/log4j-html.properties";

	public static Logger getLogger(Class<?> logClass, String logFileType) {
		if (logFileType.equals("text")) {
			PropertyConfigurator.configure(TEXT_LOGS);
		} else if (logFileType.equals("html")) {
			PropertyConfigurator.configure(HTML_LOGS);
		} else {
			throw new IllegalArgumentException(" log file type non reconnu !");
		}

		String className = logClass.getName();
		return Logger.getLogger(className);
	}
}
