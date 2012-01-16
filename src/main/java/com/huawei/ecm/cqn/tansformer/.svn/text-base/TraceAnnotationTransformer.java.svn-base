package com.huawei.ecm.cqn.tansformer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.huawei.ecm.cqn.asm.AbstractClassAdapter;
import com.huawei.ecm.cqn.asm.TraceClassAdapter;
import com.huawei.ecm.cqn.config.Config;
import com.huawei.ecm.cqn.trace.Trace;
import com.huawei.ecm.cqn.trace.Trace.ClassLevel;
import com.huawei.ecm.cqn.trace.Trace.MethodLevel;

/**
 * @Description process trace annotation 
 * @CreateDate 2011-8-23
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class TraceAnnotationTransformer extends AnnotationTransformer {

	private static Logger logger = Logger
			.getLogger(TraceAnnotationTransformer.class.getName());

	public <T extends Annotation> TraceAnnotationTransformer(Config config) {
		super(config, Trace.class);
	}

	/**
	 * 获取处理Trace annotation的classAdapter
	 */
	public AbstractClassAdapter getClassAdapter() {
		Class<?> clazz = this.getBeHandledClass();
		return new TraceClassAdapter(this.getClassWriter(),
				this.getMethods(clazz), this.getConstructors(clazz),
				this.getClassName());
	}
	
	/**
	 * get all method which according to @Trace need be enhanced
	 * 
	 * @param clazz
	 * @return
	 */
	public List<Method> getMethods(Class<?> clazz) {
		List<Method> ms = new ArrayList<Method>();
		Method[] methods = clazz.getDeclaredMethods();

		Trace trace = (Trace) clazz.getAnnotation(Trace.class);
		ClassLevel classLevel = trace.classLevel();
		if (classLevel.equals(ClassLevel.ALL)) {
			MethodLevel[] levels = trace.methodLevel();

			for (Method m : methods) {

				if (contain(levels, MethodLevel.All)) {
					// trace all method
					ms.add(m);

				} else {
					if (matchTraceMethodLevel(m.getModifiers(), levels)) {
						ms.add(m);
					}
				}
			}
		} else {
			// for SingleMethod
			for (Method m : methods) {
				if (m.getAnnotation(Trace.class) != null) {
					ms.add(m);
				}
			}
		}
		logger.finer("Method need be enhaned:" + ms);
		return ms;
	}

	/**
	 * get All constructor which according to @Trace need be enhanced
	 * 
	 * @param clazz
	 * @return
	 */
	public List<Constructor<?>> getConstructors(Class<?> clazz) {
		List<Constructor<?>> ms = new ArrayList<Constructor<?>>();
		Trace trace = clazz.getAnnotation(Trace.class);
		ClassLevel classLevel = trace.classLevel();
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();

		if (classLevel.equals(ClassLevel.ALL)) {
			MethodLevel[] levels = trace.methodLevel();
			if (contain(levels, MethodLevel.NoConstructor)) {
				return ms;
			}
			for (Constructor<?> c : constructors) {
				if (contain(levels, MethodLevel.All)) {
					ms.add(c);
				} else {
					if (matchTraceMethodLevel(c.getModifiers(), levels)) {
						ms.add(c);
					}
				}
			}

		} else {
			// for SingleMethod
			for (Constructor<?> c : constructors) {
				if (c.getAnnotation(Trace.class) != null) {
					ms.add(c);
				}
			}
		}
		logger.finer("Constructor need be enhaned:" + ms);
		return ms;
	}

	private boolean contain(MethodLevel[] levels, MethodLevel level) {
		for (MethodLevel traceMethodLevel : levels) {
			if (traceMethodLevel.equals(level)) {
				return true;
			}
		}
		return false;
	}

	private boolean matchTraceMethodLevel(int modifier, MethodLevel[] levels) {
		// no static
		if (contain(levels, MethodLevel.NoStatic)
				&& Modifier.isStatic(modifier)) {
			return false;
		}
		if (contain(levels, MethodLevel.Public)) {
			return Modifier.isPublic(modifier);
		}
		if (contain(levels, MethodLevel.Protected)) {
			return Modifier.isProtected(modifier);
		}
		if (contain(levels, MethodLevel.Private)) {
			return Modifier.isPrivate(modifier);
		}
		// jdk没有对默认可见性定义
		if (contain(levels, MethodLevel.Default)) {
			return !Modifier.isPublic(modifier)
					&& !Modifier.isProtected(modifier)
					&& !Modifier.isPrivate(modifier);
		}
		// NoConstructor
		return true;
	}
}
