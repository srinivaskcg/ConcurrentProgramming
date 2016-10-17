package locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Java program to show, how to use ReentrantLock in Java. Reentrant lock is an
 * alternative way of locking apart from implicit locking provided by
 * synchronized keyword in Java.
 */
public class ReentrantExample {

	static class ThreadTest implements Runnable {

		private final ReentrantLock lock = new ReentrantLock();
		private int count = 0;

		public void run() {
			while (true) {
				try {
					getCount();
					Thread.sleep(100);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}

		// Locking using Lock and ReentrantLock

		public int getCount() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + " gets Count: " + count);
				return count++;
			} finally {
				lock.unlock();
			}
		}

		// Implicit locking using synchronized keyword
		public synchronized int getCountTwo() {
			return count++;
		}

	}

	public static void main(String args[]) {
		final ThreadTest counter = new ThreadTest();
		Thread t1 = new Thread(counter);
		Thread t2 = new Thread(counter);
		t1.start();
		t2.start();

	}
}