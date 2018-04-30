package com.routes.bean;

/**
 * Collect sys stats
 * @author lbo
 *
 */
public class SysStatsBean {

	 private long timeElapsed;
	    private long memoryUsed;
	    private String methodCalled;

	    public SysStatsBean() {
	    }

	    public SysStatsBean(String methodCalled) {
	        this.methodCalled = methodCalled;
	    }

	    public long getTimeElapsed() {
	        return timeElapsed;
	    }

	    public void setTimeElapsed(long timeElapsed) {
	        this.timeElapsed = timeElapsed;
	    }

	    public long getMemoryUsed() {
	        return memoryUsed;
	    }

	    public void setMemoryUsed(long memoryUsed) {
	        this.memoryUsed = memoryUsed;
	    }

	    public String getMethodCalled() {
	        return methodCalled;
	    }

	    public void setMethodCalled(String methodCalled) {
	        this.methodCalled = methodCalled;
	    }

	    /**
	     * This method overrides Object's equals method. SysStatsBeans are the same they are both sys stat beans. This is used for Junit testing
	     * and mocking.
	     * 
	     * @param obj
	     *            object to compare to
	     * @return boolean. True if they are equal; false if they are not.
	     */
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj instanceof SysStatsBean) {
	            return true;
	        }
	        return false;
	    }

}
