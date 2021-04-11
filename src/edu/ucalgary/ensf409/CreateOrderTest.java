/**
 * Active Team Member:
 @author     chad Holst <a href="mailto:chad.holst1@ucalgary.ca">chad.holst1@ucalgary.ca</a>
 @version    1.9
 @since      1.0
 */
package edu.ucalgary.ensf409;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
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
        testCreateOrder = new CreateOrder(testOrder, LocalDateTime.of(2021, 4, 11, 0, 0, 0), new Database("jdbc:mysql://localhost/inventory", "chad", "@@Kawa1000"));
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

}