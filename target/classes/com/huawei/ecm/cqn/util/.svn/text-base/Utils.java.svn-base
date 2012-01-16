package com.huawei.ecm.cqn.util;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.huawei.ecm.cqn.config.Config;

/*
 * @Description utils for parse Config form file
 * 
 * @CreateDate 2011-8-26
 * 
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class.getName());

	public static Config parseFile(String fileName) {
		Config config = new Config();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File(fileName));
			Node node = doc.getFirstChild();
			NodeList list = node.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node nodeitm = list.item(i);
				String nodeName = nodeitm.getNodeName();
				if (nodeitm.getFirstChild() == null) {
					continue;
				}
				String nodeValue = nodeitm.getFirstChild().getNodeValue();
				if (nodeName.equals("scanPackage")) {
					config.setPackageList(nodeValue);
				} else if (nodeName.equals("jmxListenPort")) {
					config.setJmxListenPort(Integer.parseInt(nodeValue));
				} else if (nodeName.equals("jmxStartTimeDelay")) {
					config.setJmxDelayStartTime(Integer.parseInt(nodeValue));
				} else if (nodeName.equals("logLevel")) {
					Level level = null;
					try {
						level = Level.parse(nodeValue.toUpperCase());
					} catch (Exception e) {
					}
					if (level != null) {
						config.setLogLevel(level);
					}
				} else if (nodeName.equals("logFile")) {
					boolean exist = false;
					try {
						if (new File(nodeValue).exists()) {
							exist = true;
						}
					} catch (Exception e) {
						logger.warning("logFile:" + nodeValue
								+ " don't exists,logFile be set default value:"
								+ config.getLogFile());
					}
					if (exist) {
						config.setLogFile(nodeValue);
					}

				}

			}
		} catch (Exception e) {
			logger.warning("parse config file error,use default config");
		}
		return config;
	}

	public static void main(String[] args) {
		System.out
				.println(Utils
						.parseFile("D:\\coder\\java_workspace\\trace\\resources\\config.xml"));
	}
}
