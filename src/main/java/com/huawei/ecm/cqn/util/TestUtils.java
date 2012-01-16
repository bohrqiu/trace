/**
 * 
 */
package com.huawei.ecm.cqn.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.objectweb.asm.Type;
/**
 * @Description utils for test.
 * @CreateDate 2011-8-23
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class TestUtils {
	public static void printByteCodeAfterEnhenced(Class<?> clazz)
			throws IOException, ClassNotFoundException, InstantiationException,
			IllegalAccessException {
//		ClassReader cr = new ClassReader(clazz.getName());
//		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//		TraceClassVisitor tcv = new TraceClassVisitor(cw, new PrintWriter(
//				System.out));
//		TraceAnnotationTransformer traceTransformer = new TraceAnnotationTransformer(
//				new Config());
//		ClassAdapter classAdapter = new AbstractClassAdapter(tcv,
//				traceTransformer.getMethods(clazz),
//				traceTransformer.getConstructors(clazz), clazz.getName());
//		// 修改后的代码检查是否符合虚拟机要求，注意顺序，相当于拦截器
//		CheckClassAdapter cca = new CheckClassAdapter(classAdapter);
//
//		cr.accept(cca, ClassReader.EXPAND_FRAMES);
//		byte[] tmp = cw.toByteArray();
//		ReflectClassLoader mcl = new ReflectClassLoader(tmp);
//		FileOutputStream fos = new FileOutputStream("c:\\test_tmp.class");
//		fos.write(tmp);
//		System.out.println(mcl.getParent());
//		@SuppressWarnings("unused")
//		Class<?> c = mcl.findClass(clazz.getName());
		// 很悲剧的是，由于是不同的类加载器，不能使用当前类加载器去执行。使用TraceClassVisitor会打印结果，能看到代码已经加入进去了。
	}

	public static void printRefletInfo(Class<?> clazz) {
		for (Method m : clazz.getDeclaredMethods()) {
			System.out.println("getDescription:" + Type.getMethodDescriptor(m));
			System.out.println("getName:" + m.getName());
			System.out.println("getModifiers:" + m.getModifiers());
			System.out.println("isStatic:"
					+ Modifier.isStatic(m.getModifiers()));
			System.out.println("getExceptionTypes:"
					+ Arrays.toString(m.getExceptionTypes()));
			System.out.println(m.toGenericString());
			System.out.println();

		}
		for (Constructor<?> m : clazz.getConstructors()) {
			System.out.println("getDescription:"
					+ Type.getConstructorDescriptor(m));
			System.out.println("getName:" + m.getName());

			System.out.println("getModifiers:" + m.getModifiers());
			System.out.println("isStatic:"
					+ Modifier.isStatic(m.getModifiers()));
			System.out.println("getExceptionTypes:"
					+ Arrays.toString(m.getExceptionTypes()));
			System.out.println(m.toGenericString());
			System.out.println();

		}

	}
}
