package com.huawei.ecm.cqn.trace;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.huawei.ecm.cqn.config.Config;
import com.huawei.ecm.cqn.jmx.ProfileJMXAgent;
import com.huawei.ecm.cqn.log.JDKLogging;
import com.huawei.ecm.cqn.tansformer.TraceAnnotationTransformer;
import com.huawei.ecm.cqn.util.Utils;

/**
 * @Description use javaagent to enhance class which match some pattern for
 * trace class
 * @CreateDate 2011-8-16
 * @Author <a href="mailto:bohr.qiu@gmail.com">Bohr.Qiu</a>
 */
public class TraceAgent {
	// java.runtime.version=1.6.0_24-b07
	private static Logger logger = Logger.getLogger(TraceAgent.class.getName());

	public static void premain(String args, Instrumentation inst) {
		final Config config = Utils.parseFile(args);
		// 初始化日志
		JDKLogging.initLog(config);
		logger.fine("transformer start successful,begin transform...");

		inst.addTransformer(new TraceAnnotationTransformer(config), true);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				logger.info("before jvm shutdown:\n" + Profile.state());
			}
		}));
		logger.fine("shutdown hook be added to record statistics when jvm shut down.");
		// JMX start at a delay time.
		ScheduledExecutorService scheduleExec = Executors
				.newScheduledThreadPool(1);
		scheduleExec.schedule(new Runnable() {

			@Override
			public void run() {
				ProfileJMXAgent pa = new ProfileJMXAgent(config
						.getJmxListenPort());
				pa.start();
				logger.info("JMX listening port:" + config.getJmxListenPort());
			}
		}, config.getJmxDelayStartTime(), TimeUnit.SECONDS);
		scheduleExec.shutdown();

	}
}
