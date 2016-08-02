package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSI on 18.02.2016.
 */
public class ErrorChecker {

    private static boolean isCurrentError;

    /*
    param @text - text, that was typed
    param @task - text that was assigned
    returns indexes of errors,
    where
    index[0] - beggining of the mistake
    index[1] - end of the mistake
    if no errors - returns 0;
     */

    public static List<Integer> checkTheError(String text, String task)
    {
        if(!text.equals(task.substring(0,text.length())))
        {
             List<Integer> errorIndexes=findErrorIndexes(text, task);
            return errorIndexes;
        }
        else return null;
    }

    private static List<Integer> findErrorIndexes(String text, String task)
    {
        List<Integer> indexes = new ArrayList<Integer>();
        int currentIndex;
        for (currentIndex=0; currentIndex<text.length(); currentIndex++)
        {
     /*       if(task.charAt(currentIndex)==text.charAt(currentIndex))
            {
                if(isCurrentError)
                {
                    indexes.add(currentIndex);
                    isCurrentError=false;
                }
            }
            if (task.charAt(currentIndex) != text.charAt(currentIndex)) {
                if(currentIndex==text.length()-1)
                {
                    indexes.add(currentIndex);
                }
                if(!isCurrentError)
                {
                    indexes.add(currentIndex);
                    isCurrentError=true;
                }
            }*/
            if(text.charAt(currentIndex)!=task.charAt(currentIndex))
                indexes.add(currentIndex);

        }
        isCurrentError=false;
        for(Integer i:indexes)
        {
            System.out.println(i);
        }
        return indexes;
    }
}
