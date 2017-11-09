/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JsonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.tools.cron;

/**
 * 作业启动器<br>
 * 负责检查<strong>TaskTable</strong>是否有匹配到此时运行的Task<br>
 * 检查完毕后启动器结束
 * 
 * @author Looly
 *
 */
public class TaskLauncher extends Thread{
	
	private Scheduler scheduler;
	private long millis;
	
	public TaskLauncher(Scheduler scheduler, long millis) {
		this.scheduler = scheduler;
		this.millis = millis;
	}
	
	@Override
	public void run() {
		//匹配秒部分由用户定义决定，始终不匹配年
		scheduler.taskTable.executeTaskIfMatchInternal(millis);
		
		//结束通知
		scheduler.taskLauncherManager.notifyLauncherCompleted(this);
	}
}
