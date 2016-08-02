package sample;



import java.util.Map;
import java.util.TreeMap;
import javafx.scene.*;
/**
 * Created by MSI on 18.02.2016.
 */
public class PerformanceAnalytics {
   private static long start;
   private static long finish;
   private static long inputStart;
   private static long inputFinish;
   private static TreeMap<Float,Character> timeTable= new TreeMap<Float, Character>();
   public static void startClock()
   {
      start=System.nanoTime();
      inputStart=start;
   }

   public static void noteChar(String text)
   {
      inputFinish=System.nanoTime();
      timeTable.put((float)(inputFinish-inputStart)/1000000000, text.charAt(text.length()-1));
      inputStart=System.nanoTime();


   }

   public static String stopClock(String task)
   {
      finish=System.nanoTime();
      Map.Entry<Float, Character> pair = timeTable.pollLastEntry();
      System.out.println(pair.getKey()+ "it is");
      float CPM = task.length()/((float)(finish-start)/1000000000/60);
      String resultText = pair.getValue()+" took"+ String.format("%.03f",pair.getKey())+" seconds" +
              "/r/n Your effective CPM are "+CPM;
      return resultText;
   }
}
