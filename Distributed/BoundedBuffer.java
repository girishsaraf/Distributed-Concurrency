package precodes;
class BoundedBuffer {        // designed for a single producer thread
                             // and a single consumer thread
   private int numSlots = 0;
   private double[] buffer = null;
   private int putIn = 0, takeOut = 0;
   private int count = 0;

   public BoundedBuffer(int numSlots) {
      if (numSlots <= 0) throw new IllegalArgumentException("numSlots<=0");
      this.numSlots = numSlots;
      buffer = new double[numSlots];
      System.out.println("BoundedBuffer alive, numSlots=" + numSlots);
   }

   public synchronized void deposit(double value) {
      while (count == numSlots)
         try {
            wait();
         } catch (InterruptedException e) {
            System.err.println("interrupted out of wait");
         }
      buffer[putIn] = value;
      putIn = (putIn + 1) % numSlots;
      count++;                   // wake up the consumer
      if (count == 1) notify();  // since it might be waiting
      System.out.println(" after deposit, count=" + count
         + ", putIn=" + putIn);
   }

   public synchronized double fetch() {
      double value;
      while (count == 0)
         try {
            wait();
         } catch (InterruptedException e) {
            System.err.println("interrupted out of wait");
         }
      value = buffer[takeOut];
      takeOut = (takeOut + 1) % numSlots;
      count--;                           // wake up the producer
      if (count == numSlots-1) notify(); // since it might be waiting
      System.out.println(" after fetch, count=" + count
         + ", takeOut=" + takeOut);
      return value;
   }
   public static void main(String args[]) {
	   BoundedBuffer b=new BoundedBuffer(3);
   }
}