/**
 * 
 */
package com.huawei.ecm.cqn.trace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.huawei.ecm.cqn.jmx.ProfileMXBean;

/**
 * @Description record method's execute time, execute times.
 * @CreateDate 2011-8-21
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class Profile implements ProfileMXBean{
	private static Logger logger = Logger.getLogger(Profile.class.getName());
	// �洢����ǩ��������������Դ�����е�����
	private static ThreadLocal<String> methodName = new ThreadLocal<String>();
	// ����ִ��ʱ��
	private static Map<String, MethodEntity> map = new ConcurrentHashMap<String, MethodEntity>();

	public static class MethodEntity implements Comparable<MethodEntity> {
		// ִ��ʱ��
		private long timeCost;
		// ִ�д���
		private long times;

		public double getAvg() {
			return timeCost * 1.0 / times;
		}

		MethodEntity(long timeCost, long times) {
			this.timeCost = timeCost;
			this.times = times;
		}

		public long getTimeCost() {
			return timeCost;
		}

		public void setTimeCost(long timeCost) {
			this.timeCost = timeCost;
		}

		public long getTimes() {
			return times;
		}

		public void setTimes(long times) {
			this.times = times;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(MethodEntity o) {
			return (int) (this.getAvg() - o.getAvg());
		}

		@Override
		protected MethodEntity clone() throws CloneNotSupportedException {
			return new MethodEntity(this.timeCost, this.times);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (timeCost ^ (timeCost >>> 32));
			result = prime * result + (int) (times ^ (times >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MethodEntity other = (MethodEntity) obj;
			if (timeCost != other.timeCost)
				return false;
			if (times != other.times)
				return false;
			return true;
		}

	}

	/**
	 * ��¼ִ��ʱ���ִ�д���,can be invoke by client.
	 */
	public static void begin() {
		String methodSign = getMethodSignature();
		methodName.set(methodSign);
		if (map.containsKey(methodSign)) {
			MethodEntity c = map.get(methodSign);
			c.setTimes(c.getTimes() + 1);
			c.setTimeCost(c.getTimeCost() - System.currentTimeMillis());
		} else {
			map.put(methodSign, new MethodEntity(-System.currentTimeMillis(),
					1l));
		}

	}

	/*
	 * ����ִ�����ʱ���ã�Ҫ��֤�˷����ڷ���ִ�����ʱ���뱻���õ���
	 * can be invoke by client
	 */
	public static void end() {
		String methodSign = methodName.get();
		if (methodSign == null || !map.containsKey(methodSign)) {
			logger.warning("Internal method can be execute by client");
			throw new RuntimeException(
					"Internal method can be execute by client");
		}
		MethodEntity me = map.get(methodSign);
		me.setTimeCost(me.getTimeCost() + System.currentTimeMillis());
	}

	/**
	 * ���ͳ������
	 */
	public static String state() {
		StringBuilder sb = new StringBuilder();

		// ����ƽ������ʱ������
		List<Entry<String, MethodEntity>> list = new ArrayList<Entry<String, MethodEntity>>(
				map.entrySet());

		Collections.sort(list, new Comparator<Entry<String, MethodEntity>>() {
			public int compare(Entry<String, MethodEntity> o1,
					Entry<String, MethodEntity> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}

		});
		for (Entry<String, MethodEntity> entry : list) {
			MethodEntity me = entry.getValue();
			sb.append("Method:");
			sb.append(entry.getKey());
			sb.append("\t");

			sb.append("TakeTime:");
			sb.append(me.getTimeCost());
			sb.append("ms\t");

			sb.append("ExecuteTimes:");
			sb.append(me.getTimes());
			sb.append("\t");

			sb.append("AvgTakeTime:");
			sb.append(me.getTimeCost() * 1.0 / me.getTimes());
			sb.append("ms\n");
		}
		logger.warning("statistic:\n" + sb.toString());
		return sb.toString();
	}

	/**
	 * ���ͳ������
	 */
	public static void clear() {
		logger.info("statistic be reseted.");
		map.clear();
	}

	/**
	 * get method signature include source code line number
	 * 
	 * @return
	 */
	private static String getMethodSignature() {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
		String className = ste.getClassName();
		String methodName = ste.getMethodName();
		int line = ste.getLineNumber() - 1;
		return className + "#" + methodName + ":" + line;
	}

	/* (non-Javadoc)
	 * @see com.huawei.ecm.cqn.jmx.ProfileMBean#getState()
	 */
	@Override
	public String getState() {
		return Profile.state();
	}

	/* (non-Javadoc)
	 * @see com.huawei.ecm.cqn.jmx.ProfileMBean#getStateMap()
	 */
	@Override
	public Map<String, MethodEntity> getStateMap() {
		return map;
	}

	/* (non-Javadoc)
	 * @see com.huawei.ecm.cqn.jmx.ProfileMBean#getMethodEntity(java.lang.String)
	 */
	@Override
	public MethodEntity getMethodEntity(String methodSignature) {
		return map.get(methodSignature);
	}

	/* (non-Javadoc)
	 * @see com.huawei.ecm.cqn.jmx.ProfileMBean#getAllMethod()
	 */
	@Override
	public Set<String> getAllMethod() {
		return map.keySet();
	}

	@Override
	public void reset() {
		map.clear();
	}
	
}
