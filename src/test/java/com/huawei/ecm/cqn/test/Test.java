/**
 * 
 */
package com.huawei.ecm.cqn.test;

import java.io.IOException;

import com.huawei.ecm.cqn.trace.Profile;
import com.huawei.ecm.cqn.util.TestUtils;

/*
 * @�ļ��������������ɵ��ֽ��룬����ӡ
 * @��Ȩ����:(C)2009-2010
 * @�������: 2011-8-20  
 * @����          �� Bohr.Qiu  
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
