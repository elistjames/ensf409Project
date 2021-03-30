package edu.ucalgary.ensf409;

import java.util.Scanner;

public class Order {
    private String category;
    private String type;
    private int amount;

    public Order(){}

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void main(String args[]){
        Order order = new Order();
        Scanner input = new Scanner(System.in);
        loop:   while(true){
            System.out.println("Please enter a number corresponding to your desired furniture category:");
            System.out.println("(1) Desk");
            System.out.println("(2) Chair");
            System.out.println("(3) Filing");
            System.out.println("(4) Lamp");

            switch(input.nextLine().trim()){
                case "1":
                    order.setCategory("desk");
                    break loop;
                case "2":
                    order.setCategory("chair");
                    break loop;
                case "3":
                    order.setCategory("filing");
                    break loop;
                case "4":
                    order.setCategory("lamp");
                    break loop;
                default:
                    System.out.println("Invalid Entry");
                    break;
            }
        }
        String t = "";
        String n = "";
        do{
            System.out.println("Please enter a type: ");
            t = input.nextLine().trim();
            System.out.println("Please enter the amount: ");
            n = input.nextLine().trim();
            if(t.equals("") && (n.equals("")||n.equals("0"))){
                System.out.println("Invalid input, try again.");
            }
        }
        while (t.equals("") && (n.equals("")||n.equals("0")));
        order.setType(t);
        order.setAmount(Integer.parseInt(n));

        System.out.println(order.getCategory());
        System.out.println(order.getType());
        System.out.println(order.getAmount());


    }
}

