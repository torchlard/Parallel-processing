import java.util.concurrent.locks.ReentrantLock;

public class Interruptible {

  static class Acc {
    final ReentrantLock l1 = new ReentrantLock();
    final ReentrantLock l2 = new ReentrantLock();
    
    Thread t1 = new Thread(){
      public void run(){
        try {
          l1.lockInterruptibly();
          Thread.sleep(3000);
          l2.lockInterruptibly();
        } catch(InterruptedException e) {System.out.println("t1 interrupted"); }
      }
    };
  }

  public static void main(String[] args) throws InterruptedException {
    Acc acc = new Acc();
    acc.t1.start();
    acc.t1.join();
  }
}







