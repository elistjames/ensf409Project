package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 */
public class Order
{
    private String furnitureCategory;
    private String furnitureType;
    private int numberItems;

    static boolean command = true;

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int orderCounter = 1;
        Order order = new Order();
        Database db = new Database("jdbc:mysql://localhost/inventory", "scm", "ensf409");
        db.initializeConnection();
        db.updateLocal();
        /**
         *
         */
        while(Order.command){

            Scanner input1 = new Scanner(System.in);
            /**
             *
             */
            loop:   while(true){
                System.out.println("Please enter a number corresponding to your desired furniture category:");
                System.out.println("(1) Desk");
                System.out.println("(2) Chair");
                System.out.println("(3) Filing");
                System.out.println("(4) Lamp");

                switch(input1.nextLine().trim()){
                    case "1":
                        order.setFurnitureCategory("desk");
                        break loop;
                    case "2":
                        order.setFurnitureCategory("chair");
                        break loop;
                    case "3":
                        order.setFurnitureCategory("filing");
                        break loop;
                    case "4":
                        order.setFurnitureCategory("lamp");
                        break loop;
                    default:
                        System.out.println("Invalid Entry");
                        break;
                }
            }
            /**
             *
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
            order.setNumberItems(n);
            CreateOrder co = new CreateOrder(order, orderCounter, db);

            db.initializeConnection();
            db.updateLocal();
            ArrayList<Integer> already = new ArrayList<Integer>();
            switch(order.getFurnitureCategory()){

                case "desk":
                    co.deskPrice(db.getDesk(), 0, already, order.getFurnitureType(), order.getNumberItems(), 0, 0, 0);
                    break;
                case "chair":
                    co.chairPrice(db.getChairs(), 0, already, order.getFurnitureType(), order.getNumberItems(), 0, 0, 0, 0);
                    break;
                case "filing":
                    co.filingPrice(db.getFilings(), 0, already, order.getFurnitureType(), order.getNumberItems(), 0, 0, 0);
                    break;
                case "lamp":
                    co.lampPrice(db.getLamps(), 0, already, order.getFurnitureType(), order.getNumberItems(), 0, 0);
                    break;
            }
            System.out.println();
            int lowest = co.getLowestPrice();
            if(lowest != 0){
                System.out.println("The price found to make this item is: $" + lowest+".00");
            }
            else{
                System.out.println("Impossible to make this order due to lack inventory");
            }

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
     *
     */
    public Order(){

    }

    /**
     *
     * @return
     */
    public int getNumberItems() {
        return numberItems;
    }

    /**
     *
     * @return
     */
    public String getFurnitureCategory() {
        return furnitureCategory;
    }

    /**
     *
     * @return
     */
    public String getFurnitureType() {
        return furnitureType;
    }

    /**
     *
     * @param numberItems
     */
    public void setNumberItems(int numberItems) {
        this.numberItems = numberItems;
    }

    /**
     *
     * @param furnitureCategory
     */
    public void setFurnitureCategory(String furnitureCategory) {
        this.furnitureCategory = furnitureCategory;
    }

    /**
     *
     * @param furnitureType
     */
    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

}

