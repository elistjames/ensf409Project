package edu.ucalgary.ensf409;

import java.io.*;import java.util.ArrayList;

public class CreateOrder
{
    private Order originalRequest;
    private PrintWriter outStream;
    private String[] itemsOrdered;
    private String totalPrice;
    Database db;

    CreateOrder(Order request, int orderNumber, Database db)
    {
        this.originalRequest = request;
        try
        {
            this.outStream = new PrintWriter(new File("Order" + Integer.toString(orderNumber) + ".txt"));
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
            System.arraycopy(toOrder, 0, itemsOrdered, 0, toOrder.length);
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
        for (String s : this.itemsOrdered) {
            outStream.println("ID: " + s);
        }
        outStream.println();
        outStream.println("Total Price: " + this.totalPrice);
    }


    public int getLowestPrice(){
        int lowest = 0;
        if(prices.size() >= 1){
            lowest = prices.get(0);
        }
        for (Integer price : prices) {
            if (price <= lowest) {
                lowest = price;
            }
        }
        prices.clear();
        return lowest;
    }

    public boolean newEvent(ArrayList<Integer> arr, int index){
        boolean didNotHappen = true;
        for(int j = 0; j < arr.size(); j++){
            if(index == arr.get(j)){
                didNotHappen = false;
            }
        }
        return didNotHappen;
    }
    ArrayList<Integer>prices = new ArrayList<Integer>();

    /**
     * the chair price
     * @param priceTotal
     * @param alreadyHit
     * @param type
     * @param number
     * @param legs
     * @param arms
     * @param seats
     * @param cushions
     */
    public void chairPrice(Chair table[], int priceTotal, ArrayList<Integer>alreadyHit, String type, int number, int legs, int arms, int seats, int cushions) {
        int totalPrice2 = priceTotal;
        boolean newEvent;
        for (int i = 0; i < table.length; i++) {
            int lCount = legs;
            int aCount = arms;
            int sCount = seats;
            int cCount = cushions;
            ArrayList<Integer>alreadyHit2 = new ArrayList<Integer>(alreadyHit);
            if(table[i].getType().equals(type)){
                if(newEvent(alreadyHit, i)){
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
                    chairPrice(table, totalPrice2, alreadyHit2, type, number, lCount, aCount, sCount, cCount);
                }
            }
        }
    }
}
