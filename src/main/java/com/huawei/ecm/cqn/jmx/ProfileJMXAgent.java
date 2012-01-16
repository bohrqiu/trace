package com.huawei.ecm.cqn.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import com.huawei.ecm.cqn.trace.Profile;
import com.sun.jdmk.comm.HtmlAdaptorServer;

/*
 * @Description
 * 
 * @CreateDate 2011-8-24
 * 
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class ProfileJMXAgent {
	private MBeanServer mbs = null;
	private HtmlAdaptorServer htmlAdaptor = new HtmlAdaptorServer();

	public ProfileJMXAgent(int port) {
		mbs = MBeanServerFactory.createMBeanServer("ProfileAgent");

		HtmlAdaptorServer htmlAdaptor = new HtmlAdaptorServer();

		Profile profileMbean = new Profile();
		ObjectName adapterName = null;
		ObjectName profileMbeanName = null;

		try {

			profileMbeanName = new ObjectName("ProfileAgent:name=profile");
			mbs.registerMBean(profileMbean, profileMbeanName);
			adapterName = new ObjectName(
					"ProfileAgent:name=htmladapter,port="+port);
			mbs.registerMBean(htmlAdaptor, adapterName);
			htmlAdaptor.setPort(port);
			htmlAdaptor.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void start() {
		htmlAdaptor.start();
	}

//	public static void main(String args[]) {
//		System.out.println("HelloAgent is running");
//		ProfileAgent agent = new ProfileAgent(10001);
//
//	}
}
