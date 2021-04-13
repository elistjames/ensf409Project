/**
 * Active Team Member:
 @author     chad Holst <a href="mailto:chad.holst1@ucalgary.ca">chad.holst1@ucalgary.ca</a>
 @version    1.9
 @since      1.0
 */

package edu.ucalgary.ensf409;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.Assert.*;
import java.util.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * Tests various methods from the @Order class
 */
public class OrderTest
{
    private Order testOrder;
    /**
     * class OrderTest will test the user inputs such that the integer value inputted corresponds to their respective data member,
     * furnitureCategory and furnitureType. We simulate user input using the System Rules library.
     */

    @Before
    public void setUp()
    {
        testOrder = new Order(); // test object
    }
    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream(); // allows for access to an input stream where we place the input into provideLines() method
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog(); // allows for checking what gets printed to the terminal

    /**
     * testUserCategory() will demonstrate that if you select an incorrect input, then the method will loop again until a
     * valid input is made. For this test, we try inputting anything other than 1,2,3, or 4 into the input stream.
     * After the incorrect input, we supply the correct input as "1"; "1" corresponds
     * to setting the furnitureCategory data member in the Order class to "desk". This shows that any invalid input
     * will be taken care of by the loop which minimizes any potential input errors. "2" corresponds to "chair", "3" corresponds to
     * "filing" and "4" corresponds to "lamp". We test that the default branch is taken with SystemOutRule() which
     * retrieves the error message "Invalid Input" and asserts that the branch was taken through assertEquals() which asserts the error message matches.
     * Then, we supply correct input of "1" and assertEquals() that "1" corresponds to setting "desk" in furnitureCategory
     * data member. Unfortunately, the test only demonstrates the loop instead of actually implementing it
     * because we had a Scanner: line not found error everytime we tried to loop with the test class.
     */
    @Test
    public void testUserCategoryFirstLevel()
    {
        // setup
        boolean passedTest = false; // will stay false if incorrect input is made, assigned true if input is correct

        // run test
        try
        {
            Scanner testInput = new Scanner(System.in); // input stream for entering incorrect String other than 1,2,3 or 4
            systemInMock.provideLines("any String other than 1,2,3 or 4"); // any incorrect input causes method to take default branch back and prints error message
            String capturedInput = testOrder.userCategory(testInput); // will have nothing to return/set furnitureCategory data member with in Order class so switch reloops and default branch chosen
            assertEquals("Invalid Entry", systemOutRule.getLog()); // check if "Invalid Entry" was printed and default branch was selected in switch statement. Then, goes back to top of switch.
        }
        catch (Exception InvalidInput)
        {
            systemInMock.provideLines("1"); // inputs correct switch case 1 which corresponds to "desk" and sets furnitureCategory to "desk".
            Scanner testInput2 = new Scanner(System.in);//
            String revisedInput = testOrder.userCategory(testInput2); // we supply the correct input to the method now and it corresponds to "desk"
            if(revisedInput.equals("desk") || revisedInput.equals("chair") || revisedInput.equals("lamp") || revisedInput.equals("filing"))
            {
                passedTest = true;
            }
        }

        // verify
        Assert.assertTrue("The user must select 1,2,3 or 4", passedTest);
    }

    /**
     * testUserTypeLevelTwo() will check if the subsequent input level occurs; i.e, a case was taken in the userCategory() method switch statement
     * because there was valid input of 1,2,3 or 4. We test that all possible branches in the second input are satisfied corresponding to their
     * respective furnitureCategory. We test the possible furnitureType data member branches that are set as each case in the switch in UserType()
     * corresponds to the data member, furnitureType. We generate random integer numbers 1-4 to first take a correct input for
     * furnitureCategory data member. Then, we generate random integers depending on how many possible furnitureType options for each branch option.
     * When we take a subsequent branch, boolean passedTest is true. We assert that it is true which verifies it has taken a branch.
     * As you can see it will display the different branch choices printed on the terminal depending on furnitureCategory selected.
     */
    @Test
    public void testUserTypeLevelTwoInput()
    {
        // setup
        boolean passedTest = false;
        int randomFurnitureCategory = (int)(Math.random() * 4 + 1); // random number between 1-4 which are correct
        String randomInput = String.valueOf(randomFurnitureCategory);

        // run test
        Scanner testInput = new Scanner(System.in);
        systemInMock.provideLines(randomInput); // inputs correct random integer for 1-4 for the first level of inputs which are correct
        String correctLevelOne = testOrder.userCategory(testInput);
        testOrder.setFurnitureCategory(correctLevelOne);

        if(testOrder.getFurnitureCategory().equals("desk"))
        {
            int randomDeskType = (int)(Math.random() * 3 + 1); // random number between 1-3 which are correct
            String randomInput2 = String.valueOf(randomDeskType);
            Scanner testInput2 = new Scanner(System.in);
            systemInMock.provideLines(randomInput2);
            String correctLevelTwoDesk = testOrder.userType(testOrder, testInput2);
            if(correctLevelTwoDesk.equals("Traditional") || correctLevelTwoDesk.equals("Adjustable") || correctLevelTwoDesk.equals("Standing"))
            {
                passedTest = true;
            }
        }
        if(testOrder.getFurnitureCategory().equals("chair"))
        {
            int randomChairType = (int)(Math.random() * 5 + 1); // random number between 1-5 which are correct
            String randomInput3 = String.valueOf(randomChairType);
            Scanner testInput3 = new Scanner(System.in);
            systemInMock.provideLines(randomInput3);
            String correctLevelTwoChair = testOrder.userType(testOrder, testInput3);
            if(correctLevelTwoChair.equals("Task") || correctLevelTwoChair.equals("Mesh") || correctLevelTwoChair.equals("Kneeling"))
            {
                passedTest = true;
            }
            if(correctLevelTwoChair.equals("Executive") || correctLevelTwoChair.equals("Ergonomic"))
            {
                passedTest = true;
            }
        }
        if(testOrder.getFurnitureCategory().equals("filing"))
        {
            int randomFilingType = (int)(Math.random() * 3 + 1); // random number between 1-3 which are correct
            String randomInput4 = String.valueOf(randomFilingType);
            Scanner testInput4 = new Scanner(System.in);
            systemInMock.provideLines(randomInput4);
            String correctLevelTwoFiling = testOrder.userType(testOrder, testInput4);
            if(correctLevelTwoFiling.equals("Small") || correctLevelTwoFiling.equals("Medium") || correctLevelTwoFiling.equals("Large"))
            {
                passedTest = true;
            }
        }
        if(testOrder.getFurnitureCategory().equals("lamp"))
        {
            int randomLampType = (int)(Math.random() * 3 + 1); // random number between 1-3 which are correct
            String randomInput5 = String.valueOf(randomLampType);
            Scanner testInput5 = new Scanner(System.in);
            systemInMock.provideLines(randomInput5);
            String correctLevelTwoLamp = testOrder.userType(testOrder, testInput5);
            if(correctLevelTwoLamp.equals("Desk") || correctLevelTwoLamp.equals("Swing Arm") || correctLevelTwoLamp.equals("Study"))
            {
                passedTest = true;
            }
        }
        Assert.assertTrue("A branch in the second level of inputs must be taken", passedTest);
    }

}