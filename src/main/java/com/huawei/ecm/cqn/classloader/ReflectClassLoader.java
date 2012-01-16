/**
 * 
 */
package com.huawei.ecm.cqn.classloader;
/**
 * @Description class loader for load class then get class metadata by
 * reflection
 * @CreateDate 2011-8-18
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class ReflectClassLoader extends ClassLoader {
	private byte[] classfileBuffer;

	public ReflectClassLoader(byte[] classfileBuffer) {
		super();
		this.classfileBuffer = classfileBuffer;

	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		return super.defineClass(name, classfileBuffer, 0,
				classfileBuffer.length);
	}

}
