package com.huawei.ecm.cqn.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * @Description
 * @CreateDate 2011-8-18
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class Config {
	/**
	 * 对哪些包进行处理
	 */
	private List<String> packageList = null;
	/**
	 * after class been enhanced ,check class is according to java bytecode
	 * specification
	 */
	private boolean checkEnhandeClass = false;
	/**
	 * use for debug enhanced bytecode
	 */
	private boolean printDebugInfo = false;

	private int jmxStartTimeDelay = 5;

	private int jmxListenPort = 10000;

	private Level logLevel = Level.INFO;

	/**
	 * enable consolehandler which show logs in console
	 */
	private boolean isEnableConsoleHandler = false;
	private String logFile=getDefaulLogFile();
	/**
	 * when logfile exist,append log to logfile if logFileModeAppend=true
	 */
	private boolean logFileModeAppend = true;

	/**
	 * 默认匹配com.huawei
	 */
	public Config() {
		packageList = new ArrayList<String>();
		packageList.add("com.huawei");
		packageList.add("test");
	}

	public Config(List<String> packageList) {
		this.packageList = packageList;
	}

	public List<String> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<String> packageList) {
		this.packageList = packageList;
	}

	public void setPackageList(String packages) {
		this.packageList = Arrays.asList(packages.split(","));
	}

	public void addPackage(String pkg) {
		this.packageList.add(pkg);
	}

	public boolean matchPackage(String className) {
		for (String pkg : packageList) {
			if (className.startsWith(pkg)) {
				return true;
			}
		}
		return false;
	}

	public boolean isCheckEnhandeClass() {
		return checkEnhandeClass;
	}

	public void setCheckEnhandeClass(boolean checkEnhandeClass) {
		this.checkEnhandeClass = checkEnhandeClass;
	}

	public boolean isPrintDebugInfo() {
		return printDebugInfo;
	}

	public void setPrintDebugInfo(boolean printDebugInfo) {
		this.printDebugInfo = printDebugInfo;
	}

	public int getJmxDelayStartTime() {
		return jmxStartTimeDelay;
	}

	public void setJmxDelayStartTime(int jmxDelayStartTime) {
		this.jmxStartTimeDelay = jmxDelayStartTime;
	}

	public int getJmxListenPort() {
		return jmxListenPort;
	}

	public void setJmxListenPort(int jmxListenPort) {
		this.jmxListenPort = jmxListenPort;
	}

	public boolean isEnableConsoleHandler() {
		return isEnableConsoleHandler;
	}

	public void setEnableConsoleHandler(boolean isEnableConsoleHandler) {
		this.isEnableConsoleHandler = isEnableConsoleHandler;
	}

	private boolean isWindows() {
		if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) {
			return true;
		} else {
			return false;
		}
	}

	private String getDefaulLogFile() {
		if (isWindows()) {
			return "C:/trace.log";
		} else {
			return "~/trace.log";
		}
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public Level getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(Level logLevel) {
		this.logLevel = logLevel;
	}

	public boolean isLogFileModeAppend() {
		return logFileModeAppend;
	}

	public void setLogFileModeAppend(boolean logFileModeAppend) {
		this.logFileModeAppend = logFileModeAppend;
	}

	@Override
	public String toString() {
		return "Config [packageList=" + packageList + ", checkEnhandeClass="
				+ checkEnhandeClass + ", printDebugInfo=" + printDebugInfo
				+ ", jmxStartTimeDelay=" + jmxStartTimeDelay
				+ ", jmxListenPort=" + jmxListenPort + ", logLevel=" + logLevel
				+ ", isEnableConsoleHandler=" + isEnableConsoleHandler
				+ ", logFile=" + logFile + ", logFileModeAppend="
				+ logFileModeAppend + "]";
	}

}