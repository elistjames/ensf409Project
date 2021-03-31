package edu.ucalgary.ensf409;

import java.io.*;import java.util.ArrayList;

public class CreateOrder
{
    private Order originalRequest;
    private PrintWriter outStream;
    private String[] itemsOrdered;
    private String totalPrice;
    private int orderID;
    Database db;

    CreateOrder(Order request, int orderNumber, Database db)
    {
        this.originalRequest = request;
        this.orderID = orderNumber;
        try
        {
            this.outStream = new PrintWriter(new File("Order" + Integer.toString(orderID) + ".txt"));
        }
        catch (IOException e)
        {
            System.out.println("error opening output file");
            System.exit(1);
        }
        this.db = db;
    }
    public void setOriginalRequest(Order request){
        this.originalRequest = request;
    }
    public Order getOriginalRequest() {
        return this.originalRequest;
    }
    public void setItemsOrdered(String[] toOrder) {
        if(toOrder != null) {
            itemsOrdered = new String[toOrder.length];
            for (int i = 0; i < toOrder.length; i++) {
                itemsOrdered[i] = toOrder[i];
            }
        }
    }

    public String[] getItemsOrdered() {
        return this.itemsOrdered;
    }
    public void setTotalPrice(String price){
        this.totalPrice = price;
    }
    public String getTotalPrice() {
        return this.totalPrice;
    }
    public void generateOrder() {
        outStream.println("Furniture Order Form");
        outStream.println();
        outStream.println("Faculty Name:");
        outStream.println("Contact:");
        outStream.println("Date:");
        outStream.println();
        outStream.println("Original Request: " + originalRequest.getFurnitureType() + "," + originalRequest.getNumberItems());
        outStream.println();
        outStream.println("Items Ordered");
        for (int i = 0; i < this.itemsOrdered.length; i++) {
            outStream.println("ID: " + this.itemsOrdered[i]);
        }
        outStream.println();
        outStream.println("Total Price: " + this.totalPrice);
    }


    public int getLowestPrice(){
        int lowest = 0;
        if(prices.size() >= 1){
            lowest = prices.get(0);
        }
        for(int i = 0; i < prices.size(); i++){
            if(prices.get(i) <= lowest){
                lowest = prices.get(i);
            }
        }
        prices.clear();
        return lowest;
    }

    ArrayList<Integer>prices = new ArrayList<Integer>();


    public void chairPrice(int priceTotal, ArrayList<Integer>alreadyHit, String type, int number, int legs, int arms, int seats, int cushions) {
        int totalPrice2 = priceTotal;
        boolean newEvent;
        for (int i = 0; i < db.getChairs().length; i++) {
            int lCount = legs;
            int aCount = arms;
            int sCount = seats;
            int cCount = cushions;
            ArrayList<Integer>alreadyHit2 = new ArrayList<Integer>(alreadyHit);
            if(db.getChairs()[i].getType().equals(type)){
                newEvent = true;
                for(int j = 0; j < alreadyHit.size(); j++){
                    if(i == alreadyHit.get(j)){
                        newEvent = false;
                    }
                }
                if(newEvent){
                    boolean used = false;
                    alreadyHit2.add(i);
                    if(db.getChairs()[i].getLegs().equals("Y") && legs < number){
                        lCount = legs+1;
                        used = true;
                    }
                    if(db.getChairs()[i].getArms().equals("Y") && arms < number){
                        aCount = arms+1;
                        used = true;
                    }
                    if(db.getChairs()[i].getSeat().equals("Y") && seats < number){
                        sCount = seats+1;
                        used = true;
                    }
                    if(db.getChairs()[i].getCushion().equals("Y") && cushions < number){
                        cCount = cushions+1;
                        used = true;
                    }
                    totalPrice2 = priceTotal + db.getChairs()[i].getPrice();
                    if(lCount+aCount+sCount+cCount == number*4){
                        prices.add(totalPrice2);
                        return;
                    }

                    //if(used){
                    chairPrice(totalPrice2, alreadyHit2, type, number, lCount, aCount, sCount, cCount);
                    //}
                    //else{
                        //chairPrice(priceTotal, alreadyHit2, type, number, lCount, aCount, sCount, cCount);
                    //}
                }
            }
        }
    }
}
