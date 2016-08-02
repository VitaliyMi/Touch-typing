package test;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import sample.ErrorChecker;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by MSI on 01.06.2016.
 */
public class IndexesTests{

    @Test
    public void checkErrorCheking()
    {
        String correctText = "0123456789";
        String wrongText = "0223466789";
        List<Integer> mistakes= ErrorChecker.checkTheError(wrongText, correctText);
        assertEquals((int)mistakes.get(0),1);
        assertEquals((int) mistakes.get(1), 5);
    }

}
