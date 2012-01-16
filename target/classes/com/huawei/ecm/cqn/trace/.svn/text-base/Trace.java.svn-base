package com.huawei.ecm.cqn.trace;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @Description 定义类A 所有的方法都trace，使用@Trace(traceType=TraceType.AllMethod) 默认需要对类和方法都使用@Trace
 * @CreateDate 2011-8-23
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface Trace {
	enum ClassLevel {
		ALL /* all method which match traceMethodLevel will be enhanced */,
		SPECIFIED /* only method which specified the annotation will be enhanced */
	}

	enum MethodLevel {
		NoConstructor /* do not match constructor method */,
		NoStatic /* do not match static method */,
		Private /* match private method */,
		Protected /* match protected method */,
		Public /* match public method */,
		Default /* match default modifier */,
		All /* match all method */;
	}

	ClassLevel classLevel() default ClassLevel.SPECIFIED;

	/**
	 * 当TraceClassLevel为AllMethod时生效 默认处理非构造器，可见性为public的方法.
	 * 
	 * @return
	 */
	MethodLevel[] methodLevel() default { MethodLevel.Public,
			MethodLevel.NoConstructor };
}
