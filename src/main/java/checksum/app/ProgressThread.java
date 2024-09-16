package checksum.app;

public class ProgressThread extends Thread {

	private boolean isRunning = true;
	private ProgressThread.TaskProgress taskProgress;
	
	public ProgressThread(ProgressThread.TaskProgress taskProgress) {
		this.taskProgress = taskProgress;
	}
	
	@Override
	public void run() {
		super.run();
		while (isRunning) {
			try {
				Thread.sleep(100);
				taskProgress.running(System.currentTimeMillis(), true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		taskProgress.running(System.currentTimeMillis(), false);
	}
	
	@Override
	public synchronized void start() {
		super.start();
		taskProgress.progress();
		isRunning = false;
	}
	
	public static interface TaskProgress {
		void progress();
		void running(long milis, boolean isRunning);
	}
	
}
