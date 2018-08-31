public class Puzzle {

  static boolean answerReady = false;
  static int answer = 0;

  static Thread t1 = new Thread(){
    public void run(){
      answer = 42;
      answerReady = false;
    }
  };

  static Thread t2 = new Thread(){
    public void run(){
      if (answerReady){
        System.out.println("answer is " + answer);
      } else {
        System.out.println("nothing");
      }
    }
  };
  
  public static void main(String[] args) throws InterruptedException {
    t1.start();
    t2.start();

    t1.join();
    t2.join();
/* possible scenario:
  1. answer is 42
  2. answer is 0  
  3. nothing
*/
  }
}