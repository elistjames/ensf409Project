/**
 * Active Team Members:
 @author     chad Holst <a href="mailto:chad.holst1@ucalgary.ca">chad.holst1@ucalgary.ca</a>
 @author     Callum Matheson <a href="mailto:callum.matheson1@ucalgary.ca">callum.matheson1@ucalgary.ca</a>
 @version    1.9
 @since      1.0
 */
package edu.ucalgary.ensf409;

import java.time.LocalDateTime;
import org.junit.*;
import java.io.*;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Tests various methods from the @CreateOrder class
 */
public class CreateOrderTest
{
    private CreateOrder testCreateOrder;
    private Order testOrder;

    @Before
    public void setUp()
    {
        testOrder = new Order();
        testOrder.setFurnitureCategory("desk");
        testOrder.setFurnitureType("Standing");
        testOrder.setNumberItems(0);
        testCreateOrder = new CreateOrder(testOrder, LocalDateTime.of(2021, 4, 11, 0, 0, 0), new Database("jdbc:mysql://localhost/inventory", "scm", "ensf409"));
    }

    /**
     * testGetLowestPrice() will test to see if the lowest price is obtained when 0 items are ordered. We assert that the lowest
     * expected price will be 0 compared to actual which is 0.
     */
    @Test
    public void testGetLowestPrice()
    {
        // setup
        int actual = 0;

        // run test
        int expected = testCreateOrder.getLowestPrice();

        // verify
        Assert.assertEquals("Not the lowest price possible which is 0 since 0 items were ordered",expected, actual);
    }

    @Test
    //Check to make sure file paths are created as intended
    public void testCreatePath() {
        //generating date time
        CreateOrder output = new CreateOrder(testOrder, LocalDateTime.of(2021, 4, 11, 0, 0, 0), new Database("jdbc:mysql://localhost/inventory", "scm", "ensf409"));
        Assert.assertTrue("File of the given name was not created", output.getFileName().exists());
    }
    @Test
    //Ensure order chit is created with expected lines
    public void testGenerateOrder() throws FileNotFoundException {
        //generate through program regular order
        testOrder.setFurnitureCategory("chair");
        testOrder.setFurnitureType("Mesh");
        testOrder.setNumberItems(1);
        CreateOrder output = new CreateOrder(testOrder, LocalDateTime.of(2021, 4, 11, 0, 0, 0), new Database("jdbc:mysql://localhost/inventory", "scm", "ensf409"));
        String[] ordered = new String[] {"B203", "C302"};
        output.setItemsOrdered(ordered);
        output.setTotalPrice(200);
        output.generateOrder();
        //create expected output orderform
        String[] expected = {
                "Furniture Order Form",
                "",
                "Faculty Name:",
                "Contact:",
                "Date:",
                "",
                "Original Request: Mesh chair, 1",
                "",
                "Items Ordered",
                "ID: B203",
                "ID: C302",
                "",
                "Total Price: $200.00"
        };
        try {
            Scanner testOut = new Scanner(output.getFileName());
            int i = 0;
            while(testOut.hasNextLine()) {
                Assert.assertEquals(expected[i], testOut.nextLine());
                i++;
            }
        } catch(IOException e) {
            System.out.println("test failed");
            System.exit(1);
        }
    }
    @Test
    //ensure recommendation is created correctly
    public void testGenerateRecommendation() {
        testOrder.setFurnitureCategory("chair");
        testOrder.setFurnitureType("Mesh");
        testOrder.setNumberItems(1);
        CreateOrder output = new CreateOrder(testOrder, LocalDateTime.of(2021, 4, 11, 0, 0, 0), new Database("jdbc:mysql://localhost/inventory", "scm", "ensf409"));
        output.generateRecommendation();
        String[] expected = {
                "Original request cannot be completed due to current inventory",
                "",
                "Original Request: Mesh chair,1",
                "",
                "To complete your order please contact the following UofC approved suppliers:",
                "Fine Office Supplies",
                "Chairs R Us"};
        try {
            Scanner outTest = new Scanner(output.getFileName());
            int i = 0;
            while(outTest.hasNextLine()) {
                Assert.assertEquals(expected[i], outTest.nextLine());
                i++;
            }
        } catch (IOException e) {
            System.out.println("test failed");
            System.exit(1);
        }
    }
}