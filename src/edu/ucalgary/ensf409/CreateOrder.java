package edu.ucalgary.ensf409;

import java.io.*;import java.util.ArrayList;

public class CreateOrder
{
    private Order originalRequest;
    private PrintWriter outStream;
    private String[] itemsOrdered;
    private int totalPrice;
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
    public void setTotalPrice(int price){
        this.totalPrice = price;
    }
    public int getTotalPrice() {
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

    public ArrayList<Integer> updateTable(int lowestIndex){
        return combinations.get(lowestIndex);
    }


    public boolean newEvent(ArrayList<ArrayList<Integer>> arr, int index){
        boolean didNotHappen = true;
        for(int j = 0; j < arr.size(); j++){
            if(index == arr.get(j).get(0)){
                didNotHappen = false;
            }
        }
        return didNotHappen;
    }
    ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> prices = new ArrayList<Integer>();

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
    public void chairPrice(Chair table[], int priceTotal, ArrayList<ArrayList<Integer>>alreadyHit, String type, int number,
                           int legs, int arms, int seats, int cushions) {
        int totalPrice2 = priceTotal;
        for (int i = 0; i < table.length; i++) {
            int lCount = legs;
            int aCount = arms;
            int sCount = seats;
            int cCount = cushions;
            ArrayList<ArrayList<Integer>>alreadyHit2 = new ArrayList<ArrayList<Integer>>(alreadyHit);
            if(table[i].getType().equals(type)){
                if(newEvent(alreadyHit, i)){
                    alreadyHit2.add(i);
                    if(db.getChairs()[i].getLegs().equals("Y") && legs < number){
                        lCount = legs+1;
                    }
                    if(db.getChairs()[i].getArms().equals("Y") && arms < number){
                        aCount = arms+1;
                    }
                    if(db.getChairs()[i].getSeat().equals("Y") && seats < number){
                        sCount = seats+1;
                    }
                    if(db.getChairs()[i].getCushion().equals("Y") && cushions < number){
                        cCount = cushions+1;
                    }
                    totalPrice2 = priceTotal + db.getChairs()[i].getPrice();
                    if(lCount+aCount+sCount+cCount == number*4){
                        prices.add(totalPrice2);
                        combinations.add(alreadyHit2);
                        return;
                    }
                    chairPrice(table, totalPrice2, alreadyHit2, type, number, lCount, aCount, sCount, cCount);
                }
            }
        }
    }

    /**
     *
     * @param table
     * @param priceTotal
     * @param alreadyHit
     * @param type
     * @param number
     * @param legs
     * @param tops
     * @param drawers
     */
    public void deskPrice(Desk table[], int priceTotal, ArrayList<Integer>alreadyHit, String type, int number,
                          int legs, int tops, int drawers) {
        int totalPrice2 = priceTotal;
        for (int i = 0; i < table.length; i++) {
            int lCount = legs;
            int tCount = tops;
            int dCount = drawers;
            ArrayList<Integer>alreadyHit2 = new ArrayList<Integer>(alreadyHit);
            if(table[i].getType().equals(type)){
                if(newEvent(alreadyHit, i)){
                    alreadyHit2.add(i);
                    if(table[i].getLegs().equals("Y") && legs < number){
                        lCount = legs+1;
                    }
                    if(table[i].getTop().equals("Y") && tops < number){
                        tCount = tops+1;
                    }
                    if(table[i].getDrawer().equals("Y") && drawers < number){
                        dCount = drawers+1;
                    }
                    totalPrice2 = priceTotal + table[i].getPrice();
                    if(lCount+tCount+dCount == number*3){
                        prices.add(totalPrice2);
                        return;
                    }
                    deskPrice(table, totalPrice2, alreadyHit2, type, number, lCount, tCount, dCount);
                }
            }
        }
    }

    /**
     *
     * @param table
     * @param priceTotal
     * @param alreadyHit
     * @param type
     * @param number
     * @param rails
     * @param drawers
     * @param cabinets
     */
    public void filingPrice(Filing table[], int priceTotal, ArrayList<Integer>alreadyHit, String type, int number,
                            int rails, int drawers, int cabinets) {
        int totalPrice2 = priceTotal;
        for (int i = 0; i < table.length; i++) {
            int rCount = rails;
            int dCount = drawers;
            int cCount = cabinets;
            ArrayList<Integer>alreadyHit2 = new ArrayList<Integer>(alreadyHit);
            if(table[i].getType().equals(type)){
                if(newEvent(alreadyHit, i)){
                    alreadyHit2.add(i);
                    if(table[i].getRails().equals("Y") && rails < number){
                        rCount = rails+1;
                    }
                    if(table[i].getDrawers().equals("Y") && drawers < number){
                        dCount = drawers+1;
                    }
                    if(table[i].getCabinet().equals("Y") && cabinets < number){
                        cCount = cabinets+1;
                    }
                    totalPrice2 = priceTotal + table[i].getPrice();
                    if(rCount+dCount+cCount == number*3){
                        prices.add(totalPrice2);
                        return;
                    }
                    filingPrice(table, totalPrice2, alreadyHit2, type, number, rCount, dCount, cCount);
                }
            }
        }
    }

    /**
     *
     * @param table
     * @param priceTotal
     * @param alreadyHit
     * @param type
     * @param number
     * @param bases
     * @param lightBulbs
     */
    public void lampPrice(Lamp table[], int priceTotal, ArrayList<Integer>alreadyHit, String type, int number,
                          int bases, int lightBulbs) {
        int totalPrice2 = priceTotal;
        for (int i = 0; i < table.length; i++) {
            int bCount = bases;
            int lCount = lightBulbs;
            ArrayList<Integer>alreadyHit2 = new ArrayList<Integer>(alreadyHit);
            if(table[i].getType().equals(type)){
                if(newEvent(alreadyHit, i)){
                    alreadyHit2.add(i);
                    if(table[i].getBase().equals("Y") && bases < number){
                        bCount = bases+1;
                    }
                    if(table[i].getBulb().equals("Y") && lightBulbs < number){
                        lCount = lightBulbs+1;
                    }

                    totalPrice2 = priceTotal + table[i].getPrice();
                    if(bCount+lCount == number*3){
                        prices.add(totalPrice2);
                        return;
                    }
                    lampPrice(table, totalPrice2, alreadyHit2, type, number, bCount, lCount);
                }
            }
        }
    }
}
