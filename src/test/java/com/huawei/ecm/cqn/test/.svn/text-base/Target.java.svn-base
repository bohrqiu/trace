/**
 * 
 */
package com.huawei.ecm.cqn.test;

import java.util.logging.Logger;

import com.huawei.ecm.cqn.trace.Profile;
import com.huawei.ecm.cqn.trace.Trace;

/*
 * @文件描述：
 * @版权所有:(C)2009-2010
 * @完成日期: 2011-8-18  
 * @作者          ： Bohr.Qiu  
 */
@Trace
public class Target {
	private static Logger logger = Logger.getLogger(Target.class.getName());

	@Trace
	public static void test(String i) {
		logger.info("target#test");
		Profile.begin();
		Profile.end();
	}

	public Target() {

	}

	public Target(String str) {

	}

	static void test1(String i) {
		logger.info("target#test");
		Profile.begin();
		Profile.end();
	}
}
