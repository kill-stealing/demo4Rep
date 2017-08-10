package com.multiplethread;

/**
 * Super interface for all scheduler jobs.
 * All implemented classes must do the following: <br/>
 * &nbsp 1) Firstly, to check job availability by <code>checkJobActive()</code><br/>
 * &nbsp 2) Secondly, to do job operation by <code>doJob()</code>
 * 
 * @author Tiger
 *
 */
public interface SchedulerJob {

	/**
	 * Check whether a job is active or not before running a scheduler job
	 * 
	 * @return true if the job is active, otherwise false.
	 * @throws Exception
	 */
	boolean checkJobActive() throws Exception;
	
	/**
	 * To do detail job
	 * 
	 * @throws Exception
	 */
	void doJob() throws Exception;
	
}
