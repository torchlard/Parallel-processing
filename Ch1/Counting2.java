import java.util.concurrent.atomic.AtomicInteger;

public class Counting2 {
  public static void main(String[] args) throws InterruptedException {

    final AtomicInteger counter = new AtomicInteger();

    class CountingThread extends Thread {
      public void run(){
        for(int x=0; x<1000; x++)
          counter.incrementAndGet();
      }
    }

    CountingThread t1 = new CountingThread();
    CountingThread t2 = new CountingThread();
    t1.start(); t2.start();
    t1.join(); t2.join();

    System.out.println(counter.get());

  }
}