/**
 * 
 */
package com.huawei.ecm.cqn.test;

import java.io.IOException;

import com.huawei.ecm.cqn.trace.Profile;
import com.huawei.ecm.cqn.util.TestUtils;

/*
 * @文件描述：测试生成的字节码，并打印
 * @版权所有:(C)2009-2010
 * @完成日期: 2011-8-20  
 * @作者          ： Bohr.Qiu  
 */
public class Test {
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		TestUtils.printByteCodeAfterEnhenced(Target2.class);
//		TestUtils.printRefletInfo(Target2.class);
		Target.test("a");
		Target.test1("a");		
		System.out.println(Profile.state());
	}

}
