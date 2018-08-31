import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

public class WordCount {
  private static final HashMap<String, Integer> counts = new HashMap<String, Integer>();

  public static void main(String[] args) throws Exception {
    // Iterable<Page> pages = new Pages(10000, "enwiki.xml");
    // for(Page page: pages){
    //   Iterable<String> words = new Words(page.getText());
    //   for(String word: words)
    //     countWord(word);
    // }

    ArrayBlockingQueue<Page> queue = new ArrayBlockingQueue<>(100);
    HashMap<String, Integer> counts = new HashMap<String, Integer>();

    Thread counter = new Thread(new Counter(queue, counts));
    Thread parser = new Thread(new Parser(queue));

    counter.start();
    parser.start();

    parser.join();
    queue.put(new PoisonPill());
    counter.join();
  }


  private static void countWord(String word){
    Integer currentCount = counts.get(word);
    if(currentCount == null)
      counts.put(word, 1);
    else
      counts.put(word, currentCount + 1);
  }
}












