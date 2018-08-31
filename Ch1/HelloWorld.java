public class HelloWorld {

  public static void main(String[] args) throws InterruptedException {
    Thread myThread = new Thread(){
      public void run(){
        System.out.println("hello world fomr new");
      }
    };

    myThread.start();
    // Thread.yield();
    Thread.sleep(1);
    System.out.println("hello from main thread");
    myThread.join();

  }
}









