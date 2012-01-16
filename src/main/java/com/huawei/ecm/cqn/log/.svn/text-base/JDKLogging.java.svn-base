package com.huawei.ecm.cqn.log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

import com.huawei.ecm.cqn.config.Config;

/**
 * jdk logging utils
 * 
 * @Description
 * @CreateDate 2011-8-17
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class JDKLogging {

	private static String getFormattedTime(long lngMillTimes,
			String strTimeFormat) {
		Date lvDate = new Date();
		lvDate.setTime(lngMillTimes);
		SimpleDateFormat lvFormat = new SimpleDateFormat(strTimeFormat,
				Locale.getDefault());
		return lvFormat.format(lvDate);
	}

	public static void initLog(Config config) {
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler(config.getLogFile(),
					config.isLogFileModeAppend());
			fileHandler.setLevel(config.getLogLevel());
			fileHandler.setFormatter(new Formatter() {
				@Override
				public String format(LogRecord record) {
					StringBuilder sb = new StringBuilder();
					sb.append(getFormattedTime(record.getMillis(),
							"yyyy-MM-dd HH:mm:ss,SS"));
					sb.append(" ");
					sb.append(record.getLevel());
					sb.append(" [");
					sb.append(record.getSourceClassName());
					sb.append("#");
					sb.append(record.getSourceMethodName());
					sb.append("] ");
					sb.append(record.getMessage());
					sb.append("\n");
					return sb.toString();
				}
			});
			// ¸Ä±ä×î¶¥²ãlogger
			LogManager.getLogManager().getLogger("").addHandler(fileHandler);
			LogManager.getLogManager().getLogger("")
					.setLevel(config.getLogLevel());
			if (config.isEnableConsoleHandler()) {
				ConsoleHandler ch = new ConsoleHandler();
				ch.setLevel(config.getLogLevel());
				LogManager.getLogManager().getLogger("").addHandler(ch);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
