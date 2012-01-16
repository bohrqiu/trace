/**
 * 
 */
package com.huawei.ecm.cqn.tansformer;

import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.List;
import java.util.logging.Logger;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

import com.huawei.ecm.cqn.asm.AbstractClassAdapter;
import com.huawei.ecm.cqn.classloader.ReflectClassLoader;
import com.huawei.ecm.cqn.config.Config;

/**
 * 
 * @Description abstract class for annotationTransformer,subclass need provide
 * all method and constructor which will be enhanced
 * @CreateDate 2011-8-23
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public abstract class AnnotationTransformer implements ClassFileTransformer {
	/**
	 * 配置信息
	 */
	private Config config;
	/**
	 * 处理类实现的annotation
	 */
	private Class<? extends Annotation> annotation;
	/**
	 * classWriter
	 */
	private ClassWriter classWriter;
	/**
	 * 被处理类类名
	 */
	private String className;
	/**
	 * 被处理的类
	 */
	private Class<?> beHandledClass;

	public <T extends Annotation> AnnotationTransformer(Config config,
			Class<T> annotation) {

		super();
		this.config = config;
		this.annotation = annotation;
	}

	private static Logger logger = Logger
			.getLogger(TraceAnnotationTransformer.class.getName());

	public byte[] transform(ClassLoader loader, String className1,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			final byte[] classfileBuffer) throws IllegalClassFormatException {
		byte[] classBuffer=null;
		// enable package check
		className = className1.replace("/", ".");
		if (!config.matchPackage(className)) {
//			logger.finer(className + " cannot match package provided.");
			return null;
		}

		ReflectClassLoader mcl = null;
		try {
			// 使用自定义类加载器加载类，避免类被当前classloader加载。
			// 如果被当前类加载器加载，对类进行处理后在加载会失败
			mcl = AccessController.doPrivileged(new PrivilegedAction<ReflectClassLoader>() {
				public ReflectClassLoader run() {
					return new ReflectClassLoader(classfileBuffer);
				}
			});
			beHandledClass = mcl.findClass(className);
			logger.finer(className + " load by  TraceClassLoader.");

		} catch (ClassNotFoundException e) {
			logger.warning(mcl.getClass() + " load class error:" + e);
			return null;
		}

		if (beHandledClass.getAnnotation(annotation) != null) {

			ClassReader cr = null;
			// 直接使用字节数组,ClassReader 读取类，并会分发事件到ClassAdapter上面。
			cr = new ClassReader(classfileBuffer);
			classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

			ClassAdapter classAdapter = null;

			List<Method> methods = getMethods(beHandledClass);
			List<Constructor<?>> constructors = getConstructors(beHandledClass);

			if (methods.size() == 0 && constructors.size() == 0) {
				logger.finer(this.getClassName() + " haven't been enhenced.");
				return null;
			} else {
				classAdapter = getClassAdapter();
			}
			// ClassReader
			// 读取类信息，首先分发到CheckClassAdapter->TraceClassVisitor->ClassWriter
			// 是否启用调试信息
			TraceClassVisitor tcv = null;
			if (config.isPrintDebugInfo()) {
				tcv = new TraceClassVisitor(classAdapter, new PrintWriter(
						System.out));
			}
			// 是否启用类检查
			CheckClassAdapter cca = null;
			if (config.isCheckEnhandeClass()) {
				if (config.isPrintDebugInfo()) {
					cca = new CheckClassAdapter(tcv);
				} else {
					cca = new CheckClassAdapter(classAdapter);
				}
			}
			if (cca != null) {
				cr.accept(cca, ClassReader.EXPAND_FRAMES);
			} else if (tcv != null) {
				cr.accept(tcv, ClassReader.EXPAND_FRAMES);
			} else {
				cr.accept(classAdapter, ClassReader.EXPAND_FRAMES);
			}
			classBuffer = classWriter.toByteArray();

		} else {
			logger.finer(this.getClassName() + " haven't been enhenced.");
			return null;
		}
		return classBuffer;
	}

	abstract AbstractClassAdapter getClassAdapter();

	/**
	 * get All constructor need be enhanced
	 * 
	 * @param clazz
	 * @return
	 */
	abstract List<Constructor<?>> getConstructors(Class<?> clazz);

	/**
	 * @param oriClass
	 * @return
	 */
	abstract List<Method> getMethods(Class<?> oriClass);

	public Config getConfig() {
		return config;
	}

	public Class<? extends Annotation> getAnnotation() {
		return annotation;
	}

	public ClassWriter getClassWriter() {
		return this.classWriter;
	}

	public String getClassName() {
		return className;
	}

	public Class<?> getBeHandledClass() {
		return beHandledClass;
	}

	public void setClassWriter(ClassWriter classWriter) {
		this.classWriter = classWriter;
	}

}