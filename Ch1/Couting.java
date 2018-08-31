public class Couting {
  public static void main (String[] args) throws InterruptedException{

    class Counter {
      private int count = 0;
      // public void increment(){ count++; }
      public synchronized void increment(){ count++; }
      public int getCount(){ return count; }
    }
    
    final Counter counter = new Counter();
    
    class CountingThread extends Thread {
      public void run(){
        for(int i=0; i<1000; i++){
          counter.increment();
        }
      }
    }

    CountingThread t1 = new CountingThread();
    CountingThread t2 = new CountingThread();
    t1.start(); t2.start();
    t1.join(); t2.join();
    // if there's no lock (synchronized), two thread get old value simultaneously,
    // and write to same variable by different value

    System.out.println(counter.getCount());
    
  }
}