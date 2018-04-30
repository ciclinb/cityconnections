package com.routes.utils;

import org.slf4j.Logger;

import com.routes.bean.SysStatsBean;

/**
 * Build log messages for sys stats
 * @author lbo
 *
 */
public class SysStatsUtil {
	
    public static SysStatsBean getStartStatistics(String methodName) {
        SysStatsBean sysStatsBean = new SysStatsBean();
        sysStatsBean.setMethodCalled(methodName);

        Runtime.getRuntime().gc();
        long memory = Runtime.getRuntime().freeMemory();
        long time = System.currentTimeMillis();

        sysStatsBean.setMemoryUsed(memory);
        sysStatsBean.setTimeElapsed(time);

        return sysStatsBean;
    }

    public static void logStatistics(SysStatsBean sysStatsBean, Logger logger) {
        long memory = Runtime.getRuntime().freeMemory();
        long time = System.currentTimeMillis();

        if (sysStatsBean != null) {
            long intialMemory = sysStatsBean.getMemoryUsed();
            long intialTime = sysStatsBean.getTimeElapsed();

            long memoryUsed = intialMemory - memory;
            long timeElapsed = time - intialTime;

            sysStatsBean.setMemoryUsed(memoryUsed);
            sysStatsBean.setTimeElapsed(timeElapsed);
            
            if (logger != null) {
            	logger.info(sysStatsBean.getMethodCalled() + " Memory used (mb): " + memoryUsed/1024/1024 + " Time elapsed (ms): " + timeElapsed);
            } else {
//            	System.out.println(sysStatsBean.getMethodCalled() + " Memory used (mb): " + memoryUsed/1024/1024 + " Time elapsed (ms): " + timeElapsed);
            }
        }
    }

}
