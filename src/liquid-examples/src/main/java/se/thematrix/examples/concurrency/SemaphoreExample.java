package se.thematrix.examples.concurrency;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		SemaphoreExample example = new SemaphoreExample();
		for (int i = 0; i < 10; i++) {
			System.out.println(example.getNextValue());
		}
	}
	
	private int value = 0;
	
	private final Semaphore mutex = new Semaphore(1);
	
	private int getNextValue() throws InterruptedException {
		try{
			mutex.acquire();
			return value++;
		}finally{
			mutex.release();
		}
	}

}