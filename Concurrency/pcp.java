import java.util.*;
public class pcp {
	static int s=1;
    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
    	Vector buffer = new Vector();
        int size;
        System.out.println("**Producer-Consumer Problem**");
        System.out.println("Enter the size of the buffer:");
        size=sc.nextInt();
        Thread prodThread = new Thread(new Producer(buffer, size,s), "Producer");
        Thread consThread = new Thread(new Consumer(buffer, size,s), "Consumer");
        prodThread.start();
        consThread.start();
    }
}
class Semaphore{
	public boolean semwait(int s) {
		s++;
		return true;
	}
	public boolean semsignal(int s) {
		s--;
		return false;
	}
}
class Producer implements Runnable {
	static int s;
    private final Vector buffer;
    private final int SIZE;
    public Producer(Vector buffer, int size,int s) {
        this.buffer = buffer;
        this.SIZE = size;
        this.s=s;
    }
    Semaphore sem=new Semaphore();
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
            	sem.semwait(s);
                produce(i);
                System.out.print("Produced: " + i);
                System.out.println(" size:"+buffer.size());
                sem.semsignal(s);
            } catch (InterruptedException ex) {}
        }
    }
    private void produce(int i) throws InterruptedException {
        while (buffer.size() == SIZE) {
            synchronized (buffer) {
                System.out.println("Buffer is full,Producer is waiting , size: " + buffer.size());
                buffer.wait();
            }
        }
        synchronized (buffer) {
            buffer.add(i);
            buffer.notifyAll();
        }
    }
}
class Consumer implements Runnable {
	static int s;
    private final Vector buffer;
    private final int SIZE;

    public Consumer(Vector buffer, int size,int s) {
        this.buffer = buffer;
        this.SIZE = size;
        this.s=s;
    }
    Semaphore sem=new Semaphore();
    public void run() {
        while (true) {
            try {
            	sem.semwait(s);
                System.out.println("Consumed: " + consume()+"  size:"+buffer.size());
                Thread.sleep(50);
                sem.semsignal(s);
            } catch (InterruptedException ex) {}

        }
    }
    private int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            synchronized (buffer) {
                System.out.println("Buffer is empty;Consumer is waiting , size: " + buffer.size());
                buffer.wait();
            }
        }
        synchronized (buffer) {
            buffer.notifyAll();
            return (Integer) buffer.remove(0);
        }
    }
}