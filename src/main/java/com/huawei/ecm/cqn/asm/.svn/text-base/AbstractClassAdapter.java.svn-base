/**
 * 
 */
package com.huawei.ecm.cqn.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
/**
 * @Description process the class will be enhanced, 
 * @CreateDate 2011-8-22
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public abstract class AbstractClassAdapter extends ClassAdapter {

	/**
	 * construct name in bytecode
	 */
	private static final String CONSTRUCT_NAME = "<init>";
	private static Logger logger = Logger
			.getLogger(AbstractClassAdapter.class.getName());

	/**
	 * method can be proccess
	 */
	private List<Method> methods = null;
	List<Constructor<?>> constructors = null;
	private String className;


	public AbstractClassAdapter(ClassVisitor arg0, List<Method> methods,
			List<Constructor<?>> constructors, String className) {
		super(arg0);
		this.methods = methods;
		this.className = className;
		this.constructors = constructors;
	}

	public MethodVisitor visitMethod(final int access, final String name,
			final String desc, final String signature, final String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);
		MethodVisitor wrappedMv = mv;
		if (mv != null) {
			if ((isMethod(name) && checkMethod(this.methods, name,
							access, desc)) /* if method be visited,check method */
					|| (!isMethod(name) && checkConstructor(this.constructors,
							name, access, desc))/* if constructor be vistied,check constructor*/) {
				logger.fine(this.className + "#" + name + " be enhanced");
				wrappedMv = getMethodAdapter(mv);
			}
		}
		return wrappedMv;
	}
	abstract MethodVisitor getMethodAdapter(MethodVisitor mv); 
	/**
	 * 
	 * @param methodName
	 * @return false 代表constructor
	 */
	public boolean isMethod(String methodName) {
		if (CONSTRUCT_NAME.equals(methodName)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkMethod(List<Method> methods, String name, int access,
			String desc) {
		for (Method m : methods) {
			// 访问类型相等
			if (m.getModifiers() == access) {
				// 方法名相等
				if (m.getName().equals(name)) {
					// 方法描述相等
					if (Type.getMethodDescriptor(m).equals(desc)) {
						return true;
					}

				}
			}
		}
		return false;
	}

	public boolean checkConstructor(List<Constructor<?>> constructors,
			String name, int access, String desc) {
		for (Constructor<?> m : constructors) {
			// 访问类型相等
			if (m.getModifiers() == access) {
				// 方法描述相等
				if (Type.getConstructorDescriptor(m).equals(desc)) {
					return true;
				}

			}
		}
		return false;
	}
}
