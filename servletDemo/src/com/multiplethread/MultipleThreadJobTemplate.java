package com.multiplethread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * This is a abstract implementing class 
 * from super interface for all the scheduler jobs.
 * 
 * <p><code><i>MultipleThreadJobTemplate</i></code> is a template for all the jobs
 * in which multiple threads needed in order to complete the operation.
 * 
 * <p><code><i>MultipleThreadJobTemplate</i></code> will take the responsibility to
 * maintain threads related process, including following: <br>
 * &nbsp to construct a thread pool,<br>
 * &nbsp to submit the tasks which should be provided by detailed implementing job class,<br>
 * &nbsp to get the tasks result,<br>
 * &nbsp to shutdown the thread pool after job processing<br>
 * 
 * <p>All class implementing this template 
 * must check job availability by overriding <code>checkJobActive()</code>,
 * provide the tasks to this template to be processed by overriding <code>prepareTask()</code>.
 * 
 * @author Tiger
 * @see com.ibm.gs.isa.scheduler.SchedulerJob
 * 
 */
public abstract class MultipleThreadJobTemplate<T> implements SchedulerJob {

	/**
	 * A thread pool to provide threads to do the job,
	 * it will be a fixed size thread pool in this template,
	 * the size will be decided by <i>threadsNum</i>
	 * 
	 * @see #threadsNum
	 */
	private ExecutorService threadPool;
	
	/**
	 * Thread pool size, by default will be the value of <code>ISAConstant.JOB_MAX_THREAD_COUNT</code>,
	 * implementing class can set this value differently
	 */
	private int threadsNum = 10;
	
	/**
	 * A list of tasks to be processed,
	 * this list should be provided by implementing class.
	 */
	private List<? extends Callable<T>> taskList;
	
	/**
	 * A list of submitted task,
	 * template will fetch task result from this list,
	 * then add the result into a final result list.
	 * 
	 * @see #resultList
	 */
	private List<Future<T>> submittedTaskList;
	
	/**
	 * This list will contain results after job processing,
	 * implementing class can get this list by simple getter method
	 */
	private List<T> resultList;
		
	/**
	 * Should be override by implementing class
	 */
	public abstract boolean checkJobActive() throws Exception;
	
	/**
	 * A simple implemented method to do detail job process,
	 * implementing class can override this method to add additional logic.
	 */
	@Override
	public void doJob() throws Exception {
		prepareJob();
		doMultipleThreadJob();
	}

	/**
	 * To prepare for the job
	 * 
	 * @throws Exception
	 */
	public void prepareJob() throws Exception {
		prepareTask();
	}
	
	/**
	 * Should be override by implementing class to provide tasks
	 */
	public abstract void prepareTask() throws Exception;
	
	/**
	 * This is the process to do the job,
	 * first to create a thread pool,
	 * then submit the tasks provided by implementing class,
	 * finally fetch result and shut down the thread pool.
	 * 
	 * @throws Exception
	 */
	private void doMultipleThreadJob() throws Exception {
		try {
			createFixedThreadPool(this.threadsNum);
			submitTask();
			returnJobResult();
		} catch (Exception e) {
			throw e;
		} finally {
			destroyThreadPool();
		}
	}

	/**
	 * To fetch task result from the submitted tasks.
	 * Will wait the result for the max time out value.
	 * 
	 * @throws Exception
	 */
	private void returnJobResult() throws Exception {
		if(this.submittedTaskList != null) {
			this.resultList = new ArrayList<T>();
			for(Future<T> future : this.submittedTaskList){
				T result = future.get(2, TimeUnit.HOURS);
				this.resultList.add(result);
			}
		}
	}
	
	/**
	 * Create a new thread pool, this will be a fixed size pool.
	 * 
	 * @param nThreads
	 * 			max number of threads
	 * @throws Exception
	 * 			when the thread number is a negative value
	 */
	private void createFixedThreadPool(int nThreads) throws Exception {
		if(nThreads <= 0)
			throw new Exception("Invalid threads number, the number can not be a negative value!");
		else
			this.threadPool = Executors.newFixedThreadPool(nThreads);
	}
	
	/**
	 * To submit the tasks provided by implementing class.
	 * 
	 * @throws Exception
	 */
	private void submitTask() throws Exception {
		if(this.taskList != null) {
			this.submittedTaskList = new ArrayList<Future<T>>();
			
			for(Callable<T> task : this.taskList)
				this.submittedTaskList.add(this.threadPool.submit(task));
		}
	}
	
	/**
	 * To shut down the thread pool after job processing
	 * 
	 * @throws Exception
	 */
	private void destroyThreadPool() throws Exception {
		if(this.threadPool != null)
			threadPool.shutdown();
	}

	public void setTaskList(List<? extends Callable<T>> taskList) {
		this.taskList = taskList;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setThreadsNum(int threadsNum) {
		this.threadsNum = threadsNum;
	}

}
