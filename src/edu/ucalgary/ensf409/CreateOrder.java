package edu.ucalgary.ensf409;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateOrder
{
    private Order originalRequest;
    private PrintWriter outStream;
    private String[] itemsOrdered;
    private int totalPrice;
    Database db;
    private ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> prices = new ArrayList<Integer>();

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
        outStream.println("Original Request: " + originalRequest.getFurnitureType() +" "+ originalRequest.getFurnitureCategory()+ ", " + originalRequest.getNumberItems());
        outStream.println();
        outStream.println("Items Ordered");
        for (String s : this.itemsOrdered) {
            outStream.println("ID: " + s);
        }
        outStream.println();
        outStream.println("Total Price: $" + this.totalPrice+".00");
        outStream.close();
    }
    public void generateRecommendation() {
        outStream.println("Original request cannot be completed due to current inventory");
        outStream.println();
        outStream.println("Original Request: " + originalRequest.getFurnitureType() +" "+ originalRequest.getFurnitureCategory()+ "," + originalRequest.getNumberItems());
        outStream.println();
        outStream.println("To complete your order please contact the following UofC approved suppliers: ");
        outStream.println(suppliersOf(this.originalRequest.getFurnitureCategory(), this.originalRequest.getFurnitureType()));
        outStream.close();
    }

    public String suppliersOf(String category, String type) {
        ArrayList<String> suppliers = new ArrayList<String>();
        String toRet = "";
        if(category.equals("Chair")) {
            for (int i = 0; i < this.db.getChairs().length; i++) {
                if (this.db.getChairs()[i].getType().equals(type)) {
                    for(int j = 0; j < this.db.getManufacturers().length; j++) {
                        if(this.db.getChairs()[i].getManuId() == this.db.getManufacturers()[j].getManuId()) {
                            if(!suppliers.contains(this.db.getManufacturers()[j].getName())) {
                                suppliers.add(this.db.getManufacturers()[j].getName());
                            }
                        }
                    }
                }
            }
        }else if (category.equals("Desk")) {
            for (int i = 0; i < this.db.getDesk().length; i++) {
                if (this.db.getDesk()[i].getType().equals(type)) {
                    for (int j = 0; j < this.db.getManufacturers().length; j++) {
                        if (this.db.getDesk()[i].getManuId() == this.db.getManufacturers()[j].getManuId()) {
                            if (!suppliers.contains(this.db.getManufacturers()[j].getName())) {
                                suppliers.add(this.db.getManufacturers()[j].getName());
                            }
                        }
                    }
                }
            }
        } else if(category.equals("Filing")){
            for (int i = 0; i < this.db.getFilings().length; i++) {
                if (this.db.getFilings()[i].getType().equals(type)) {
                    for(int j = 0; j < this.db.getManufacturers().length; j++) {
                        if(this.db.getFilings()[i].getManuId() == this.db.getManufacturers()[j].getManuId()) {
                            if(!suppliers.contains(this.db.getManufacturers()[j].getName())) {
                                suppliers.add(this.db.getManufacturers()[j].getName());
                            }
                        }
                    }
                }
            }
        } else if (category.equals("Lamp")) {
            for (int i = 0; i < this.db.getLamps().length; i++) {
                if (this.db.getLamps()[i].getType().equals(type)) {
                    for(int j = 0; j < this.db.getManufacturers().length; j++) {
                        if(this.db.getLamps()[i].getManuId() == this.db.getManufacturers()[j].getManuId()) {
                            if(!suppliers.contains(this.db.getManufacturers()[j].getName())) {
                                suppliers.add(this.db.getManufacturers()[j].getName());
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0; i < suppliers.size(); i++) {
            toRet = toRet.concat(suppliers.get(i) + "\n");
        }
        return toRet;
    }

    /**
     * This method searches through the prices ArrayList for the lowest value.
     * @return lowest price in the ArrayList prices.
     */
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
        return lowest;
    }

    /**
     * This method returns the combination inside the ArrayList combinations the corresponds with the lowest price.
     * @return ArrayList of the the indexes in the database furniture table that make up the combination.
     */
    public ArrayList<Integer> getLowestCombination(){
        return combinations.get(prices.indexOf(this.totalPrice));
    }

    /**
     * this method clears the ArrayLists prices and combinations
     */
    public void clearLists(){
        prices.clear();
        combinations.clear();
    }

    /**
     * This method takes in a ArrayList of Integers and an integer called indicator which tells the method what furniture
     * category array to look in. The method returns a String array of the Id's for each furniture item corresponding to the
     * table index numbers in the arraylist.
     * @param itemIndexes Stores the indexes of the items used for the requested order
     * @param indicator Store a number from 0-3 indicating what furniture category to look in.
     * @return String Array of ID numbers corresponding to the Table indexes stored in the ArrayList
     */
    public String[] makeIdArray(ArrayList<Integer> itemIndexes, int indicator){
        String ids[] = new String[itemIndexes.size()];
        switch(indicator){
            case 0:
                for(int i = 0; i < itemIndexes.size(); i++){
                    ids[i] = db.getDesk()[itemIndexes.get(i)].getId();
                }
                break;
            case 1:
                for(int i = 0; i < itemIndexes.size(); i++){
                    ids[i] = db.getChairs()[itemIndexes.get(i)].getId();
                }
                break;
            case 2:
                for(int i = 0; i < itemIndexes.size(); i++){
                    ids[i] = db.getFilings()[itemIndexes.get(i)].getId();
                }
                break;
            default:
                for(int i = 0; i < itemIndexes.size(); i++){
                    ids[i] = db.getLamps()[itemIndexes.get(i)].getId();
                }
                break;
        }
        return ids;
    }

    /**
     * This method checks if the Arraylist arr contains the value i inside it.
     * @param arr ArrayList of Integers
     * @param index value being checked
     * @return true if the value is not in the ArrayList, false otherwise.
     */
    public boolean newEvent(ArrayList<Integer> arr, int index){
        boolean didNotHappen = true;
        for(int j = 0; j < arr.size(); j++){
            if(index == arr.get(j)){
                didNotHappen = false;
            }
        }
        return didNotHappen;
    }


    /**
     * chairPrice recursively searches through the chair array to find all combinations to make the desired order for the
     * user. When a combination is found, it inserts and ArrayList of the combination indexes into a 2D ArrayList called
     * combinations. It also puts the price sum of that found combination into an ArrayList called prices.
     * @param table // array of chair objects, replicating the chair table in the database
     * @param priceTotal // price of combined items
     * @param alreadyHit // stores the indexes of the chairs already checked
     * @param type // furniture type
     * @param number // number of desired furniture items
     * @param legs // counts the number of legs found
     * @param arms // counts the number of arms found
     * @param seats // counts the number of seats found
     * @param cushions // counts the number of cushions found
     */
    public void chairPrice(Chair table[], int priceTotal, ArrayList<Integer>alreadyHit, String type, int number,
                           int legs, int arms, int seats, int cushions) {
        int totalPrice2 = priceTotal; // saves the price total for each recursion stage
        // search through the chair array to find a chair of the desired type
        for (int i = 0; i < table.length; i++) {
            int lCount = legs; // saves the amount of legs found for this recursion stage
            int aCount = arms; // saves the amount of arms found for this recursion stage
            int sCount = seats; // saves the amount of seats found for this recursion stage
            int cCount = cushions; // saves the amount of cushions found for this recursion stage
            ArrayList<Integer>alreadyHit2 = new ArrayList<Integer>(alreadyHit); // saves the already checked chairs for this recursion stage
            if(table[i].getType().equals(type)){ // if the chair at current index matches the desired chair type
                if(newEvent(alreadyHit, i)){ // if the chair at current index hasn't been checked already
                    alreadyHit2.add(i); // add the current index to the ArrayList of checked array elements

                    // If chair at current index has legs and that max number of legs needed has not been reached
                    if(db.getChairs()[i].getLegs().equals("Y") && legs < number){
                        lCount = legs+1; // add legs to the amount of legs found
                    }
                    // If chair at current index has arms and that max number of arms needed has not been reached
                    if(db.getChairs()[i].getArms().equals("Y") && arms < number){
                        aCount = arms+1; // add arms to amount of arms found
                    }
                    // If chair at current index has a seat and that max number of seats needed has not been reached
                    if(db.getChairs()[i].getSeat().equals("Y") && seats < number){
                        sCount = seats+1; // add seat to the amount of seats found
                    }
                    // If chair at current index has a cushion and that max number of cushions needed has not been reached
                    if(db.getChairs()[i].getCushion().equals("Y") && cushions < number){
                        cCount = cushions+1; // add cushion to the amount of cushions found
                    }
                    // adds the chair price at current index to price sum and is saved for current recursion call
                    totalPrice2 = priceTotal + db.getChairs()[i].getPrice();
                    // if the amount legs + arms + seats + cushions found is equal to max amount pieces needed to make order
                    if(lCount+aCount+sCount+cCount == number*4){
                        prices.add(totalPrice2); // add total price of combination to prices ArrayList
                        combinations.add(alreadyHit2); // add the indexes of the combined chairs to the combinations ArrayList
                        return;
                    }
                    chairPrice(table, totalPrice2, alreadyHit2, type, number, lCount, aCount, sCount, cCount); // recursive call
                }
            }
        }
    }

    /**
     * deskPrice recursively searches through the desks array to find all combinations to make the desired order for the
     * user. When a combination is found, it inserts and ArrayList of the combination indexes into a 2D ArrayList called
     * combinations. It also puts the price sum of that found combination into an ArrayList called prices.
     * @param table // array of desk objects, replicating the desk table in the database
     * @param priceTotal // price of combined items
     * @param alreadyHit // stores the indexes of the desks already checked
     * @param type // furniture type
     * @param number // number of desired furniture items
     * @param legs // counts the number of legs found
     * @param tops // counts the number of tops found
     * @param drawers // counts the number of drawers found
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
                        combinations.add(alreadyHit2);
                        return;
                    }
                    deskPrice(table, totalPrice2, alreadyHit2, type, number, lCount, tCount, dCount);
                }
            }
        }
    }

    /**
     * filingPrice recursively searches through the filing array to find all combinations to make the desired order for the
     * user. When a combination is found, it inserts and ArrayList of the combination indexes into a 2D ArrayList called
     * combinations. It also puts the price sum of that found combination into an ArrayList called prices.
     * @param table // array of filing objects, replicating the filing table in the database
     * @param priceTotal // price of combined items
     * @param alreadyHit // stores the indexes of the filings already checked
     * @param type // furniture type
     * @param number // number of desired furniture items
     * @param rails //  counts the number of rails found
     * @param drawers // counts the number of drawers found
     * @param cabinets // counts the number of cabinets found
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
                        combinations.add(alreadyHit2);
                        return;
                    }
                    filingPrice(table, totalPrice2, alreadyHit2, type, number, rCount, dCount, cCount);
                }
            }
        }
    }

    /**
     * lampPrice recursively searches through the lamps array to find all combinations to make the desired order for the
     * user. When a combination is found, it inserts and ArrayList of the combination indexes into a 2D ArrayList called
     * combinations. It also puts the price sum of that found combination into an ArrayList called prices.
     * @param table // array of lamp objects, replicating the lamp table in the database
     * @param priceTotal // price of combined items
     * @param alreadyHit // stores the indexes of the lamps already checked
     * @param type // furniture type
     * @param number // number of desired furniture items
     * @param bases // counts the number of bases found
     * @param lightBulbs // counts the number of bulbs found
     */
    public void lampPrice(Lamp[] table, int priceTotal, ArrayList<Integer>alreadyHit, String type, int number,
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
                    if(bCount+lCount == number*2){
                        prices.add(totalPrice2);
                        combinations.add(alreadyHit2);
                        return;
                    }
                    lampPrice(table, totalPrice2, alreadyHit2, type, number, bCount, lCount);
                }
            }
        }
    }
}
