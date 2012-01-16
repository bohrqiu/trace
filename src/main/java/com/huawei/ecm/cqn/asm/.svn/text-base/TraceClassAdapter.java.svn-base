package com.huawei.ecm.cqn.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
/**
 * 
 * @Description   
 * @CreateDate 2011-8-21
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class TraceClassAdapter extends AbstractClassAdapter {
	private static Logger logger = Logger.getLogger(TraceClassAdapter.class
			.getName());
	private String className;

	public TraceClassAdapter(ClassVisitor arg0, List<Method> methods,
			List<Constructor<?>> constructors, String className) {
		super(arg0, methods, constructors, className);
		this.className = className;
	}

	/**
	 * TraceClassAdapter use TraceMethodAdapter to handle method level bytecode
	 * enhence
	 */
	@Override
	MethodVisitor getMethodAdapter(MethodVisitor mv) {
		logger.fine(className
				+ "'s MethodAdapter have be setted to TraceMethodAdapter");
		return new TraceMethodAdapter(mv);
	}

}
