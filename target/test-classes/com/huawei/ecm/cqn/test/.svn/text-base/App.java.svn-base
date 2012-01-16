package com.huawei.ecm.cqn.test;

import java.util.logging.Level;

import com.huawei.ecm.cqn.config.Config;
import com.huawei.ecm.cqn.log.JDKLogging;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		final Config config = new Config();
		config.setCheckEnhandeClass(false);
		config.setPrintDebugInfo(false);
		config.setLogFile("C:/testlog%g.log");
		config.setLogLevel(Level.WARNING);
		config.setEnableConsoleHandler(false);
		JDKLogging.initLog(config);
		System.out.println(getMethodSignature());
	}

	public static String getMethodSignature() {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		String className = ste.getClassName();
		String methodName = ste.getMethodName();
		int lineNum = ste.getLineNumber() - 1;
		return className + "#" + methodName + "[" + lineNum + "]";
	}
}
