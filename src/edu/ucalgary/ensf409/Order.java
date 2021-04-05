package edu.ucalgary.ensf409;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private LocalDateTime ldt;

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
         * This while loop is the beating heart of the program and will keep looping as long as the while argument is true.  Every
         * loop, the program prompts the user to make a new order, if they choose to do so. If not, the while loop breaks
         * and the program ends.
         */
        while(true){

            Scanner input1 = new Scanner(System.in);
            order.setFurnitureCategory(order.userCategory(input1));

            Scanner input2 = new Scanner(System.in);
            order.setFurnitureType(order.userType(order, input2));

            Scanner input3 = new Scanner(System.in);
            order.setNumberItems(order.userNumber(order, input3));

            CreateOrder co = new CreateOrder(order, order.getLDT(), db); // creates new order for based from user's input
            db.initializeConnection(); // initialize the connection to the database
            db.updateLocal(); // update the Objects arrays

            order.operation(order, co, db);

            if(order.makeAnotherOrder(order, input)){
                orderCounter++;
            }else{
                break;
            }
        }
        input.close();
    }

    /**
     * this method prompts the user to select one of the four furniture categories.  if the selection is not valid,
     * the while loop re-loops until a valid selection is made.
     * @param input User input reader
     * @return String of the category chosen by the user
     */
    public String userCategory(Scanner input) {
        String category = "";

        loop:   while(true){
            System.out.println("Please enter a number corresponding to your desired furniture category:");
            System.out.println("(1) Desk");
            System.out.println("(2) Chair");
            System.out.println("(3) Filing");
            System.out.println("(4) Lamp");

            switch(input.nextLine().trim()){
                case "1":
                    category = "desk";
                    break loop;
                case "2":
                    category = "chair";
                    break loop;
                case "3":
                    category = "filing";
                    break loop;
                case "4":
                    category = "lamp";
                    break loop;
                default:
                    System.out.println("Invalid Entry"); // Error message if invalid selection
                    break;
            }
        }
        return category;
    }

    /**
     * This method prompts the user to select a type of selected furniture category.  The do while
     * keeps re-looping until a valid selection is inputted.
     * @param order object
     * @param input user input reader
     * @return String of the type chosen by the user.
     */
    public String userType(Order order, Scanner input){
        String type = "";

        if(order.getFurnitureCategory().equals("filing")){
            type = "";
            boolean correctType;
            do {
                correctType = false;
                System.out.println();
                System.out.println("Enter a number corresponding to the type of lamp you would like:");
                System.out.println("(1) Small");
                System.out.println("(2) Medium");
                System.out.println("(3) Large");
                switch(input.nextLine().trim()){
                    case "1":
                        type = "Small";
                        correctType = true;
                        break;
                    case "2":
                        type = "Medium";
                        correctType = true;
                        break;
                    case "3":
                        type = "Large";
                        correctType = true;
                        break;
                    default:
                        System.out.println("Can only enter 1, 2, or 3");
                }
            }
            while (!correctType);
        }
        else if(order.getFurnitureCategory().equals("lamp")){
            type = "";
            boolean correctType;
            do{
                correctType = false;
                System.out.println();
                System.out.println("Enter a number corresponding to the type of lamp you would like:");
                System.out.println("(1) Desk");
                System.out.println("(2) Swing Arm");
                System.out.println("(3) Study");
                switch(input.nextLine().trim()){
                    case "1":
                        type = "Desk";
                        correctType = true;
                        break;
                    case "2":
                        type = "Swing Arm";
                        correctType = true;
                        break;
                    case "3":
                        type = "Study";
                        correctType = true;
                        break;
                    default:
                        System.out.println("Can only enter 1, 2, or 3");
                }
            }
            while (!correctType);
        }
        else if(order.getFurnitureCategory().equals("desk")){
            type = "";
            boolean correctType;
            do{
                correctType = false;
                System.out.println();
                System.out.println("Enter a number corresponding to the type of desk you would like:");
                System.out.println("(1) Traditional");
                System.out.println("(2) Adjustable");
                System.out.println("(3) Standing");

                switch(input.nextLine().trim()){
                    case "1":
                        type = "Traditional";
                        correctType = true;
                        break;
                    case "2":
                        type = "Adjustable";
                        correctType = true;
                        break;
                    case "3":
                        type = "Standing";
                        correctType = true;
                        break;
                    default:
                        System.out.println("Can only enter 1, 2, or 3");
                }
            }
            while (!correctType);
        }
        else{
            type = "";
            boolean correctType;
            do{
                correctType = false;
                System.out.println();
                System.out.println("Enter a number corresponding to the type of chair you would like:");
                System.out.println("(1) Task");
                System.out.println("(2) Mesh");
                System.out.println("(3) Kneeling");
                System.out.println("(4) Executive");
                System.out.println("(5) Ergonomic");

                switch(input.nextLine().trim()){
                    case "1":
                        type = "Task";
                        correctType = true;
                        break;
                    case "2":
                        type = "Mesh";
                        correctType = true;
                        break;
                    case "3":
                        type = "Kneeling";
                        correctType = true;
                        break;
                    case "4":
                        type = "Executive";
                        correctType = true;
                        break;
                    case "5":
                        type = "Ergonomic";
                        correctType = true;
                        break;
                    default:
                        System.out.println("Can only enter 1, 2, 3, 4, or 5.");
                }
            }
            while (!correctType);
        }
        return type;
    }

    /**
     * This method prompts the user to input the quantity of their desired furniture item.
     * @param order object
     * @param input user input reader
     * @return Integer of the amount inputted by the user.
     */
    public int userNumber(Order order, Scanner input){
        boolean correctAmount = true;
        int number = 0;
        do{
            System.out.println();
            System.out.println("Please enter the amount: ");
            try {
                number = input.nextInt();
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

        return number;
    }

    /**
     * This method uses the values stored in the order variables chosen by the user to make the output file.
     * @param order object
     * @param co CreateOrder object
     * @param db DataBase object
     */
    public void operation(Order order, CreateOrder co, Database db){
        ArrayList<Integer> already = new ArrayList<Integer>(); // create null arraylist to pass into Price search algorithms.
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH~mm~ss");
        String formatDateTime = ldt.format(format);
        switch(order.getFurnitureCategory()){
            case "desk": //If category is desk
                int lowestDesk = co.deskPrice(db.getDesk(), 0, already, order.getFurnitureType(), order.getNumberItems(),
                        0, 0, 0); // get lowest price of all combinations
                if(lowestDesk != 0){ // if a combination was found
                    co.setTotalPrice(lowestDesk); // set lowest price found to the member variable totalPrice in CreateOrder
                    ArrayList<Integer> orderedItems = co.getLowestCombination(); // get the indexes of the combination to make the lowest price
                    String[] deskIds = co.makeIdArray(orderedItems, 0); // Get the Id's of the desks corresponding to the combined indexes
                    System.out.println("The price found to make this item is: $" + lowestDesk+".00");

                    db.updateTable(orderedItems, 0); // update the desk table
                    co.setItemsOrdered(deskIds);
                    co.generateOrder(); //Makes the output file containing the order form for the requested desk.
                    co.clearLists(); //clear the array lists storing the prices and combinations
                }
                else{
                    System.out.println("Impossible to make this order due to lack of inventory");
                    co.generateRecommendation(); // makes a output file containing the recommended manufacturers to make desk.
                }
                break;
            case "chair": // if the category is chair
                int lowestChair = co.chairPrice(db.getChairs(), 0, already, order.getFurnitureType(), order.getNumberItems(),
                        0, 0, 0, 0); // get lowest price of all combinations
                if(lowestChair != 0){ // if a combination was found
                    co.setTotalPrice(lowestChair); // set lowest price found to the member variable totalPrice in CreateOrder
                    ArrayList<Integer> orderedItems = co.getLowestCombination(); // get the indexes of the combination to make the lowest price
                    String[] chairIds = co.makeIdArray(orderedItems, 1); // Get the Id's of the chairs corresponding to the combined indexes
                    System.out.println("The price found to make this item is: $" + lowestChair+".00");
                    db.updateTable(orderedItems, 1); // update the chair table
                    co.setItemsOrdered(chairIds);
                    co.generateOrder(); //Makes the output file containing the order form for the requested chair.
                    co.clearLists(); //clear the array lists storing the prices and combinations
                }
                else{
                    System.out.println("Impossible to make this order due to lack of inventory");
                    co.generateRecommendation(); // makes a output file containing the recommended manufacturers to make chair.
                }
                break;
            case "filing": // if category is filing
                int lowestFiling = co.filingPrice(db.getFilings(), 0, already, order.getFurnitureType(), order.getNumberItems(),
                        0, 0, 0); // get lowest price of all combinations
                if(lowestFiling != 0){ // if a combination was found
                    co.setTotalPrice(lowestFiling); // set lowest price found to the member variable totalPrice in CreateOrder
                    ArrayList<Integer> orderedItems = co.getLowestCombination(); // get the indexes of the combination to make the lowest price
                    String[] filingIds = co.makeIdArray(orderedItems, 2); // Get the Id's of the filings corresponding to the combined indexes
                    System.out.println("The price found to make this item is: $" + lowestFiling+".00");
                    db.updateTable(orderedItems, 2); // update the filing table
                    co.setItemsOrdered(filingIds);
                    co.generateOrder(); //Makes the output file containing the order form for the requested filing.
                    co.clearLists(); //clear the array lists storing the prices and combinations
                }
                else{
                    System.out.println("Impossible to make this order due to lack of inventory");
                    co.generateRecommendation(); // makes a output file containing the recommended manufacturers to make filing.
                }
                break;
            case "lamp": //if category is lamp
                int lowestLamp = co.lampPrice(db.getLamps(), 0, already, order.getFurnitureType(), order.getNumberItems(),
                        0, 0); // get lowest price of all combinations
                if(lowestLamp != 0){ // if a combination was found
                    co.setTotalPrice(lowestLamp); // set lowest price found to the member variable totalPrice in CreateOrder
                    ArrayList<Integer> orderedItems = co.getLowestCombination(); // get the indexes of the combination to make the lowest price
                    String[] lampIds = co.makeIdArray(orderedItems, 3); // Get the Id's of the lamps corresponding to the combined indexes
                    System.out.println("The price found to make this item is: $" + lowestLamp+".00");
                    db.updateTable(orderedItems, 3); // update the lamp table
                    co.setItemsOrdered(lampIds);
                    co.generateOrder(); //Makes the output file containing the order form for the requested lamp.
                    co.clearLists(); //clear the array lists storing the prices and combinations
                }
                else{
                    System.out.println("Impossible to make this order due to lack of inventory");
                    co.generateRecommendation(); // makes a output file containing the recommended manufacturers to make lamp.
                }
                break;
        }
        System.out.println("The file name is: "+order.getFurnitureType()+"_"+order.getFurnitureCategory()+
                "_Order_["+ formatDateTime + "].txt");
        //db.pushLocal(); //update all tables in the database.
    }

    /**
     * This method prompts the user to input if they want to make another order or not.  If they do,
     * the program re-loops to the beginning and the whole sequence of user inputs is preformed again. If not,
     * the program ends.
     * @param order object
     * @param input user input reader
     * @return true - if the user inputs Y and false if the user inputs N.
     */
    public boolean makeAnotherOrder(Order order, Scanner input){
        boolean command = true;
        StringBuilder yn = new StringBuilder();
        while(true){
            System.out.println();
            System.out.println("Would you like to make another order? (Y/N)");
            yn.append(input.nextLine().trim());
            if(yn.length() < 2){
                if(yn.toString().equals("y") || yn.toString().equals("Y") || yn.toString().equals("n") || yn.toString().equals("N")){
                    if(yn.toString().equals("n") || yn.toString().equals("N")){
                        command = false;
                    }
                    break;
                }
                else{
                    System.out.println("You can only enter Y or N");
                }
            }
            else{
                System.out.println("You can only enter Y or N");
            }
        }
        return command;
    }

    /**
     * Default constructor for class Order
     */
    public Order(){
        this.ldt = LocalDateTime.now();
    }

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

    /**
     * Getter method for LocalDateTime
     * @return current time on the System
     */
    public LocalDateTime getLDT(){
        return this.ldt;
    }

}//End of Order documentation

