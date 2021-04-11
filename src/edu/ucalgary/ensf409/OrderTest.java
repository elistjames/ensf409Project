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
    capturedInput = testInputWithIncorrectData if (capturedInput == repeatOptions) {
    revisedInput = testInputWithCorrectData
    if (revisedInput == nextSetOfOptions) {
        passedTest = true
    }
}
    */
    //assertTrue("Message", passedTest)

    /**
     * testUserCategory() will verify that if you select an incorrect input, then the method will reloop until a
     * valid input is made. For this example, we input anything other than 1,2,3,4 into the input stream and
     * then if it is not 1,2,3 or 4, then it will call the method again and supply the correct input as 1; 1 corresponds
     * to setting the furnitureCategory data member in the Order class to "chair". This shows that any invalid input
     * will be taken care of by the loop which stops a propagation of errors.
     */

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    @Test
    public void testUserCategoryFirstLevel()
    {
        // set up
        boolean passedTest = false; // will stay false if incorrect input is made, assigned true if input is correct
        Order testObject = new Order();
        Scanner testInput = new Scanner(System.in); // input stream for entering 1,2,3 or 4

        // run test

        systemInMock.provideLines("while any invalid input other than 1,2,3 or 4 is put into input stream"); // inputting anything other than 1,2,3 or 4 it loops back for valid input
        String capturedInput = testObject.userCategory(testInput);
        if (!capturedInput.equals("desk") || !capturedInput.equals("chair") || !capturedInput.equals("lamp") || !capturedInput.equals("filing"))
        {
            systemInMock.provideLines("1"); // selects correct switch case and sets data member furnitureCategory to "chair" in Order class
            String revisedInput = testObject.userCategory(testInput);
            if(revisedInput.equals("desk") || revisedInput.equals("chair") || revisedInput.equals("lamp") || revisedInput.equals("filing"));
            {
                passedTest = true;
            }
        }
        Assert.assertTrue("The user must select 1,2,3 or 4", passedTest);
    }
    public void testUserTypeLevelTwo()
    {

        /**
        if(revisedInput.equals("Traditional") || revisedInput.equals("Adjustable") || revisedInput.equals("Adjustable"))
        {
            passTest = true;
        }
         */

    }

}