package edu.ucalgary.ensf409;
import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.Assert.*;

import java.util.*;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class OrderTest
{

    static boolean command = true;

    /*
    passedTest = false
    capturedInput = testInputWithIncorrectData
     if (capturedInput == repeatOptions)
     {
    revisedInput = testInputWithCorrectData
    if (revisedInput == nextSetOfOptions) {
        passedTest = true
    }
    */
    //assertTrue("Message", passedTest)

    /**
     * testUserCategory() will verify that if you select an incorrect input, then the method will reloop until a
     * valid input is made.
     */

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    @Test
    public void testUserCategory()
    {
        // set up
        boolean passTest = false; // will stay false if incorrect input is made, assigned true if input is correct
        Order testObject = new Order();
        Scanner testInput = new Scanner(System.in);

        // run test
        testObject.userCategory(testInput);
        systemInMock.provideLines("1","2","3","4");

    }

}
