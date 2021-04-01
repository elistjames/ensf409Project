package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Order Class is the main class and that prompts the user to make an order on the console with a sequence of
 * questions asking for what furniture category, furniture type and the desired quantity of that item.
 */
public class Order
{
    private String furnitureCategory; // store the furniture category
    private String furnitureType; //store the furniture type
    private int numberItems; // store the quantity of desired item

    static boolean command = true; //indicates weather or not another order will be made
    /**
     * Start of the program
     * @param args
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in); //Reads the user input
        int orderCounter = 1; // indicates the order number
        Order order = new Order(); // stores order information
        Database db = new Database("jdbc:mysql://localhost/inventory", "scm", "ensf409"); //makes new database
        /**
         * This while loop is the beating heart of the program and will keep looping as long as command == true.  Every
         * loop, the program prompts the user to make a new order, if they choose to do so. If not, the while loop breaks
         * and the program ends.
         */
        while(Order.command){

            Scanner input1 = new Scanner(System.in);
            /**
             * this block of code inside the following while loop prompts the user to select one of the four furniture
             * categories.  if the selection is not valid, the while loop re-loops until a valid selection is made.
             */
            loop:   while(true){
                System.out.println("Please enter a number corresponding to your desired furniture category:");
                System.out.println("(1) Desk");
                System.out.println("(2) Chair");
                System.out.println("(3) Filing");
                System.out.println("(4) Lamp");

                switch(input1.nextLine().trim()){
                    case "1":
                        order.setFurnitureCategory("desk"); //sets furniture category to desk
                        break loop;
                    case "2":
                        order.setFurnitureCategory("chair"); // sets furniture category to chair
                        break loop;
                    case "3":
                        order.setFurnitureCategory("filing"); // sets furniture category to filing
                        break loop;
                    case "4":
                        order.setFurnitureCategory("lamp"); // sets furniture category to lamp
                        break loop;
                    default:
                        System.out.println("Invalid Entry"); // Error message if invalid selection
                        break;
                }
            }
            /**
             * The following block of code prompts the user to select a type of selected furniture category.  The do while
             * keeps re-looping until a valid selection is inputted.
             */
            Scanner input2 = new Scanner(System.in);
            if(order.getFurnitureCategory().equals("filing")){
                String t = "";
                boolean correctType;
                do{
                    correctType = false;
                    System.out.println();
                    System.out.println("What size would you like? (Small / Medium / Large)");
                    t = input2.nextLine().trim();
                    if(t.equals("Small")||t.equals("small")||t.equals("Medium")||t.equals("medium")||t.equals("Large")||t.equals("large")){
                        correctType = true;
                    }
                    if(!correctType){
                        System.out.println("Invalid entry: can only enter Small, Medium or Large");
                    }
                }
                while (!correctType);
                order.setFurnitureType(t);
            }
            else{
                String t = "";
                boolean correctType;

                do{
                    correctType = false;
                    System.out.println();
                    System.out.println("Please enter a type: ");
                    t = input2.nextLine().trim();
                    for(int i = 0; i < t.length(); i++){
                        if((Character.toLowerCase(t.charAt(i)) > 64 && Character.toLowerCase(t.charAt(i)) < 91)|| (Character.toLowerCase(t.charAt(i)) > 96 && Character.toLowerCase(t.charAt(i)) < 123)){
                            correctType = true;
                        }
                    }
                    if(!correctType){
                        System.out.println("Invalid entry: Please enter a valid type of furniture");
                    }

                }
                while (!correctType);
                order.setFurnitureType(t);
            }
            Scanner input3 = new Scanner(System.in);
            boolean correctAmount = true;
            int n = 0;
            do{
                System.out.println();
                System.out.println("Please enter the amount: ");
                try {
                    n = input3.nextInt();
                }
                catch (InputMismatchException e){
                    System.out.println("Must be an integer");
                    correctAmount = false;
                }
                if(!correctAmount){
                    System.out.println("Invalid entry: Must enter a valid amount");
                }
            }
            while(!correctAmount);

            order.setNumberItems(n); // sets the number of items to the amount that the user inputted

            CreateOrder co = new CreateOrder(order, orderCounter, db); // creates new order for based from user's input

            db.initializeConnection(); // initialize the connection to the database
            db.updateLocal(); // update the Objects arrays
            ArrayList<Integer> already = new ArrayList<Integer>(); // create null arraylist to pass into Price search algorithms.
            switch(order.getFurnitureCategory()){
                case "desk": //If category is desk
                    co.deskPrice(db.getDesk(), 0, already, order.getFurnitureType(), order.getNumberItems(), 0, 0, 0);
                    int lowestDesk = co.getLowestPrice();
                    if(lowestDesk != 0){
                        co.setTotalPrice(lowestDesk);
                        ArrayList<Integer> orderedItems = co.getLowestCombination();
                        String[] deskIds = co.makeIdArray(orderedItems, 0);
                        System.out.println("The price found to make this item is: $" + lowestDesk+".00");
                        db.updateTable(orderedItems, 0);
                        co.setItemsOrdered(deskIds);
                        co.generateOrder();
                        co.clearLists();
                    }
                    else{
                        System.out.println("Impossible to make this order due to lack of inventory");
                        co.generateRecommendation();
                    }
                    break;
                case "chair": // if the category is chair
                    co.chairPrice(db.getChairs(), 0, already, order.getFurnitureType(), order.getNumberItems(), 0, 0, 0, 0);
                    int lowestChair = co.getLowestPrice();
                    if(lowestChair != 0){
                        co.setTotalPrice(lowestChair);
                        ArrayList<Integer> orderedItems = co.getLowestCombination();
                        String[] chairIds = co.makeIdArray(orderedItems, 1);
                        System.out.println("The price found to make this item is: $" + lowestChair+".00");
                        db.updateTable(orderedItems, 1);
                        co.setItemsOrdered(chairIds);
                        co.generateOrder();
                        co.clearLists();
                    }
                    else{
                        System.out.println("Impossible to make this order due to lack of inventory");
                        co.generateRecommendation();
                    }
                    break;
                case "filing": // if category is filing
                    co.filingPrice(db.getFilings(), 0, already, order.getFurnitureType(), order.getNumberItems(), 0, 0, 0);
                    int lowestFiling = co.getLowestPrice();
                    if(lowestFiling != 0){
                        co.setTotalPrice(lowestFiling);
                        ArrayList<Integer> orderedItems = co.getLowestCombination();
                        String[] filingIds = co.makeIdArray(orderedItems, 2);
                        System.out.println("The price found to make this item is: $" + lowestFiling+".00");
                        db.updateTable(orderedItems, 2);
                        co.setItemsOrdered(filingIds);
                        co.generateOrder();
                        co.clearLists();
                    }
                    else{
                        System.out.println("Impossible to make this order due to lack of inventory");
                        co.generateRecommendation();
                    }
                    break;
                case "lamp": //if category is lamp
                    co.lampPrice(db.getLamps(), 0, already, order.getFurnitureType(), order.getNumberItems(), 0, 0);
                    int lowestLamp = co.getLowestPrice();
                    if(lowestLamp != 0){
                        co.setTotalPrice(lowestLamp);
                        ArrayList<Integer> orderedItems = co.getLowestCombination();
                        String[] lampIds = co.makeIdArray(orderedItems, 3);
                        System.out.println("The price found to make this item is: $" + lowestLamp+".00");
                        db.updateTable(orderedItems, 3);
                        co.setItemsOrdered(lampIds);
                        co.generateOrder();
                        co.clearLists();
                    }
                    else{
                        System.out.println("Impossible to make this order due to lack of inventory");
                        co.generateRecommendation();
                    }
                    break;
            }

            /**
             * The following block of code prompts the user to input if they want to make another input or not.  If they do,
             * the program re-loops to the beginning and the whole sequence is preformed again. If not, the program ends.
             */
            boolean valid = true;
            StringBuilder yn = new StringBuilder();
            StringBuilder a = new StringBuilder(yn.toString());
            while(valid){
                System.out.println();
                System.out.println("Would you like to make another order? (Y/N)");
                yn.append(input.nextLine().trim());
                if(yn.length() < 2){
                    if(yn.toString().equals("y") || yn.toString().equals("Y") || yn.toString().equals("n") || yn.toString().equals("N")){
                        if(yn.toString().equals("n") || yn.toString().equals("N")){
                            valid = false;
                            command = false;

                        }
                        else{
                            orderCounter++;
                        }
                        break;
                    }
                    else{
                        System.out.println("You can only enter Y or N");
                    }
                }
            }
        }
        input.close();
    }

    /**
     * Default constructor for class Order
     */
    public Order(){}

    /**
     * Getter method for NumberItems
     * @return numberItems
     */
    public int getNumberItems() {
        return numberItems;
    }

    /**
     * Getter method for furnitureCategory
     * @return furnitureCategory
     */
    public String getFurnitureCategory() {
        return furnitureCategory;
    }

    /**
     * Getter method for furnitureType
     * @return furnitureType
     */
    public String getFurnitureType() {
        return furnitureType;
    }

    /**
     * Setter method for numberItems
     * @param numberItems number of Items
     */
    public void setNumberItems(int numberItems) {
        this.numberItems = numberItems;
    }

    /**
     * Setter method for furniture category
     * @param furnitureCategory furniture category
     */
    public void setFurnitureCategory(String furnitureCategory) {
        this.furnitureCategory = furnitureCategory;
    }

    /**
     * Setter method for furnitureType
     * @param furnitureType furniture type
     */
    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

}//End of Order documentation

