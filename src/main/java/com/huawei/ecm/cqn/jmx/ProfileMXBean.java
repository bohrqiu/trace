package com.huawei.ecm.cqn.jmx;

import java.util.Map;
import java.util.Set;

import com.huawei.ecm.cqn.trace.Profile.MethodEntity;

/*
 * @Description
 * 
 * @CreateDate 2011-8-24
 * 
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public interface ProfileMXBean {
	public String getState();

	public Map<String, MethodEntity> getStateMap();

	public MethodEntity getMethodEntity(String methodSignature);

	public Set<String> getAllMethod();

	public void reset();
}
