/**
 * 
 */
package com.huawei.ecm.cqn.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
/**
 * 
 * @Description MethodApapter which process method need be enhenced by Profile
 * @CreateDate 2011-8-21
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class TraceMethodAdapter extends MethodAdapter {
	private boolean isFirstVisted = false;

	public TraceMethodAdapter(MethodVisitor visitor) {
		super(visitor);
	}
	/**
	 * keep the source code line number and insert Profile.begin() method
	 */
	@Override
	public void visitLineNumber(int line, Label start) {
		if(isFirstVisted){
			//visit line Numer at first time ,then insert line number then insert code
			super.visitLineNumber(line, start);
			super.visitMethodInsn(Opcodes.INVOKESTATIC,
					"com/huawei/ecm/cqn/trace/Profile", "begin", "()V");
			isFirstVisted=false;
			return ;
		}
		super.visitLineNumber(line, start);
	}

	public void visitCode() {
		isFirstVisted = true;
		super.visitCode();
	}
	/**
	 * Profile.end() will be execute at any time
	 */
	public void visitInsn(int inst) {
		switch (inst) {
		case Opcodes.ARETURN:
		case Opcodes.DRETURN:
		case Opcodes.FRETURN:
		case Opcodes.IRETURN:
		case Opcodes.LRETURN:
		case Opcodes.RETURN:
		case Opcodes.ATHROW:
			this.visitMethodInsn(Opcodes.INVOKESTATIC,
					"com/huawei/ecm/cqn/trace/Profile", "end", "()V");
			break;
		default:
			break;
		}
		super.visitInsn(inst);
	}



}
