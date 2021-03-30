package edu.ucalgary.ensf409;

import java.io.*;

public class CreateOrder {
    private Order originalRequest;
    private PrintWriter outStream;
    private String[] itemsOrdered;
    private String totalPrice;

    CreateOrder() {
        this.originalRequest = null;
        try {
            this.outStream = new PrintWriter(new File("Order.txt"));
        }catch (IOException e) {
            System.out.println("error opening output file");
            System.exit(1);
        }
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
        outStream.println("Original Request: " + originalRequest.getType() + "," + originalRequest.getAmount());
        outStream.println();
        outStream.println("Items Ordered");
        for(int i = 0; i < this.itemsOrdered.length; i++) {
            outStream.println("ID: " + this.itemsOrdered[i]);
        }
        outStream.println();
        outStream.println("Total Price: " + this.totalPrice);
    }
}
