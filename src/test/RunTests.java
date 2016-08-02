package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by MSI on 01.06.2016.
 */
public class RunTests {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(IndexesTests.class);
        for (Failure failure : result.getFailures())
        {
            System.out.println(failure.getMessage());
        }
        System.out.println(result.wasSuccessful());
    }
}
